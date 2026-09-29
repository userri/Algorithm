import java.util.*;
import java.io.*;

class Solution {
	static int M, A, total;
	static int[] moveA, moveB, power;

	static int[][] map = new int[10][10];

	// 이동하지않음, 상우하좌
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	public static void main(String args[]) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bfr.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(bfr.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			moveA = new int[M];
			moveB = new int[M];
			power = new int[A + 1];
			total = 0;

			for (int i = 0; i < 10; i++)
				Arrays.fill(map[i], 0);

			st = new StringTokenizer(bfr.readLine());
			for (int i = 0; i < M; i++)
				moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bfr.readLine());
			for (int i = 0; i < M; i++)
				moveB[i] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(bfr.readLine());
				int c = Integer.parseInt(st.nextToken()) - 1; // x -> c
				int r = Integer.parseInt(st.nextToken()) - 1; // y -> r
				int range = Integer.parseInt(st.nextToken());
				power[i] = Integer.parseInt(st.nextToken());

				fillMap(r, c, range, i);
			}

			int ar = 0, ac = 0, br = 9, bc = 9;
			// 움직이기 전 충전
			charge(ar, ac, br, bc);
			// 시간 지나면서 충전
			for (int i = 0; i < M; i++) {
				ar += dr[moveA[i]];
				ac += dc[moveA[i]];
				br += dr[moveB[i]];
				bc += dc[moveB[i]];

				charge(ar, ac, br, bc);
			}

			sb.append("#").append(test_case).append(" ").append(total);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void fillMap(int r, int c, int range, int bcIdx) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (Math.abs(r - i) + Math.abs(c - j) <= range)
					map[i][j] |= (1 << bcIdx);
			}
		}
	}

	static void charge(int ar, int ac, int br, int bc) {
		int maxSum = 0;
		// A와 B가 어떤 BC에도 들어가지 못했을 때(i = A, j = A)를 포함해서 순회
		for (int i = 0; i <= A; i++) {
			for (int j = 0; j <= A; j++) {
				int sum = 0;
				int aPower = ((map[ar][ac] & (1 << i)) != 0) ? power[i] : 0;
				int bPower = ((map[br][bc] & (1 << j)) != 0) ? power[j] : 0;

				if (i == j) {
					sum = aPower;
				} else {
					sum = aPower + bPower;
				}

				maxSum = Math.max(maxSum, sum);
			}
		}
		total += maxSum;
	}

}