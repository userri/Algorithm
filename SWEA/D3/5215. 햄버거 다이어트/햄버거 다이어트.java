
// 냅색
// dp: 결과를 얻기 위한 최소한의 정보: 조합 말고, 제한된 칼로리 안에서 최대점수

import java.util.*;
import java.io.*;
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            // N, L
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int[][] foods = new int[N][2]; // 0에는 점수, 1에는 칼로리
            
            // N개의 줄 - T, K
            for(int i = 0; i < N ;i++) {
                st = new StringTokenizer(br.readLine());
                int t = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                foods[i][0] = t;
                foods[i][1] = k;
            }

            // i번째 칼로리에서 최대 선호도를 저장 -> dp[L]을 정답으로 제출
            int[] dp = new int[10001];

            for(int i = 0; i < N; i++) {
                int taste = foods[i][0];
                int cal = foods[i][1];
                for(int w = L; w-cal >= 0; w--) {
                    dp[w] = Math.max(dp[w], dp[w-cal] + taste);
                }
            }
            System.out.println("#"+test_case+" "+dp[L]);

            
            
        }
    }
}