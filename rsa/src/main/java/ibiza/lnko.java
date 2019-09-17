package ibiza;



import java.math.BigInteger;

public class lnko {

    public lnko(){}

    public BigInteger lnko(BigInteger a, BigInteger b) {
        BigInteger tmp;

        if (a.compareTo(new BigInteger("0"))==-1) a= a.multiply(new BigInteger("-1"));
        if (b.compareTo(new BigInteger("0"))==-1) b= b.multiply(new BigInteger("-1"));
        while(a.compareTo(new BigInteger("0"))==1) {
            tmp = b;
            b=a;
            a=tmp.remainder(a);

        }
        return b;
    }

    public BigInteger KEA(BigInteger Rk1, BigInteger Rk2){
        BigInteger Qn=Rk1;
        BigInteger qk=Rk1.divide(Rk2);
        BigInteger Xk1=new BigInteger("1");
        BigInteger Xk2=new BigInteger("0");
        BigInteger Yk1=new BigInteger("0");
        BigInteger Yk2=new BigInteger("1");
        BigInteger temp=new BigInteger("0");
        int n=1;


        while (Rk2.compareTo(new BigInteger("0"))==1){
            n+=1;
            qk=Rk1.divide(Rk2);
            temp=Xk1;
            Xk1=Xk2;
            Xk2=Xk1.multiply(qk).add(temp);
            temp=Yk1;
            Yk1=Yk2;
            Yk2=Yk1.multiply(qk).add(temp);
            temp=Rk1;
            Rk1=Rk2;
            Rk2=temp.remainder(Rk1);

        }

        if (n % 2==1){
            Yk1=Yk1.multiply(new BigInteger("-1"));
        }
        while (Yk1.compareTo(new BigInteger("0"))==-1){
            Yk1=Yk1.add(Qn);
        }

        return Yk1;
    }

    public BigInteger KEA2(BigInteger Rk1, BigInteger Rk2){
        BigInteger Qn=Rk1;
        BigInteger qk=Rk1.divide(Rk2);
        BigInteger Xk1=new BigInteger("1");
        BigInteger Xk2=new BigInteger("0");
        BigInteger Yk1=new BigInteger("0");
        BigInteger Yk2=new BigInteger("1");
        BigInteger temp=new BigInteger("0");
        int n=1;


        while (Rk2.compareTo(new BigInteger("0"))==1){
            n+=1;
            qk=Rk1.divide(Rk2);
            temp=Xk1;
            Xk1=Xk2;
            Xk2=Xk1.multiply(qk).add(temp);
            temp=Yk1;
            Yk1=Yk2;
            Yk2=Yk1.multiply(qk).add(temp);
            temp=Rk1;
            Rk1=Rk2;
            Rk2=temp.remainder(Rk1);

        }

        if (n % 2==0){
            Xk1=Xk1.multiply(new BigInteger("-1"));
        }
        /*while (Yk1.compareTo(new BigInteger("0"))==-1){
            Yk1=Yk1.add(Qn);
        }*/

        return Xk1;
    }
    public BigInteger KEA3(BigInteger Rk1, BigInteger Rk2){
        BigInteger Qn=Rk1;
        BigInteger qk=Rk1.divide(Rk2);
        BigInteger Xk1=new BigInteger("1");
        BigInteger Xk2=new BigInteger("0");
        BigInteger Yk1=new BigInteger("0");
        BigInteger Yk2=new BigInteger("1");
        BigInteger temp=new BigInteger("0");
        int n=1;


        while (Rk2.compareTo(new BigInteger("0"))==1){
            n+=1;
            qk=Rk1.divide(Rk2);
            temp=Xk1;
            Xk1=Xk2;
            Xk2=Xk1.multiply(qk).add(temp);
            temp=Yk1;
            Yk1=Yk2;
            Yk2=Yk1.multiply(qk).add(temp);
            temp=Rk1;
            Rk1=Rk2;
            Rk2=temp.remainder(Rk1);

        }

        if (n % 2==1){
            Yk1=Yk1.multiply(new BigInteger("-1"));
        }
        /*while (Yk1.compareTo(new BigInteger("0"))==-1){
            Yk1=Yk1.add(Qn);
        }*/

        return Yk1;
    }



    public static void main(String[] args) {
        lnko ln=new lnko();

        //System.out.println(ln.lnko(new BigInteger("31"),new BigInteger("47")));
        //int a=37/3;
        BigInteger a=new BigInteger("2080");
        BigInteger b=new BigInteger("779");

        //System.out.println(ln.KEA(a,b));
        System.out.println(ln.KEA2(a,b));
        System.out.println(ln.KEA(a,b));
        BigInteger c=a.divide(b);
        //System.out.println(c);
    }
}
