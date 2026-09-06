
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        int max = 0;
        
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for(int j = a; j <= b; j++) {
                arr[j] += 1;
                max = Math.max(max, arr[j]);
            }
        }
        System.out.println(max);
    }
}