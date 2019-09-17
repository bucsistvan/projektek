#include <iostream>
#include <sstream>
#include <fstream>
#include <time.h>
#include <string>
#include <stdlib.h>

using namespace std;

const int meret=15;
string tomb[meret][meret];
string szabalyok[meret];
string nemterm;
string term;
string szo;


void kiir2Dtomb(){
    //A táblázat kiírása
    cout<<endl<<"A felepitett tomb:"<<endl;
    int space=10;
    for(int i=0;i<meret;i++){
        if((tomb[i][1]!="")){
            cout<<"|";
            for(int j=0;j<meret;j++){
                if((tomb[i][j]!="")||((i==0)&&(j==0))){
                    cout<<tomb[i][j];
                    for(int k=0;k<space-tomb[i][j].length();k++){
                        cout<<" ";
                    }
                }
            }
            cout<<"|"<<endl;
        }
    }
}

void elso_sor(){
    //A táblázat elsõ sorának felépítése
    int i=0;
    while(i<term.length()){
        //cout<<term.substr(i,1);
        tomb[0][i+1]=term.substr(i,1);
        i++;
    }
    tomb[0][i+1]="#";
}

void elso_oszlop(){
    //A táblázat elsõ oszlopának felépítése
    int i=0;
    int j=1;
    while(i<nemterm.length()){
        tomb[j][0]=nemterm.substr(i,1);
        j++;
        i++;
    }
    i=0;
    while(i<term.length()){
        tomb[j][0]=term.substr(i,1);
        j++;
        i++;
    }
    tomb[j][0]="#";

}

void beolvas(){
    //Az adatok beolvasása az fájlból
    ifstream myfile ("in.txt");
    string line;
    int i=0;
    if (myfile.is_open()){
        while (getline(myfile,line)){
            i+=1;
            if(i==1){
                term=line;
            }else if(i==2){
                nemterm=line;
            }else if(i==3){
                szo=line;
            }else{
                szabalyok[i-4]=line;
            }
        }
    }
}

void bejar(){
    //A táblázat felépítése az elso sorának és oszlopának valamint a szabályok segitségével
    for(int i=0;i<meret;i++){
        for(int j=0;j<meret;j++){
            if((tomb[i][0]!="") && (tomb[0][j]!="") && (tomb[i][0]!="#")){
                if(tomb[i][0]==tomb[0][j]){
                    tomb[i][j]="pop";
                }else{
                    for(int k=0;k<meret;k++){
                        if(szabalyok[k]!=""){
                            if((tomb[i][0]==szabalyok[k].substr(0,1))&&(szabalyok[k].substr(3,1)==tomb[0][j])){
                                ostringstream stream;
                                stream << k;
                                string x_str = stream.str();
                                tomb[i][j]+="("+szabalyok[k].substr(3,szabalyok[k].length()-3)+","+x_str+")";
                            }
                        }
                    }
                }
            }
            if((tomb[i][0]=="#") && (tomb[0][j]=="#")){
                tomb[i][j]="accept";
            }
            if((tomb[i][j]=="")&&(tomb[i][0]!="") && (tomb[0][j]!="")) tomb[i][j]="error";
        }

    }

}

string szabaly_alkalmazas(string s,string szabaly){
    string vegleges;
    //Az adott szabály lkalmazása az adott sztringre
    vegleges=s.substr(0,s.find(szabaly.substr(0,1)));
    vegleges+=szabaly.substr(3,szabaly.length());
    vegleges+=s.substr(s.find(szabaly.substr(0,1))+1,s.length()-s.find(szabaly.substr(0,1))-1);

    return vegleges;
}

string visszaepites(string s){
    //Az eredeti bemenet visszaéptése a szabályok helyes sorrendjének akalmazásával
    string felepitett;
    int i;
    while(s.length()!=0){
        s=s.substr(1,s.length()-1);
        string num=s.substr(0,1);
        i=stoi(num);
        felepitett=szabaly_alkalmazas(felepitett,szabalyok[i]);
        s=s.substr(2,s.length()-1);
    }
    return felepitett;
}

void ellenoriz(){
    //A bemenet leellenõrzése a táblázat segitségével
    string szo_teszt=szo+"#";
    string felepitett="S#";
    string valasz;
    string szabaly;
    string hasznalt_szabalyok;
    while((szabaly!="accept")&&(szabaly!="error")){
        int i=0;
        while(tomb[i][0]!=felepitett.substr(0,1)){
            i++;
        }
        int j=0;
        while(tomb[0][j]!=szo_teszt.substr(0,1)){
            j++;
        }
        szabaly=tomb[i][j];

        if(szabaly!="error"){
            if(szabaly=="pop"){
                szo_teszt=szo_teszt.substr(1,szo_teszt.length()-1);
                felepitett=felepitett.substr(1,felepitett.length()-1);
            }else if(szabaly!="accept"){
                felepitett=szabaly.substr(1,szabaly.find(",")-1)+felepitett.substr(1,szo_teszt.length()-1);
                hasznalt_szabalyok+="("+szabaly.substr(szabaly.find(",")+1,szabaly.length()-szabaly.find(",")-2)+")";
            }
        }
    }
    if(szabaly!="error"){
        cout<<"Az elemzes sikeres!"<<endl;
        cout<<"A hasznalt szabalyok: "<<hasznalt_szabalyok<<endl;
        cout<<"A bemenet visszaepitese a szabalyok segitsegevel: "<<visszaepites(hasznalt_szabalyok)<<endl;
    }else{
        cout<<"Az elemzes sikertelen!"<<endl;
        cout<<"A vizsgalt szo nem generalhato az adott nyelvtannal!"<<endl;
    }
}

int main()
{
    beolvas();
    //A szabályok kiiratása
    for(int i=0;i<meret;i++){
        if(szabalyok[i]!="") cout<<szabalyok[i]<<endl;;
    }
    cout<<"A vizsgalando bemenet: "<<szo<<endl;
    elso_sor();
    elso_oszlop();
    bejar();
    kiir2Dtomb();
    ellenoriz();
    return 0;
}
