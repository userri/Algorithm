import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N+1];
        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = score[1];
        // 한칸씩 밟고 올라오는게 최대
        if (N >= 2) dp[2] = score[1] + score[2]; 
        // 1,3칸 밟을지 /2,3칸 밟을지
        if (N >= 3) dp[3] = Math.max(score[1], score[2]) + score[3];
        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i-3] + score[i-1], dp[i-2]) + score[i];
            // 현재 계단에 도달하기 위해
            // 이전 3개의 계단에서
            // 1,3 밟고 올건지
            // 2 밟고 올건지
        }

        System.out.println(dp[N]);
    }
}