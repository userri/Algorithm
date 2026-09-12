import java.util.*;
import java.io.*;
public class Main {
    static List<List<Integer>> list;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        list = new ArrayList<>();
        parent = new int[100_001];
        for(int i = 0; i < 100_000; i++) list.add(new ArrayList<>());


        int n = Integer.parseInt(br.readLine());
        for (int t = 0; t < n - 1; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }
        dfs(1);
        for(int i = 2; i <= n; i++)
            System.out.println(parent[i]);
    }
    static void dfs(int node) {
        for(int next: list.get(node)) {
            if(parent[next] != 0) continue;
            parent[next] = node;
            dfs(next);
        }
    }
}