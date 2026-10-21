import java.util.*;
import java.io.*;

class Solution {
	static List<List<Integer>> taller;
	static List<List<Integer>> smaller;

	static int N, M;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			taller = new ArrayList<>();
			smaller = new ArrayList<>();

			for (int i = 0; i <= N; i++)
				taller.add(new ArrayList<>());
			for (int i = 0; i <= N; i++)
				smaller.add(new ArrayList<>());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a가 작음
				taller.get(b).add(a);
				smaller.get(a).add(b);
			}
			int result = 0;

			for (int i = 1; i <= N; i++) {
				int cnt = bfs(i);
				if (cnt == N - 1)
					result++;
			}

			sb.append("#").append(test_case).append(" ").append(result);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(node);
		boolean[] visited = new boolean[N + 1];
		int cnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int tall : taller.get(cur)) {
				if (!visited[tall]) {
					visited[tall] = true;
					q.offer(tall);
					cnt++;
				}
			}
		}
		q.clear();
		q.offer(node);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int small : smaller.get(cur)) {
				if (!visited[small]) {
					visited[small] = true;
					q.offer(small);
					cnt++;
				}
			}
		}
		return cnt;
	}

}
