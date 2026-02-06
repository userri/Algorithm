
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        boolean one = true;
        int result = 0;
        boolean even = false;
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            if (str.equals("*")) {
                even = i % 2 == 0;
                one = !one;
                continue;
            }
            int num = Integer.parseInt(str);
            if (one) {
                result += num;
            } else {
                result += 3 * num;
            }
            one = !one;
        }
//        System.out.println(0 + 1*3+ 6 + 8 *3+ 1 + 9*3 + 8 + 3*3+2+1+4*3 );
//        System.out.println("result : " + result);
        int x = 0;
        if (even) {
            for (int i = 0; i <= 9; i++) {
                x = i;
                if ((result + x) % 10 == 0) {
                    //System.out.println(result + x);
//                    System.out.println("case 1: 찾음!");
                    break;
                }
            }
        } else {
            for (int i = 0; i <= 9; i++) {
                x = i;
                if ((result + 3 * x) % 10 == 0) {
//                    System.out.println("case 2:찾음!");
                    break;
                }

            }
        }
        System.out.println(x);

    }
}