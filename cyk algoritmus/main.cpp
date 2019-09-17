#include <iostream>
#include <fstream>
#include <time.h>
#include <string>

using namespace std;
string tomb1[5][5];//={{"0","1"},{"B","S"},{"","A"}};
string tomb2[10][10];//={{"SA","AB","BS","SA"},{"S","S","A","B"} };//{{"-","S","A","B"},{"S"},{"A"},{"B"},{"C"},{"D"}};
string tomb3[10][10];
int n=5;
string bemenet="11110";
int aktualisoszlop(string karakter){
    //visszaadja azt az oszlopszámot, amelyen az aktuális karakter szerepel
    int k=0;
    while((tomb1[0][k]!=karakter)&&(k<5)){
        k+=1;
    }
    return k;
}

string nemterm(int oszlop){
    //Visszaadja azokat a nemterminálisokat, amelyek az adott oszlopon szerepelnek
    string s;
    for(int k=1;k<5;k++){
        s+=tomb1[k][oszlop];
    }
    return s;
}

void kiir2Dtomb(string tomb[10][10]){
    //kiírja a 10x10-es tömböt jó láthatóan
    int space=5;
    for(int i=9-n;i<10;i++){
        cout<<"|";
        for(int j=0;j<n;j++){
            cout<<tomb[i][j];
            for(int k=0;k<space-tomb[i][j].length();k++){
                cout<<" ";
            }
        }
        cout<<"|"<<endl;
    }
}

void beolvas(){
    //beolvassa az adatokat az in.txt fájlból
    ifstream myfile ("in.txt");
    string terminalisok;
    string nemterminalisok;
    string egyesszabalyok[10];
    string kettesszabalyok[10];
    string line;
    int egyesekszama=-1;
    int sor=0;
    if (myfile.is_open()){
    while (getline(myfile,line)){
      sor+=1;
      if(sor==1) terminalisok=line;
      else if(sor==2) nemterminalisok=line;
      else if(sor==3) bemenet=line;
      if((line.length()==2)&&(sor>3)){
         egyesekszama+=1;
        egyesszabalyok[egyesekszama]=line;
      }else if((line.length()==3)&&(sor>3)){
        kettesszabalyok[sor-5-egyesekszama]=line;
      }
    }
    myfile.close();
  }
  /*
  cout<<terminalisok<<endl;
  cout<<nemterminalisok<<endl;
  for(int i=0;i<10;i++){
    cout<<egyesszabalyok[i]<<" ";
  }
  cout<<endl;
  for(int i=0;i<10;i++){
    cout<<"|"<<kettesszabalyok[i]<<"| ";
  }
  cout<<endl;*/
  for(int i=0;i<terminalisok.length();i++){
    tomb1[0][i]=terminalisok.at(i);
  }
  cout<<"A vizsgalando szo:"<<endl;
  cout<<bemenet<<endl;
  n=bemenet.length();
  //cout<<n<<endl;
  int jelzo[5]={0,0,0,0,0};
  for(int i=0;i<5;i++){

    for(int j=0;j<10;j++){
        if((tomb1[0][i]!="")&&(egyesszabalyok[j]!="")){
            string valami;
            valami=egyesszabalyok[j].at(1);
            if(tomb1[0][i]==valami){
                jelzo[i]+=1;
                tomb1[jelzo[i]][i]=egyesszabalyok[j].at(0);
            }
        }
    }
  }
  ///
  for(int i=0;i<10;i++){
    string temp1;
    string temp2;
    string temp3;
    if(kettesszabalyok[i]!=""){
        temp2=kettesszabalyok[i].at(1);
        temp3=kettesszabalyok[i].at(2);
        temp1=kettesszabalyok[i].at(0);
        tomb2[1][i]=temp1;
        tomb2[0][i]=temp2+temp3;
    }
  }
  ///
  cout<<endl<<"Egyes szabalyok:"<<endl;
  for(int i=0;i<5;i++){
        //cout<<"|";
    /*for(int j=0;j<5;j++){
    if(tomb1[i][j]!="")cout<<tomb1[i][j]<<"  ";
    //else cout<<"   ";
    }*/
    for(int j=0;j<5;j++){
        if(tomb1[j][i]!=""){
            if(j==0) cout<<tomb1[j][i]<<" -> ";
            else cout<<tomb1[j][i]<<"|";
        }
    }
    cout<<endl;
  }
  cout<<"kettes szabalyok:"<<endl;
  for(int i=0;i<=sor-5-egyesekszama;i++){
    cout<<tomb2[1][i]<<" -> ";
    cout<<tomb2[0][i]<<endl;
  }
  //cout<<sor-5-egyesekszama<<endl;
}

string szabalykereso(string s, int szint){
    //Megvizsgálja, hogy az adott string szerepel-e a 2. tömb elsõ sorában, és ha igen, akkor visszaadja a második sorában szereplõ karaktert
    string megoldasok;
    for(int i=0;i<10;i++){
        if(s==tomb2[0][i]){
            megoldasok+=tomb2[1][i];
        }
    }
    return megoldasok;
}

string dupla_kiszedes(string s){
    //Kiszedi az adott stringbõl azokat a karaktereket, amelyek többször fodulnak elõ
    string t;
    for(int i=0;i<s.length();i++){
        bool bu=false;
        for(int j=i+1;j<s.length();j++){
            if(s.at(i)==s.at(j)) bu=true;
        }
        if(!bu) t+=s.at(i);
    }
    return t;
}

string mintakereso(string elso, string masodik, int szint){
    //Végigmegy a 2 stringen, és kiválaszt minegyikbõl 1 karaktert, majd az azokból létrehozott párt továbbadja
    string vegleges_m;
    string s1;
    string s2;
    for(int i=0;i<elso.length();i++){
        s1=elso.at(i);
        for(int j=0;j<masodik.length();j++){
            s2=masodik.at(j);
            vegleges_m+=szabalykereso(s1+s2, szint);
        }
    }
    //visszaküldi a végleges megoldásokat, amelyek már nem tartalmaznak ismétlõdést
    return dupla_kiszedes(vegleges_m);
}

void parkereso(int kx,int ky,int szint){
    //Meghatározza aokat a párokat, amelyek az adott pozición levõ nemterminálisokbõl levezethetõek
    string minta;
    for(int i=szint-1;i>0;i--){
        string elso=tomb3[kx][ky+i];
        string masodik=tomb3[kx+i-szint][ky];
        minta+=mintakereso(elso,masodik, szint);
        /*cout<<szint<<endl;
        cout<<elso<<endl;
        cout<<masodik<<endl;*/
    }
    tomb3[kx][ky]=dupla_kiszedes(minta);
}

void tobbi(int szint){
    //Megkeresi minden szinten azoknak a pozícióját, amelyeket vizsgálni kell
    for(int i=n-szint+1;i>=1;i--){
        //int koordinatax=i+szint+3;
        int koordinatax=i+szint-n+8;
        int koordinatay=i-1;
        //cout<<koordinatax<<endl;
        //cout<<koordinatay<<endl;
        parkereso(koordinatax,koordinatay,szint);
        //cout<<"tobbi: "<<i<<endl;
    }
}

void minden_szint(){
    //Végigmegy az összes szinten
    for(int i=2;i<=n;i++){
        tobbi(i);
    }
}

int main(){
    //Beolvassa az adatokat az in.txt állományból
    beolvas();
    //Az elsõ sor feltöltése az elsõ tömb és a bemenet segitségével
    for(int i=n;i>0;i--){
        string betu(1,bemenet.at(n-i));
        tomb3[i+9-n][i-1]=nemterm(aktualisoszlop(betu));
        tomb3[i+8-n][i-1]=betu;
    }
    //A tömb kiiratása, hogy lássuk az elsõ lépés után az eredményt
    cout<<endl<<"A tomb kiiratasa az elso sor feltoltese utan:"<<endl;
    kiir2Dtomb(tomb3);
    //A további szintek feltöltése
    minden_szint();
    //A tömb ismételt kiiratása, hogy lássuk a végeredményt
    cout<<endl<<"A vegso megoldas kiiratasa:"<<endl;
    kiir2Dtomb(tomb3);
    //Maga a válasz arra, hogy a szó levezethetõ-e az adott nyelvtanba
    cout<<endl<<"A szo az adott nyelvtanban ";
    if(tomb3[9][0].find("S")!=string::npos){
        cout<<"levezetheto!"<<endl;
    }else cout<<"nem vezetheto le!"<<endl;
    return 0;
}
