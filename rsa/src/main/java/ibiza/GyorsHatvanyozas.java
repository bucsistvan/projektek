package ibiza;

import java.math.BigInteger;

public class GyorsHatvanyozas {
    private BigInteger alap;
    private BigInteger kitevo;
    private BigInteger modulusz;
    public GyorsHatvanyozas(){}

    public BigInteger eredmeny(BigInteger g, BigInteger e, BigInteger m){
        this.alap=g;
        this.kitevo=e;
        this.modulusz=m;

        String binaris=kitevo.toString(2);
        BigInteger a=new BigInteger("1");
        BigInteger b=alap;
        for (int i=binaris.length()-1;i>=0;i--){
            if (binaris.charAt(i)=='1'){
                a=a.multiply(b).remainder(modulusz);
            }
            b= b.multiply(b).remainder(modulusz);
        }

        return a;
    }

    public static void main(String[] args) {
        GyorsHatvanyozas gyorsHatvanyozas=new GyorsHatvanyozas();

        System.out.println(gyorsHatvanyozas.eredmeny(new BigInteger("11"),new BigInteger("120"),new BigInteger("241")));
    }


}
