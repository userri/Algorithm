

/*
b형이랑 비슷한듯
목표위치에 정확하게 멈추기 위해 최소 몇 번의 이동이 필요한지
게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것을 한번의 이동으로 정의

<제한>
board 길이 3~ 100
board원소길이 3~100

<구현>
bfs처럼 가고 visited를 표시하되, 진행방향의 끝까지 가야 해
*/
import java.util.*;

class Solution {
	int N, M;

	char[][] grid;

	// 상하좌우
	int[] drow = { -1, 1, 0, 0 };
	int[] dcol = { 0, 0, -1, 1 };
	int gRow, gCol;
	boolean[][] visited;

	int answer = -1;

	public int solution(String[] board) {
		N = board.length;
		M = board[0].length();
		grid = new char[N][M];
		visited = new boolean[N][M];

		int stRow = 0;
		int stCol = 0;
		gRow = 0;
		gCol = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = board[i].charAt(j);
				if (grid[i][j] == 'R') {
					stRow = i;
					stCol = j;
				} else if (grid[i][j] == 'G') {
					gRow = i;
					gCol = j;
				}
			}
		}

		bfs(stRow, stCol);

		return answer;
	}

	void bfs(int curRow, int curCol) {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { curRow, curCol, 0 });
		visited[curRow][curCol] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0];
				int nc = cur[1];
				int tr = nr, tc = nc;
				// 각 방향으로 끝까지 가
				while (true) {
					tr += drow[i];
					tc += dcol[i];
					if (tr < 0 || tr >= N || tc < 0 || tc >= M) {
						break;
					}
					if (grid[tr][tc] == 'D') {
						break;
					}
					nr = tr;
					nc = tc;
				}
				if (visited[nr][nc]) {
					continue;
				}
				if (grid[nr][nc] == 'G') {
					answer = cur[2] + 1;
					return;
				}
				q.offer(new int[] { nr, nc, cur[2] + 1 });
				
				visited[nr][nc] = true;
			}
		}
	}
}