import java.util.*;
import java.io.*;

/*
 * 최대한 많은 과자봉지. 한번에 들고다닐수있는 최대 무게 합 출력
 * 과자 정확히 두 봉지 사야 함!
 * */
class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = 0, M = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int[] weights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(weights);
			int result = -1;
			int l = 0, r = N - 1;

			while (l < r) {
				int sum = weights[l] + weights[r];
				if (sum <= M) {
					result = Math.max(result, sum);
					if (l + 1 >= r)
						r--;
					else
						l++;
				} else {
					// 만약 합이 초과하면 큰쪽을 줄이기
					r--;
				}
			}

			System.out.println("#" + test_case + " " + result);
		}
	}
}