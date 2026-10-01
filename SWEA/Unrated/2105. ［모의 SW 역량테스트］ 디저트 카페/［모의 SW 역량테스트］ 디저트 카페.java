
import java.util.*;
import java.io.*;

public class Solution {
	static int N, MAX;
	static int answer;
	static boolean foundMax;

	static int[][] map;
	static int[] moveCnt;
	static boolean[] visited;

	// 좌하, 우하, 우상, 좌상
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { -1, 1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			MAX = (N - 1) * (N - 2);
			visited = new boolean[N * N + 1];
			answer = 0;
			foundMax = false;
			map = new int[N][N];

			// 각 방향별로 몇번이동했는지 저장
			moveCnt = new int[4];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {
					moveCnt[0] = 0;
					moveCnt[1] = 0;
					moveCnt[2] = 0;
					moveCnt[3] = 0;
					dessert(i, j, 0);
					if (foundMax)
						break;
				}
				if (foundMax)
					break;
			}

			sb.append("#").append(tc).append(" ");
			sb.append(answer == 0 ? -1 : answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dessert(int r, int c, int sum) {
		if (moveCnt[3] != 0 && moveCnt[1] == moveCnt[3]) {
			if (sum == MAX)
				foundMax = true;
			answer = Math.max(answer, sum);
			return;
		}

		if (moveCnt[3] != 0) {
			// 좌상으로 이동중이었다면 무조건 우하 이동한 만큼 가야해
			int nr = r + dr[3];
			int nc = c + dc[3];
			if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
				visited[map[nr][nc]] = true;
				moveCnt[3]++;
				dessert(nr, nc, sum + 1);
				if (foundMax)
					return;
				moveCnt[3]--;
				visited[map[nr][nc]] = false;
			}
		} else if (moveCnt[2] != 0) { // 우상으로 이동중이었다면
			int nr, nc;
			// 무조건 우상으로 이동
			if (moveCnt[2] < moveCnt[0]) {
				nr = r + dr[2];
				nc = c + dc[2];
				if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
					visited[map[nr][nc]] = true;
					moveCnt[2]++;
					dessert(nr, nc, sum + 1);
					if (foundMax)
						return;
					moveCnt[2]--;
					visited[map[nr][nc]] = false;
				}
			} else if (moveCnt[2] == moveCnt[0]) { // 같아지면 좌상으로 이동하기 시작
				nr = r + dr[3];
				nc = c + dc[3];
				if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
					visited[map[nr][nc]] = true;
					moveCnt[3]++;
					dessert(nr, nc, sum + 1);
					if (foundMax)
						return;
					moveCnt[3]--;
					visited[map[nr][nc]] = false;
				}
			}
		} else if (moveCnt[1] != 0) { // 우하로 이동중이었다면 분기처리
			// 계속 우하로
			int nr = r + dr[1];
			int nc = c + dc[1];
			if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
				visited[map[nr][nc]] = true;
				moveCnt[1]++;
				dessert(nr, nc, sum + 1);
				if (foundMax)
					return;
				moveCnt[1]--;
				visited[map[nr][nc]] = false;
			}

			// 우상으로 틀기
			nr = r + dr[2];
			nc = c + dc[2];
			if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
				visited[map[nr][nc]] = true;
				moveCnt[2]++;
				dessert(nr, nc, sum + 1);
				if (foundMax)
					return;
				moveCnt[2]--;
				visited[map[nr][nc]] = false;
			}
		} else { // 좌하로 이동중이었다면 분기처리
			// 계속 좌하로
			int nr = r + dr[0];
			int nc = c + dc[0];
			if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
				visited[map[nr][nc]] = true;
				moveCnt[0]++;
				dessert(nr, nc, sum + 1);
				if (foundMax)
					return;
				moveCnt[0]--;
				visited[map[nr][nc]] = false;
			}

			// 우하로 틀기
			nr = r + dr[1];
			nc = c + dc[1];
			if (!(nr < 0 || nr >= N || nc < 0 || nc >= N) && !visited[map[nr][nc]]) {
				visited[map[nr][nc]] = true;
				moveCnt[1]++;
				dessert(nr, nc, sum + 1);
				if (foundMax)
					return;
				moveCnt[1]--;
				visited[map[nr][nc]] = false;
			}
		}
	}


}
