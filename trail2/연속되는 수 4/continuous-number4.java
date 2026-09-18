import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int maxCnt = 1;
        int cnt = 1;
        for(int i = 1; i < n; i++) {
            if(arr[i] > arr[i-1]) {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
            }
            else {
                cnt = 1;
            }
            // System.out.println(arr[i] + " " + cnt);
        }
        System.out.println(maxCnt);
    }
}