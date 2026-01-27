import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (ArrayList<Integer> a : graph) {
            Collections.sort(a);
        }

        boolean[] visited1 = new boolean[N + 1];
        boolean[] visited2 = new boolean[N + 1];

        dfs(graph, V, visited1);
        System.out.println();
        bfs(graph, V, visited2);

    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int v, boolean[] visited1) {
        visited1[v] = true;
        System.out.print(v + " ");
        for (int n : graph.get(v)) {
            if (!visited1[n]) {
                dfs(graph,n,visited1);
            }
        }
    }

    private static void bfs(ArrayList<ArrayList<Integer>> graph, int v, boolean[] visited2) {
        Queue<Integer> q = new LinkedList<>();
        visited2[v] = true;
        q.offer(v);
        while (!q.isEmpty()) {
            int n = q.poll();
            System.out.print(n + " ");
            for (int node : graph.get(n)) {
                if (!visited2[node]) {
                    visited2[node] = true;
                    q.offer(node);
                }
            }
        }
    }


}