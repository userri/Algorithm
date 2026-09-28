
import java.util.*;
import java.io.*;

class Solution {
	static int N, HALF, harvest;
	static int[][] farm;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			HALF = N / 2;
			farm = new int[N][N];
			visited = new boolean[N][N];
			harvest = 0;

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = line.charAt(j) - '0';
				}
			}

			bfs();
			sb.append("#" + test_case + " " + harvest);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { HALF, HALF, 0 });
		harvest = farm[HALF][HALF];
		visited[HALF][HALF] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[2] == HALF)
				continue;
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				int ndist = cur[2] + 1;
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (visited[nr][nc])
					continue;
				q.offer(new int[] { nr, nc, ndist });
				visited[nr][nc] = true;
				harvest += farm[nr][nc];
			}
		}
	}

}