
/*
 * 규영이와 인영이의 카드게임
 * 1~18 카드
 * 9장씩
 * 한라운드:한장씩 내
 * 높은수가 적힌 카드 낸 사람이 카드합만큼 점수얻어
 * 아홉라운드 끝냇을때 총점 높은 사람이 승자
 * 
 * 규영이 카드 9개 주어짐
 * 규영이가 이기는 경우, 지는 경우 총 몇가지인지 구해라
 * 
 * 
 * 구현
 * 일단 테케를 보면 합쳐서 36만 이내니까 완탐 dfs도 괜찮을것같기도 해
 * 그리고 이긴쪽이 가져간 카드를 합치면 모든 카드의 합이 나올 테니
 * 한쪽의 카드합만 계산해도 될듯 해
 * 
 */
import java.util.*;
import java.io.*;

class Solution {

	static int[] gyu = new int[9];
	static int[] inyoung = new int[9];
	static boolean[] isSelected = new boolean[9];
	static int gyuWin, gyuLose;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(gyu);
			int inIdx = 0;
			int gIdx = 0;
			for (int i = 1; i <= 18; i++) {
				if (gIdx < 9 && gyu[gIdx] == i) {
					gIdx++;
					continue;
				}
				inyoung[inIdx++] = i;
			}
			// System.out.println(Arrays.toString(gyu));
			// System.out.println(Arrays.toString(inyoung));

			gyuWin = 0;
			gyuLose = 0;
			permutation(0, 0, 0);

			System.out.println("#" + test_case + " " + gyuWin + " " + gyuLose);
		}
	}

	// 인영카드에서 검사한 개수
	static void permutation(int cnt, int gyuSum, int inSum) {
		if (cnt == 9) {
			if(gyuSum > inSum) {
				gyuWin++;
			}
			else if(gyuSum < inSum) {
				gyuLose++;
			}
			return;
		}
		// cnt번째 라운드에서 인영의 i번째 카드를 포함시킴
		for(int i = 0; i < 9; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			int sumDiff = gyu[cnt] + inyoung[i];
			if(gyu[cnt] < inyoung[i]) permutation(cnt+1, gyuSum, inSum + sumDiff);
			if(gyu[cnt] > inyoung[i]) permutation(cnt+1, gyuSum+ sumDiff, inSum);
			isSelected[i] = false;
		}
	}

}