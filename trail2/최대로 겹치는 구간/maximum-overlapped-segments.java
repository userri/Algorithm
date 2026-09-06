

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] arr = new int[201];
        int max = 0;
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for(int j = a; j < b; j++) {
                arr[j+100] += 1;
                max = Math.max(max, arr[j+100]);
            }
        }
        System.out.println(max);
    }
}