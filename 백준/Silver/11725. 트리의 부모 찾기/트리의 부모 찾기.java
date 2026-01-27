import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph;
        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        boolean[] visited = new boolean[N + 1];
        int[] parent = new int[N + 1];
        bfs(graph, 1, visited, parent);

    }

    private static void bfs(ArrayList<Integer>[] graph, int i, boolean[] visited, int[] parent) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        visited[i] = true;
        while (!q.isEmpty()) {
            int n = q.poll();
            for (int node : graph[n]) {
                if (!visited[node]) {
                    visited[node] = true;
                    q.offer(node);
                    parent[node] = n;
                }
            }
        }
        for (int j = 2; j < N+1; j++) {
            sb.append(parent[j]).append("\n");
        }
        System.out.println(sb);
    }
}