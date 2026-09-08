/*
 * 수제 버거 장인
 * 재료 1부터 N가지(1~20)
 * M가지의 쌍: i와 j는 같이 넣을 수 없다
 * M: 0~400
 * 1 <= i,j <= N // 서로다른숫자(대소관계 안정해짐)
 * 
 * */
import java.util.*;
import java.io.*;

class Solution {
	static boolean[] selected;
	static boolean[][] forbidden;
	static int n;
	static int result;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			selected = new boolean[n + 1];
			forbidden = new boolean[n + 1][n + 1];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				forbidden[a][b] = true;
				forbidden[b][a] = true;
			}
			result = 0;
			burger(1);

			System.out.println("#" + test_case + " " + result);
		}
	}

	private static void burger(int now) {
		if(now == n+1) {
			result++;
			return;
		}

		// 다음재료 넣느냐 안넣느냐 두가지
		// 안넣음
		burger(now+1);
		
		// 넣을 수 있는지 검사
		boolean possible = true;
		for(int i = 0; i < now; i++) {
			if(selected[i] && forbidden[i][now]) {
				possible = false;
				break;
			}
		}
		
		if(possible) {
			selected[now] = true;
			burger(now + 1);
			selected[now] = false;
		}
	}
}
