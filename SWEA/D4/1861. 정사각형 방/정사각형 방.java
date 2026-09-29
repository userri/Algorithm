
import java.util.*;
import java.io.*;

class Solution {
	static int[][] rooms;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, curMove, maxMove;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			int[][] numToRoom = new int[N * N + 1][2]; // i번째 방의 행과 열을 저장
			curMove = 1;
			maxMove = 1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
					numToRoom[rooms[i][j]][0] = i;
					numToRoom[rooms[i][j]][1] = j;
				}
			}
			int minStart = 1;
			int start = 1;

			for (int room = 1; room <= N * N; room++) {
				boolean find = false;
				int r = numToRoom[room][0];
				int c = numToRoom[room][1];
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (rooms[nr][nc] == room + 1) {
						find = true;
						break;
					}
				}
				if (find) {
					curMove++;
					if (maxMove < curMove) {
						minStart = start;
						maxMove = curMove;
					}
				} else {
					curMove = 1;
					start = room + 1;
				}
			}

			sb.append("#").append(test_case).append(" ").append(minStart).append(" ").append(maxMove);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}