import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String oct = br.readLine().trim();

        if (oct.equals("0")) {
            System.out.println(0);
            return;
        }
        String[] bin3 = {
                "000", "001", "010", "011",
                "100", "101", "110", "111"
        };

        int a = oct.charAt(0) - '0';
        String firstBin = Integer.toBinaryString(a);
        sb.append(firstBin);

        for (int i = 1; i < oct.length(); i++) {
            int b = oct.charAt(i) - '0';
            sb.append(bin3[b]);
        }
        System.out.println(sb.toString());


    }
}