/*

*/
import java.util.*;
import java.io.*;
public class Main {
    static List<List<Integer>> tree;
    static int[] dist;
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) tree.add(new ArrayList<>());

        dist = new int[n+1];
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        dfs(1, 0);
        int sum = 0;
        for(int i = 1; i < n+1; i++) {
            if(tree.get(i).size() == 0) sum += dist[i];
        }
        
        // 짝수면 A 차례됐을때 말 없어서 A가 짐
        System.out.println(sum % 2 != 0 ? 1 : 0);
    }
    static void dfs(int parent, int accum) {
        for(int next: tree.get(parent)) {
            if(next == 1) continue;
            if(dist[next] != 0) continue;
            tree.get(next).remove(Integer.valueOf(parent));
            dist[next] = accum + 1;
            dfs(next, accum + 1);
        }
    }
}