import java.util.*;
import java.io.*;
public class Main {
    static List<List<int[]>> list;
    static boolean[] visited;
    static int maxNode = 1;
    static int maxDist = 0;
    public static void main(String[] args) throws IOException {
        list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        

        for(int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new int[]{v, w});
            list.get(v).add(new int[]{u, w});
            maxDist = Math.max(w, maxDist);
        }
        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1, 1, 0);


        int start = maxNode;
        visited = new boolean[n+1];
        visited[start] = true;
        dfs(start, start, 0);

        System.out.println(maxDist);
    }
    static void dfs(int node, int middle, int accum) {
        for(int[] next: list.get(middle)) {
            if(visited[next[0]]) continue;
            visited[next[0]] = true;
            
            if(maxDist < accum + next[1]) {
                maxDist = accum + next[1];
                maxNode = next[0];
            }
            dfs(node, next[0], accum + next[1]);
        }
    }
}