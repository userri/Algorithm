
import java.util.*;
import java.io.*;

class Solution {
	static boolean[] row, col, rightUp, rightDown;
	static int N, answer;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			// 가로, 세로, 우상향대각선, 우하향대각선 각각 저장
			row = new boolean[N];
			col = new boolean[N];
			rightUp = new boolean[2 * N - 1]; // 대각선 안에서 가로 + 세로 일정. 0+0 ~ 2N-2;
			rightDown = new boolean[2 * N - 1]; // 가로 - 세로 일정. (0 - N+1) ~ (N-1 - 0) -> N-1씩 더해줘서 0~2*N-2로 보정

			answer = 0;
			dfs(0);

			System.out.println("#" + test_case + " " + answer);
		}
	}

	// 몇번째 row까지 검사했는지를 넘김
	static void dfs(int rowCnt) {
		if (rowCnt == N) {
			answer++;
			return;
		}
		row[rowCnt] = true;
		for (int j = 0; j < N; j++) {
			if (col[j] || rightUp[rowCnt + j] || rightDown[rowCnt - j + N - 1])
				continue;
			col[j] = rightUp[rowCnt + j] = rightDown[rowCnt - j + N - 1] = true;
			dfs(rowCnt + 1);
			col[j] = rightUp[rowCnt + j] = rightDown[rowCnt - j + N - 1] = false;
		}
	}

}