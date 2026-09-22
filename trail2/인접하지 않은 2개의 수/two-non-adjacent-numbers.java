import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 2; j < n; j++) {
                result = Math.max(result, arr[i] + arr[j]);
            }
        }
        System.out.println(result);
    }
}