import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] periods = new int[10][];
        periods[0] = new int[]{0};
        periods[1] = new int[]{1};
        periods[2] = new int[]{2, 4, 8, 6};
        periods[3] = new int[]{3, 9, 7, 1};
        periods[4] = new int[]{4, 6};
        periods[5] = new int[]{5};
        periods[6] = new int[]{6};
        periods[7] = new int[]{7, 9, 3, 1};
        periods[8] = new int[]{8, 4, 2, 6};
        periods[9] = new int[]{9, 1};

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a %= 10;
            b %= periods[a].length;
            b = b == 0 ? periods[a].length : b;

            if (a == 0) {
                System.out.println(10);
                continue;
            }
            System.out.println((int)Math.pow(a,b)%10);
        }
    }
}