
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int computerCount = Integer.parseInt(br.readLine());
        int pairs = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= computerCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < pairs; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[computerCount + 1];
        dfs(graph, 1, visited);
        System.out.println(count - 1);
    }

    static int count;

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        count++;
        for (int n : graph.get(node)) {
            if (!visited[n]) {
                dfs(graph, n, visited);
            }
        }
    }

}