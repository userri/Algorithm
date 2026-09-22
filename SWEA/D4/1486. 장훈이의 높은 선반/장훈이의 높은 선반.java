

/*
 * 탑의 높이가 B이상인 탑 중에서 높이가 가장 낮은 탑
 * 냅색
 * */
import java.util.*;
import java.io.*;

class Solution {
	static int[] heights;
	static int[] dp;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			heights = new int[N];

			st = new StringTokenizer(br.readLine());
			int S = 0;
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
				S += heights[i];
			}
			dp = new int[S + 1]; // dp[i] 합이 i이하인 것들 중에서 가장 큰 것 저장

			for (int i = 0; i < N; i++) {
				int h = heights[i];
				for (int j = S; j - h >= 0; j--) {
					dp[j] = Math.max(dp[j], dp[j - h] + h);
				}
			}
			int result = Integer.MAX_VALUE;
			for (int i = B; i <= S; i++) {
				if (dp[i] >= B) {
					result = Math.min(result, dp[i]-B);
				}
			}

			System.out.println("#" + test_case + " " + result);
		}
	}

}
