import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        System.out.println("Enter p: ");
        Scanner sc = new Scanner(System.in);
        long p = sc.nextLong();
        BigInteger pBigInteger = BigInteger.valueOf(p);
        String isPrime = isPrimeTrialDivision(pBigInteger);

        if(isPrime == "yes") {
            System.out.println("Enter g: ");
            long g = sc.nextLong();
            System.out.println("Enter a: ");
            long a = sc.nextLong();

            long A = powermod_simple(g, a, p);
            System.out.println("A/B: " + A);

            System.out.println("Enter other user's A/B: ");
            long B = sc.nextLong();

            long k = powermod_simple(B, a, p);
            System.out.println("k: " + k);
        }
        else {
            System.out.println("p is not a prime number!!!");
        }
    }

    private static String isPrimeTrialDivision(BigInteger inumber) {
        // convert BigInteger to BigDecimal:
        BigDecimal number = new BigDecimal(inumber);

        BigDecimal Sqrt = number.sqrt(MathContext.DECIMAL64);
        BigDecimal lSqrt = Sqrt.setScale(0, RoundingMode.UP);

        BigDecimal _sqrt = lSqrt.add(BigDecimal.ONE);

        for (BigDecimal i = new BigDecimal(2); !i.equals(_sqrt); i = i.add(BigDecimal.ONE)) {
            // if this number has a divisor:
            //if ((number % i) == 0) {
            if( number.remainder(i).compareTo(BigDecimal.ZERO) == 0 ) {
                // it's complex, so it isn't prime number:
                return "no";
            }
        }

        // if it is prime:
        return "yes";
    }

    public static long powermod_simple(long a, long b, long n) {
        long tmp = a % n; // or: long tmp = 1

        for (long i = 0; i < b - 1; i++) { // or: for (long i = 0; i < b; i++) {
            tmp = (tmp * (a % n)) % n;
        }

        return tmp;
    }

}
