import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0;i<T;i++) {
            int N = Integer.parseInt(br.readLine());
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                int num = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, num);
                maxValue = Math.max(maxValue, num);
            }

            sb.append(minValue).append(' ').append(maxValue).append('\n');
        }


        bw.write(sb.toString());
        bw.flush();


    }
}