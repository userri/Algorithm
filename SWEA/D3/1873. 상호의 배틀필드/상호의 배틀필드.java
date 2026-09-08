
/*
 * 상호의 배틀필드
 * 맵 높이 H, 너비 W: 2~20
 * H개의 줄에 길이 W인 문자열
 * 사용자가 넣을 입력의 개수 N
 * 길이가 N인 문자열
 * 
 * */
import java.util.*;
import java.io.*;

class Solution {

	static char[][] map;
	static char[] user;
	static int h, w;

	// 상하좌우
	static int[] drow = { -1, 1, 0, 0 };
	static int[] dcol = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h][w];

			int stRow = 0, stCol = 0;
			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					char c = map[i][j];
					if (c == '<' || c == '>' || c == 'v' || c == '^') {
						stRow = i;
						stCol = j;
					}
				}
			}

			int n = Integer.parseInt(br.readLine());
			user = new char[n];
			String userLine = br.readLine();
			for (int i = 0; i < n; i++) {
				user[i] = userLine.charAt(i);
			}

			tank(stRow, stCol);

			System.out.print("#" + test_case + " ");
			for (char[] line : map) {
				for (char c : line) {
					System.out.print(c);
				}
				System.out.println();
			}
		}
	}

	static void tank(int stRow, int stCol) {
		int curRow = stRow, curCol = stCol;
		Map<Character, Integer> dirMap = new HashMap<>();
		dirMap.put('U', 0);
		dirMap.put('D', 1);
		dirMap.put('L', 2);
		dirMap.put('R', 3);
		dirMap.put('S', 4);

		Map<Character, Integer> curToDir = new HashMap<>();
		curToDir.put('^', 0);
		curToDir.put('v', 1);
		curToDir.put('<', 2);
		curToDir.put('>', 3);
		char[] dirToCur = new char[] { '^', 'v', '<', '>' };

		for (int i = 0; i < user.length; i++) {
			if (dirMap.get(user[i]) != 4) {
				int idx = dirMap.get(user[i]);
				int nr = curRow + drow[idx];
				int nc = curCol + dcol[idx];
				// 범위 넘어가더라도 현재위치의 탱크방향은 바꿔놓기
				map[curRow][curCol] = dirToCur[idx];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
					continue;
				}
				// 평지라면 이동
				if (map[nr][nc] == '.') {
					// 이전 위치를 평지로 만들어놓고 이동
					map[curRow][curCol] = '.';
					map[nr][nc] = dirToCur[idx];
					curRow = nr;
					curCol = nc;
				}
			} else {
				// 현재방향으로 포탄을 발사
				// 벽 만날때까지 직진. 벽돌이면 벽을 평지로 만들고, 강철벽이면 그냥 멈춤
				int dirIdx = curToDir.get(map[curRow][curCol]);
				int nr = curRow;
				int nc = curCol;
				while (true) {
					nr += drow[dirIdx];
					nc += dcol[dirIdx];
					if (nr < 0 || nr >= h || nc < 0 || nc >= w)
						break;
					if (map[nr][nc] == '*') {
						map[nr][nc] = '.';
						break;
					} else if (map[nr][nc] == '#') {
						break;
					}
				}
			}
		}
	}
}
