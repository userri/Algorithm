import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] dp = new int[N+1];
        if (N == 1) {
            System.out.println(1);
            return;
        }
        if (N==2) {
            System.out.println(3);
            return;
        }
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i<=N; i++) {
            dp[i] = dp[i-1]%10007 + (2*dp[i-2])%10007;
        }
        System.out.println(dp[N]%10007);
    }
}