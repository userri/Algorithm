
import java.util.*;
import java.io.*;

class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int month1 = Integer.parseInt(st.nextToken());
			int month3 = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());

			int[] plan = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++)
				plan[i] = Integer.parseInt(st.nextToken());

			int[] dp = new int[13];

			for (int i = 1; i <= 12; i++) {
				// 월별로 지난달 최소값 + 이번달 (일별 이용권 구매 합 vs 월별 이용권) 중 작은값 계산함
				dp[i] = dp[i - 1] + Math.min(day * plan[i], month1);

				// 3달 이용권 고려
				if (i >= 3) {
					dp[i] = Math.min(dp[i], dp[i - 3] + month3);
				} else {
					// 1월, 2월은 i-3이 존재하지 않으므로 그냥 month3과 최솟값 비교
					dp[i] = Math.min(dp[i], month3);
				}
			}

			// 최종 1년 이용권과 비교
			dp[12] = Math.min(dp[12], year);

			sb.append("#" + test_case + " " + dp[12]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}