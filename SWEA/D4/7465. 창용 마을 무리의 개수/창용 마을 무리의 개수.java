
import java.util.*;
import java.io.*;

class Solution {
	static List<List<Integer>> graph;
	static int N, M, answer;
	static boolean[] visited;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			visited = new boolean[N + 1];
			answer = 0;

			for (int i = 0; i <= N; i++)
				graph.add(new ArrayList<>());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int u = Integer.parseInt(st.nextToken());
				graph.get(v).add(u);
				graph.get(u).add(v);
			}
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					dfs(i);
					answer++;
				}
			}

			sb.append("#").append(test_case).append(" ").append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int v) {
		for (int next : graph.get(v)) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
	}

}
