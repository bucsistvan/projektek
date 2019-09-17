package ibiza;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        lnko ln=new lnko();
        GyorsHatvanyozas gyh=new GyorsHatvanyozas();
        long startTime =0;
        long endTime = 0;
        long totalTime=0;
        long startTime1 =System.nanoTime();
        long endTime1 = 0;
        long totalTime1=0;
        miller miller=new miller();
        BigInteger p=new BigInteger("0");
        BigInteger q=new BigInteger("0");
        int i=0;
        int db=0;
        //A számok méretének megadása bitekben
        int meret=1024;
        //A keresett prímek száma
        int primekszama=2;
        //Random szám generálása
        Random randomGenerator = SecureRandom.getInstance("SHA1PRNG");
        BigInteger randomInteger = new BigInteger(meret, randomGenerator);
        //Ha a szám páros akkor hozzáadunk egyet
        if(randomInteger.remainder(new BigInteger("2")).compareTo(new BigInteger("0"))==0){
            randomInteger=randomInteger.add(new BigInteger("1"));
        }
        System.out.println("Prímek kiszámítása...");
        //Annyiszor hajtjuk végre az utasítássorozatot, amennyi prímet keresünk
        while (i!=primekszama) {
            startTime= System.nanoTime();
            db+=1;
            //Leellenőrizzük a random számról, hogy prim e a Miller-Rabin teszttel
            if (miller.teszt(randomInteger,meret)) {
                //Ha a szám prím akkor lementjük, és választunk megy másik random számot
                i+=1;
                if(i==1) p=randomInteger;
                else q=randomInteger;
                randomInteger = new BigInteger(meret, randomGenerator);
                if(randomInteger.remainder(new BigInteger("2")).compareTo(new BigInteger("0"))==0){
                    randomInteger=randomInteger.add(new BigInteger("1"));
                }
            } else {
                //Ha a szám nem prím, akkor megnöveljük kettővel, így biztos megint páratlan lesz
                randomInteger=randomInteger.add(new BigInteger("2"));
            }
            endTime = System.nanoTime();
            totalTime+=endTime-startTime;
        }


        BigInteger n=p.multiply(q);
        BigInteger Qn=p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));

        //Egy random e szám válsztása 1 és fí ne között, úgy hogy egymáshoz képest relatív prímek legyenek
        BigInteger e=new BigInteger(2*meret, randomGenerator);
        while ((e.compareTo(Qn)!=-1)||(ln.lnko(e,Qn).compareTo(new BigInteger("1"))!=0)){
            e=new BigInteger(2*meret, randomGenerator);
        }
        /*if(ln.lnko(e,Qn).compareTo(new BigInteger("1"))!=0){
            System.out.println("masodik");
        }*/

        BigInteger d=ln.KEA(Qn,e);

        //Üzenet megadása
        BigInteger m=new BigInteger("19990131");
        //Üzenet lekódolása
        BigInteger enc=gyh.eredmeny(m,e,n);
        //Az üzenet visszafejtése
        //BigInteger dec=gyh.eredmeny(enc,d,n);

        //üzenet visszafejtése kínai maradéktétellel
        BigInteger c1=gyh.eredmeny(enc,d.remainder(p.subtract(new BigInteger("1"))),p);
        BigInteger c2=gyh.eredmeny(enc,d.remainder(q.subtract(new BigInteger("1"))),q);
        BigInteger M1=q;
        BigInteger M2=p;
        BigInteger dec=c1.multiply(ln.KEA2(M1,M2)).multiply(M1).add(c2.multiply(ln.KEA3(M1,M2)).multiply(M2));
        //BigInteger dec;


        endTime1   = System.nanoTime();
        //Az adatok kiiratása
        writeOut(p,q,e,d,enc,dec,endTime1,startTime1,totalTime,primekszama);

    }

    static void writeOut(BigInteger a,BigInteger b,BigInteger e,BigInteger d,BigInteger enc,BigInteger dec,long endTime1,long startTime1,long totalTime,long primekszama){
        System.out.println("Az első prim:         "+a);
        System.out.println("A második prim:       "+b);
        System.out.println("Titkosító kitevő:     "+e);
        System.out.println("Visszafejtő kitevő:   "+d);
        System.out.println("Titkosított üzenet:   "+enc);
        System.out.println("Visszafejtett üzenet: "+dec);
        totalTime=totalTime/primekszama;
        long totalTime1 = endTime1 - startTime1;
        System.out.println("Futási idő: "+totalTime1/1000000+" ms");
        System.out.println("Átlagos találat: "+totalTime/1000000+" ms");
    }
}
