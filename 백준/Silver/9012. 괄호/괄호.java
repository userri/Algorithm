import java.io.*;
import java.util.Arrays;
import java.util.Map;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            int before = 0;
            int after = 0;
            boolean flag = false;
            for (String s : input) {
                if (s.equals("(")) {
                    before++;
                } else {
                    after++;
                }
                if (after > before) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("NO");
                continue;
            }
            if (after == before) {
                System.out.println("YES");
            } else System.out.println("NO");
        }
    }
}