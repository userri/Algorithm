
/*
 * 오른쪽으로 쭉 진행하면서 상승하는지 확인
 * 감소하기 시작하면 직전의 걔를 봉우리로 삼아
 * 오른쪽으로 진행해
 * 감소를 멈추면 이제 봉우리 개수 카운트(왼쪽개수 * 오른쪽 개수)
 * 
 * 엣지케이스: 감소한 적이 없다면?
 */

import java.util.*;
import java.io.*;

class Solution {
	static int[] tops;

	public static void main(String args[]) {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			tops = new int[N];
			for (int i = 0; i < N; i++)
				tops[i] = sc.nextInt();

			int cnt = 0;
			int l = 0, top = 0, r = 0;
			boolean onUp = false;
			for (int i = 1; i < N; i++) {
				// 이전부터 증가하는 중이 아니었다면(감소하다가 처음 증가라면) 왼쪽끝을 업데이트
				if (tops[i - 1] < tops[i]) {
					if (!onUp) {
						l = i - 1;
					}
					onUp = true;
					top = i; // 상승중에는 봉우리 계산시켜
				} else { // 문제조건: 증가 아니면 무조건 감소
					onUp = false;
					r = i; // 사실 필요없음
					if (l < top) {
						cnt += top - l;
					}
				}
//				System.out.println(l + ", " + top + ", " + r);
			}
//			System.out.println("-------");

			sb.append("#" + test_case + " " + cnt);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}