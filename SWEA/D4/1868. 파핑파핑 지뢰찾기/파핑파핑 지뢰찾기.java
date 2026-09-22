

import java.util.*;
import java.io.*;

class Solution {
	static char[][] map;
	static int[][] cntMap;
	static boolean[][] visited;

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int N;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			cntMap = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// 각 칸별 주위에 몇개있는지 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*') {
						cntMap[i][j] = -1;
						continue;
					}
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if (map[nr][nc] == '*')
							cnt++;
					}
					cntMap[i][j] = cnt;
				}
			}

			int clickCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cntMap[i][j] == 0 && !visited[i][j]) {
						checkZero(i, j);
						clickCnt++;
					}
				}
			}
			
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 지뢰가 아니면서 방문안된칸은 하나씩 클릭해야함
					if(cntMap[i][j] != -1 && !visited[i][j]) {
						clickCnt++;
					}
				}
			}

			System.out.println("#" + test_case + " " + clickCnt);
		}
	}

	static void checkZero(int r, int c) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.offer(new int[] { r, c });
		// 지뢰 아닌 것 중 0과 그 주변 다 방문처리
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			// 방문처리하면서 0일때만 큐에 또 넣기
			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == '*') continue;
				visited[nr][nc] = true;
				if(cntMap[nr][nc] == 0) q.offer(new int[] {nr, nc}); 

			}
		}
	}

}