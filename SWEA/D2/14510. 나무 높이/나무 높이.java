
import java.util.*;
import java.io.*;

class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] trees = new int[N];

			st = new StringTokenizer(br.readLine());
			int max = 0;
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, trees[i]);
			}

			int oddCnt = 0, evenCnt = 0;
			for (int i = 0; i < N; i++) {
				int diff = max - trees[i];
				evenCnt += diff / 2;
				oddCnt += diff % 2;
			}
			while (evenCnt >= oddCnt + 2) {
				evenCnt--;
				oddCnt += 2;
			}

			int answer = Math.max(2 * oddCnt - 1, evenCnt * 2);

			sb.append("#").append(test_case).append(" ").append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}