import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int before = -1;
        int cnt = 1;
        int max = 1;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(before == arr[i]) {
                cnt++;
                max = Math.max(cnt, max);
            }
            else {
                cnt = 1;
                before = arr[i];
            }
        }
        System.out.println(max);
    }
}