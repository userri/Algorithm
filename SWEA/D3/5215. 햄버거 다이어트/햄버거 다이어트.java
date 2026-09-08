import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int[] dp = new int[L+1];

            // 선호도, 칼로리 저장
            int[][] food = new int[N][2];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                food[i][0] = Integer.parseInt(st.nextToken());
                food[i][1] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < N; i++) {
                int fav = food[i][0];
                int cal = food[i][1];
                for(int j = L; j-cal >= 0; j--) {
                    // 역방향 탐색: 동일한 재료가 중복 적용되는 것을 방지 (0/1 배낭 문제의 핵심)
                    // dp[j - cal] 값이 현재 재료로 갱신되기 전에 참조하여, 재료당 1회만 조합에 사용하도록 보장
                    dp[j] = Math.max(dp[j], dp[j-cal] + fav);
                }
            }
            System.out.println("#" + test_case + " " + dp[L]);
		}
	}
}