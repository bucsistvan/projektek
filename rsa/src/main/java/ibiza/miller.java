package ibiza;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import static java.lang.Math.pow;

public class miller {
    GyorsHatvanyozas gyh=new GyorsHatvanyozas();
    lnko ln=new lnko();
    Random randomGenerator = SecureRandom.getInstance("SHA1PRNG");
    BigInteger One=new BigInteger("1");
    BigInteger Two=new BigInteger("2");

    public miller() throws NoSuchAlgorithmException {}

    public boolean teszt(BigInteger n, int meret){

        BigInteger p=Two;
        BigInteger d=n.subtract(One);
        int r=1;
        int S=0;
        //S és d kiszámítása
        while(d.remainder(p).equals(0)){
            d=d.divide(Two);
            r+=1;
        }
        S=r;
        //Egy random a szam valsztása, hasonlóan a prímek választásához
        BigInteger a;
        a= new BigInteger(meret, randomGenerator);
        while(ln.lnko(a,n).compareTo(One)!=0){
            a=a.add(One);;
        }
        int i=0;
        boolean b = true;
        //Megvizsgáljuk 10 darab a-ra, hogy a kiválsztott szám prím-e
        while ((i<10)&&(b)){
            if (gyh.eredmeny(a, d, n).compareTo(One)==0) {
                //Ha a gyorshatványozákor kijön, hogy egyenlő 1-gyel, akkor a szám prímgyanus, és mehetünk tovább
                b=true;
            } else {
                //H a gyorshatványozáskor nem jön ki egynek, akkor végignézzük az összes olyan r-re, ami kisebb még mint az S
                b = false;
                r = 1;
                while ((r < S) && (!b)) {
                    if (gyh.eredmeny(a,  (d.multiply(new BigInteger(Double.toString(pow(2, r))))), n).compareTo(n.subtract(One))!=0) {
                    } else {
                        //Ha kijön valamelyik számra, hogy hogy a modulusz eredménye n-1, akkor a szám megint prímgyanús
                        b=true;
                    }
                    r+=1;
                }
            }
            //Ha a szám még prímgyanus, akkor választunk egy új a-t
            if (b) {
                a = a.add(One);
                while (ln.lnko(a, n).compareTo(One) != 0) {
                    a = a.add(One);
                }
                i += 1;
            }
        }
        return b;
    }
}
