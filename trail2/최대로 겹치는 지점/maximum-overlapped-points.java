import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[101];
        int max = 0;
        for(int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            for(int i = x1 ; i <= x2; i++) {
                nums[i] += 1;
                max= Math.max(max, nums[i]);
            }
        }
        System.out.println(max);
        // Please write your code here.
    }
}