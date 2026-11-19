
import java.util.*;
import java.io.*;

class Solution {
	static int V, E;
	static int[][] nodes;
	static int[] parent;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			nodes = new int[E][3];
			parent = new int[V + 1];
			for (int i = 0; i <= V; i++)
				parent[i] = i;

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				nodes[i][0] = a;
				nodes[i][1] = b;
				nodes[i][2] = c;
			}
			Arrays.sort(nodes, (a, b) -> {
				if (a[2] != b[2])
					return a[2] - b[2];
				return 0;
			});

			long answer = 0;

			for (int i = 0; i < E; i++) {
				if (find(nodes[i][0]) != find(nodes[i][1])) {
					union(nodes[i][0], nodes[i][1]);
					answer += nodes[i][2];
				}
			}

			sb.append("#" + test_case + " ").append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		parent[aParent] = bParent;
	}

	static int find(int node) {
		if (parent[node] == node)
			return node;
		return parent[node] = find(parent[node]);
	}

}