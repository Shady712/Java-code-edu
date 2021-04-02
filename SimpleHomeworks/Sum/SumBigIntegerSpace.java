import java.math.BigInteger;

public class SumBigIntegerSpace {
    public static void main(String[] args) {
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.getType(args[i].charAt(j)) != Character.SPACE_SEPARATOR) {
                    int l = j; 
                    int r = j + 1;
                    while (r < args[i].length() && Character.getType(args[i].charAt(r)) != Character.SPACE_SEPARATOR) {
                        r++;
                    }
		    ans = ans.add(new BigInteger(args[i].substring(l, r)));
                    j = r - 1;
                }
            }
        }
        System.out.println(ans);
     }
}