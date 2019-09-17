#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int tabla[22][16] =
            {/*   betü szam {   }   (   *   )   :   =   <   >   _ egyeb $  bu olv            /*
            /* 0:*/{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,   0, 0},
            /* 1:*/{2,  4,  6,  19, 8,  19, 19, 12, 19, 14, 17, 1,  19, 21,  0, 1}, //#kezdõállapot
            /* 2:*/{2,  2,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  -1, 1}, //#azonosítóban
            /* 3:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,   0, 0}, //#azonosító vége
            /* 4:*/{5,  4,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  -1, 1}, //#számban
            /* 5:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,   0, 0}, //#szám vége
            /* 6:*/{6,  6,  6,  7,  6,  6,  6,  6,  6,  6,  6,  6,  6,  19, -1, 1}, //#{} kommentben
            /* 7:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  -1, 0}, //#{} komment vége
            /* 8:*/{20, 20, 20, 20, 20, 9,  20, 20, 20, 20, 20, 20, 20, 19, -1, 1}, //#(-t talált
            /* 9:*/{9,  9,  9,  9,  9,  10, 9,  9,  9,  9,  9,  9,  9,  19, -1, 1}, //#(* *) kommentben
            /*10:*/{9,  9,  9,  9,  9,  10, 11, 9,  9,  9,  9,  9,  9,  19, -1, 1}, //#*(* *)-ban (már csak egy csukó zárójelet vár)
            /*11:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  -1, 0}, //#(* *) komment vége
            /*12:*/{20, 20, 20, 20, 20, 20, 20, 20, 13, 20, 20, 20, 20, 19, -1, 1}, //#: -t talált
            /*13:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  -1, 0}, //#:= token
            /*14:*/{20, 20, 20, 20, 20, 20, 20, 20, 15, 20, 16, 20, 20, 19, -1, 1}, //#<-t talált
            /*15:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  -1, 0}, //#<= token
            /*16:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  -1, 0}, //#<> token
            /*17:*/{20, 20, 20, 20, 20, 20, 20, 20, 18, 20, 20, 20, 20, 19, -1, 1},//#>-t talált
            /*18:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  -1, 0}, //#>= token
            /*19:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,   0, 0}, //#hibakezelõ
            /*20:*/{1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,   1, 0}, //#tovább
            /*21:*/{0}                                                      //#stop
            };
string betu="qwertyuiopasdfghjklzxcvbnm";
string szam="0123456789";
string test;

string bemenet(){
    //bemenet beolvasasa fajlbol
    ifstream myfile ("in.txt");
    string line;
    if (myfile.is_open()){
        while (getline(myfile,line)){
        }
        myfile.close();
    }
    return line;
}


int kovetkezo_sor(int sor, string kar){
    int vissza=tabla[sor][13];
    //megvizsgalja, hogy a tablazat adott sorabol a megadott karakterre melyik sorba ugrik
    if(betu.find(kar)!=std::string::npos){
        vissza=tabla[sor][0];
    }else if(szam.find(kar)!=std::string::npos){
        vissza=tabla[sor][1];
    }else if(kar=="{"){
        vissza=tabla[sor][2];
    }else if(kar=="}"){
        vissza=tabla[sor][3];
    }else if(kar=="("){
        vissza=tabla[sor][4];
    }else if(kar=="*"){
        vissza=tabla[sor][5];
    }else if(kar==")"){
        vissza=tabla[sor][6];
    }else if(kar==":"){
        vissza=tabla[sor][7];
    }else if(kar=="="){
        vissza=tabla[sor][8];
    }else if(kar=="<"){
        vissza=tabla[sor][9];
    }else if(kar==">"){
        vissza=tabla[sor][10];
    }else if(kar==" "){
        vissza=tabla[sor][11];
    }else {
        vissza=tabla[sor][12];
    }

    return vissza;
}

int main()
{
    //az a pozicio, amelyik karakternel tartunk az adott bemenet eseteben
    int lepes=0;
    //az aktualis sor
    int i=1;
    test=bemenet();
    while(i!=21){
        //az aktualis karakter amit vizsgalunk
        string aktualis="";
        aktualis=test.substr(lepes,1);
        //a kovetkezo sor meghatarozasa az elozo sorbol, valamint az adott karakterbol
        i=kovetkezo_sor(i,aktualis);
        //az allapotok meghatarozasa
        if(i==3) cout<<"{azonosito}";
        if(i==5) cout<<"{szam}";
        if((i==7)||(i==11)) cout<<"{komment}";
        if(i==13) cout<<"{ertekadas}";
        if(i==15) cout<<"{kisebb_vagy_egyenlo}";
        if(i==16) cout<<"{nem_egyenlo}";
        if(i==18) cout<<"{nagyobb_vagy_egyenlo}";
/*
        //hibaellenorzo resz
        cout<<endl;
        cout<<"Vizsgalt karakter: ";
        cout<<aktualis<<endl;
        cout<<"Backup: ";
        cout<<tabla[i][14]<<endl;
        cout<<"Sor: ";
        cout<<i<<endl;
        cout<<"Lepes: ";
        cout<<lepes<<endl;
*/
        if(lepes+1>=test.length()){
            i=21;
        }
        if(tabla[i][14]==-1){
            lepes+=1;
        }
    }
    return 0;
}
