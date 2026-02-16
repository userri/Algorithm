import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 1;

        solve();

        System.out.println(dp[N]);
        
    }
    static void solve() {
        for (int i = 1; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            // i보다 작은 최대 제곱수 + 그 제곱수를 제외한 dp값(i-j*j)을 최소값으로 업데이트
            for (int j = 1; j*j<=i; j++ ) {
                min = Math.min(min, dp[i-j*j]);
            }
            // min 값 + 최대제곱수(j*j)
            dp[i] = min + 1;
        }
    }
}