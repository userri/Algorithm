

import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			String[] arr = new String[n];
			int half = n / 2;
			for (int i = 0; i < n; i++)
				arr[i] = st.nextToken();

			String[] result = new String[n];
			if (n % 2 == 0) {
				for (int i = 0; i < n; i++) {
					if (i < n / 2)
						result[i * 2] = arr[i];
					else {
						result[(i - n / 2) * 2 + 1] = arr[i];
					}
				}
			} else {

				for (int i = 0; i < n; i++) {
					if (i <= n / 2)
						result[i * 2] = arr[i];
					else {
						result[(i - (n / 2 + 1)) * 2 + 1] = arr[i];
					}
				}
			}

			System.out.print("#" + test_case + " ");
			for (String r : result)
				System.out.print(r + " ");
			System.out.println();
		}
	}
}
