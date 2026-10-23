
/*
 * 큐에 넣기만 하고 아직 방문하지 않은 집을 카운트에 포함시켜서 헤맴
 */

import java.util.*;
import java.io.*;

class Solution {
	static int N, M, ANSWER;
	static int[][] city;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			ANSWER = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			city = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			sb.append("#").append(test_case).append(" ").append(ANSWER);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c, 1 }); // 거리를 함께 저장(k 결정)
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		int homeCnt = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (city[cur[0]][cur[1]] == 1) {
				homeCnt++;
			}

			int curBenefit = homeCnt * M;
			int cost = cur[2] * cur[2] + (cur[2] - 1) * (cur[2] - 1);

			if (curBenefit - cost >= 0) {
				ANSWER = Math.max(ANSWER, homeCnt);
			}

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				q.offer(new int[] { nr, nc, cur[2] + 1 });
			}
		}
	}

}