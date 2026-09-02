

import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int[][] map;
		int[][] partRowSum;

		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			partRowSum = new int[N][N - (M - 1)];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int k = 0; k < M; k++)
					sum += map[i][k];
				partRowSum[i][0] = sum;
				for (int j = 1; j < N - (M - 1); j++) {
					sum -= map[i][j - 1];
					sum += map[i][j + M - 1];
					partRowSum[i][j] = sum;
				}
			}

			int result = 0;
			// 부분합 배열에서 세로로 묶기
			for (int i = 0; i < N - (M - 1); i++) {
				int sum = 0;
				for (int k = 0; k < M; k++)
					sum += partRowSum[k][i];
				result = Math.max(result, sum);
				for (int j = 1; j < N - (M - 1); j++) {
					sum -= partRowSum[j - 1][i];
					sum += partRowSum[j + (M - 1)][i];
					result = Math.max(result, sum);
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
}