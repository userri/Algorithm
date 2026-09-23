

import java.util.*;
import java.io.*;

class Solution {
	static char[][] maze;
	static boolean[][] visited;
	static boolean arrived;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			maze = new char[100][100];
			visited = new boolean[100][100];
			arrived = false;

			for (int i = 0; i < 100; i++) {
				maze[i] = br.readLine().toCharArray();
			}
			dfs(1, 1);

			System.out.println("#" + test_case + " " + (arrived ? 1 : 0));
		}
	}
	static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			if (arrived)
				return;
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= 100 || nc < 0 || nc >= 100)
				continue;
			if (visited[nr][nc])
				continue;
			if (maze[nr][nc] == '3') {
				arrived = true;
				return;
			}
			if (maze[nr][nc] == '1')
				continue;
			visited[nr][nc] = true;
			dfs(nr, nc);
			visited[nr][nc] = false;
		}
	}

}