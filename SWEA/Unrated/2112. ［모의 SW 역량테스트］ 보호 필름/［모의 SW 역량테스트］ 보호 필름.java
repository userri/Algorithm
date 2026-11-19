
import java.util.*;
import java.io.*;

class Solution {
	static int[][] films;
	static int D, W, K, min;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			min = K;
			films = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					films[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0);
			sb.append("#" + test_case + " ").append(min);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int row, int cnt) {
		if (cnt >= min) return;
		if (row == D) {
			if(check()) {
				min = Math.min(min, cnt);
			}
			return;
		}
		
		int[] temp = Arrays.copyOf(films[row], W);
		dfs(row + 1, cnt);
		Arrays.fill(films[row], 0);
		dfs(row + 1, cnt + 1);
		Arrays.fill(films[row], 1);
		dfs(row + 1, cnt + 1);
		films[row] = temp;		
	}

	static boolean check() {
		for (int j = 0; j < W; j++) {
			int cnt = 1;
			int before = films[0][j];
			for (int i = 1; i < D; i++) {
				int temp = films[i][j];
				if (temp == before) {
					cnt++;
				} else {
					cnt = 1;
					before = temp;
				}
				if (cnt >= K)
					break;
			}
			if (cnt < K)
				return false;
		}

		return true;
	}
}