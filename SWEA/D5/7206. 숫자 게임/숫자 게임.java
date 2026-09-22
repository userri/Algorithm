

import java.util.*;
import java.io.*;

class Solution {
	static Map<Integer, Integer> memo;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		memo = new HashMap<>();

		for (int test_case = 1; test_case <= T; test_case++) {
			String input = br.readLine();
			int ans = func(input);

			System.out.println("#" + test_case + " " + ans);
		}
	}

	static int func(String num) {
		int value = Integer.parseInt(num);
		if (value < 10)
			return 0;

		// 한 번 계산한 값은 다시 계산하지 않고 리턴
		if (memo.containsKey(value))
			return memo.get(value);

		int max = 0; // 최대 쪼갤 수 있는 값

		// N자리 숫자 -> 칸막이 최대 N-1개
		// 최대비트: (1 << N-1) -1
		for (int mask = 1; mask <= (1 << (num.length() - 1)) - 1; mask++) {
			int start = 0; // substring 시작위치
			int product = 1;

			// num.length()-1 개의 칸막이 검사
			for (int i = 0; i < num.length() - 1; i++) {
				if ((mask & (1 << i)) == 0)
					continue;
				int n = Integer.parseInt(num.substring(start, i + 1));
				product *= n;
				start = i + 1;
			}

			// 마지막 조각은 따로 곱해줘야 함
			int n = Integer.parseInt(num.substring(start));
			product *= n;

			max = Math.max(max, 1 + func(String.valueOf(product)));
		}
		memo.put(value, max);

		return max;
	}

}