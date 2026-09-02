

import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 상하좌우
		int[] drow = { -1, 1, 0, 0 };
		int[] dcol = { 0, 0, -1, 1 };

		for (int test_case = 1; test_case <= 10; test_case++) {
			String t = br.readLine();

			int[][] map = new int[16][16];
			for (int i = 0; i < 16; i++) {
				String line = br.readLine();
				for (int j = 0; j < 16; j++) {
					map[i][j] = (int) (line.charAt(j) - '0');
				}
			}

			boolean[][] visited = new boolean[16][16];
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] { 1, 1 });
			visited[1][1] = true;

			int result = 0;

			while (!q.isEmpty()) {
				int[] cur = q.poll();
				if (map[cur[0]][cur[1]] == 3) {
					result = 1;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + drow[i];
					int nc = cur[1] + dcol[i];
					if (nr < 0 || nr >= 16 || nc < 0 || nc >= 16)
						continue;
					if (map[nr][nc] == 1) 
						continue;
					if (visited[nr][nc])
						continue;
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}