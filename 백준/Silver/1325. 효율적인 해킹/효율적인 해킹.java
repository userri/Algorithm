import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }

        int[] hackList = new int[N + 1];

        boolean[] visited = new boolean[N + 1];

        int maxHack = 0;
        for (int i = 1; i < N + 1; i++) {
            hackList[i] = dfs(graph, i, visited);
            maxHack = Math.max(maxHack, hackList[i]);
            visited = new boolean[N + 1];
            count = 0;
        }
        ArrayList<Integer> maxHackList = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            if (hackList[i] == maxHack) {
                maxHackList.add(i);
            }
        }
        Collections.sort(maxHackList);
        for (int i : maxHackList) {
            System.out.print(i + " ");
        }


    }

    private static Integer dfs(ArrayList<ArrayList<Integer>> graph, int v, boolean[] visited) {
        visited[v] = true;
        count++;
        for (Integer a : graph.get(v)) {
            if (!visited[a]) {
                visited[a] = true;
                dfs(graph, a, visited);
            }
        }
        return count;
    }

}