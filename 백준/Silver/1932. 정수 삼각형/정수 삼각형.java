import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] nums = new int[N][];
        int[][] dp = new int[N][];
        for(int i = 0 ;i < N;i++) {
            nums[i] = new int[i+1];
            dp[i] = new int[i+1];
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < i+1;j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = nums[0][0];
        for(int i = 0;i < N-1;i++) {
            for(int j = 0; j < nums[i].length;j++) {
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + nums[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + nums[i+1][j+1]);
            }
        }
        int max = 0;
        for(int n:dp[dp.length-1]) {
            max = Math.max(max, n);
        }
        System.out.println(max);
    }
}