
import java.util.*;
import java.io.*;

class Solution {

	static class Cage {
		int l, r, s;

		Cage(int l, int r, int s) {
			this.l = l;
			this.r = r;
			this.s = s;
		}
	}

	static int N, X, M;
	static Cage[] ham;
	static int[] arr, answer;
	static boolean found;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ham = new Cage[M];
			arr = new int[N + 1];
			answer = new int[N + 1];
			found = false;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				ham[i] = new Cage(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}
			dfs(N);
			bw.write("#" + test_case + " ");
			if (!found)
				bw.write("-1");
			else {
				for (int i = 1; i <= N; i++)
					bw.write(answer[i] + " ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int idx) {
		if (found)
			return;
		if (idx == 0) {
			answer = arr.clone();
			found = true;
			return;
		}

		// 뒤에서 최대부터 채워넣어보기
		for (int i = X; i >= 0; i--) {
			arr[idx] = i;
			if (isValid(idx)) {
				dfs(idx - 1);
			}
			if (found)
				return;
		}

	}

	static boolean isValid(int idx) {

		for (Cage h : ham) {
			// idx가 포함된 케이지만 검사
			if (idx < h.l || h.r < idx)
				continue;
			int sum = 0;
			// idx 이후의 arr[i]는 확정됐다고 가정하고 합 구해보기
			for (int i = idx; i <= h.r; i++) {
				sum += arr[i];
			}

			if (idx == h.l) { // 왼쪽끝까지 도달했으면 합이 si와 동일하지 않으면 실패
				if (sum != h.s)
					return false;
			} else {
				if (sum > h.s)
					return false; // 이 시점에서 si보다 크면 불가능
				// 남은 칸 다 최댓값(X)로 채워도 si에 도달못하면 실패
				int remain = idx - h.l;
				if (sum + X * remain < h.s)
					return false;
			}
		}

		return true;
	}

}