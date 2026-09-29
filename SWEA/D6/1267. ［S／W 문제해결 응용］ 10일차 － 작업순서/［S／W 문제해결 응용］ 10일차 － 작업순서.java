
import java.util.*;
import java.io.*;

class Solution {
	static int V, E;
	static List<List<Integer>> graph;
	static int[] parentCnt;
	static StringBuilder sb;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			graph = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			for (int i = 0; i <= V; i++)
				graph.add(new ArrayList<>());
			parentCnt = new int[V + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(start).add(end);
				parentCnt[end]++;
			}

			List<Integer> parentList = new ArrayList<>();
			for (int i = 1; i <= V; i++) {
				if (parentCnt[i] == 0)
					parentList.add(i);
			}

			sb.append("#").append(test_case).append(" ");
			for (int p : parentList) {
				bfs(p);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(node);
		sb.append(node).append(" ");
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph.get(cur)) {
				parentCnt[next] -= 1;
				// 더이상 진입점 없는 애만 큐에 넣어
				if (parentCnt[next] == 0) {
					q.offer(next);
					sb.append(next).append(" ");
				}
			}
		}
	}

}