

import java.util.*;
import java.io.*;

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
			int result = -1;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int sum = weights[i] + weights[j];
					if(sum > M) continue;
					result = Math.max(result, sum);
				}
			}

			System.out.println("#" + test_case + " " + result);
		}
	}
}