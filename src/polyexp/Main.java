/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package polyexp;

import java.math.BigDecimal;
import java.math.BigInteger;
import sun.security.util.BigInt;

/**
 *
 * @author Dave
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigInteger stax = new BigInteger("7");
        BigInteger stb = new BigInteger("3");
        BigInteger exp = new BigInteger("2");//6905520
        BigInteger ax = stax;
        BigInteger b = stb;
        BigInteger N = new BigInteger("6899713");
        BigInteger i = new BigInteger("1");
        while(i.compareTo(exp) < 0) {
            BigInteger hax = (stax.multiply(b).add(stb.multiply(ax))).mod(N);
            b = (stb.multiply(b).add(new BigInteger("-" + stax.multiply(ax).toString()))).mod(N);
            ax = hax;
            if(b.compareTo(BigInteger.ZERO) < 0)
                b = b.add(N);
            if(ax.compareTo(BigInteger.ZERO) < 0)
                ax = ax.add(N);
            i = i.add(BigInteger.ONE);
        }
        System.out.println("ax = " + ax + "; b = " + b + ";");
        BigInteger ax2[] = {new BigInteger("4582680"), new BigInteger("3802776"),
            new BigInteger("182753"), new BigInteger("4909647"), new BigInteger("4097434")};
        BigInteger b2[] = {new BigInteger("3238899"), new BigInteger("1201197"),
            new BigInteger("2944918"), new BigInteger("2688787"), new BigInteger("432602")};
        BigInteger wax = ax2[0];
        BigInteger wb = b2[0];
        for(int j = 1; j< 5; j++) {
            BigInteger hwax = (wax.multiply(b2[j]).add(wb.multiply(ax2[j]))).mod(N);
            wb = (b2[j].multiply(wb).add(new BigInteger("-" + (ax2[j].multiply(wax)).toString()))).mod(N);
            wax = hwax;
            if(wax.compareTo(BigInteger.ZERO) < 0)
                wax = wax.add(N);
            if(wb.compareTo(BigInteger.ZERO) < 0)
                wb = wb.add(N);
        }
        System.out.println("ax = " + wax + "; b = " + wb + ";");
        BigInteger ax3[] = {new BigInteger("854"), new BigInteger("854"),
            new BigInteger("53"), new BigInteger("473"), new BigInteger("3539018")};
        BigInteger b3[] = {new BigInteger("44"), new BigInteger("124"),
            new BigInteger("885"), new BigInteger("523"), new BigInteger("3346415")};
        BigInteger wax3 = ax3[0];
        BigInteger wb3 = b3[0];
        BigInteger targetax = new BigInteger("6623980");
        BigInteger targetb = new BigInteger("3158963");
        for(int j = 1; j<4; j++) {
            BigInteger hwax = (wax3.multiply(b3[j]).add(wb3.multiply(ax3[j]))).mod(N);
            wb3 = (b3[j].multiply(wb3).add(new BigInteger("-" + (ax3[j].multiply(wax3)).toString()))).mod(N);
            wax3 = hwax;
            if(wax3.compareTo(BigInteger.ZERO) < 0)
                wax3 = wax3.add(N);
            if(wb3.compareTo(BigInteger.ZERO) < 0)
                wb3 = wb3.add(N);
        }
        BigInteger wb3Inv = wb3.modInverse(N);
        BigInteger coefbInv = ((wb3.multiply(wb3)).add(wax3.multiply(wax3))).modInverse(N);
        /* (wax3 * b + wb3 * a) = targetax
         * (wb3*b - wax3 * a) = targetb
         * a = (targetax - wax3 *b) * wb3Inv
         * wb3*b* - wax3 * ((targetax - wax3 *b) * wb3Inv) = targetb
         * wb3*wb3*b-wax3*targetax+wax3*wax3*b = targetb*wb3
         * (wb3*wb3 + wax3*wax3)*b = targetb*wb3+wax3*targetax
         * b = (targetb*wb3+wax3*targetax)*coefbInv
         */
        BigInteger solvb = (((targetb.multiply(wb3)).add(wax3.multiply(targetax))).multiply(coefbInv)).mod(N);
        BigInteger solva = ((targetax.add(new BigInteger("-" + (wax3.multiply(solvb)).toString()))).multiply(wb3Inv)).mod(N);
        BigInteger tmp = new BigInteger("5882476");
        tmp.modInverse(N);
        System.out.println("ax3 = " + wax3 + "; b3 = " + wb3 + ";" + tmp.modInverse(N));
        System.out.println("Solved a = " + solva + "; b = " + solvb);
        ax3[4] = solva;
        b3[4] = solvb;
//        for(int j = 1; j<5; j++) {
        int j=4;
            BigInteger hwax = (wax3.multiply(b3[j]).add(wb3.multiply(ax3[j]))).mod(N);
            wb3 = (b3[j].multiply(wb3).add(new BigInteger("-" + (ax3[j].multiply(wax3)).toString()))).mod(N);
            wax3 = hwax;
            if(wax3.compareTo(BigInteger.ZERO) < 0)
                wax3 = wax3.add(N);
            if(wb3.compareTo(BigInteger.ZERO) < 0)
                wb3 = wb3.add(N);
//        }
        System.out.println("checking ax3 = " + wax3 + "; b3 = " + wb3 + ";");

    }


}
