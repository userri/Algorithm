import java.util.*;
import java.io.*;
public class Main {
    static List<List<Integer>> tree;
    static int deleteNode, leaf;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        tree = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        for(int i = 0; i < n; i++) tree.add(new ArrayList<>());
        st = new StringTokenizer(br.readLine());

        int root = -1;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) root = i;
            else tree.get(parent).add(i);
        }
        deleteNode = Integer.parseInt(br.readLine());
        if(deleteNode == root) {
            System.out.println(0);
            return;
        }
        
        for(int i = 0; i < n; i++) {
            if(tree.get(i).contains(deleteNode)) {
                tree.get(i).remove(Integer.valueOf(deleteNode));
                break;
            }
        }
        visited[root] = true;
        dfs(root);
        System.out.println(leaf);
    }
    static void dfs(int parent) {
        for(int next: tree.get(parent)) {
            if(visited[next]) continue;
            visited[next] = true;
            if(tree.get(next).size() == 0) leaf++;
            else dfs(next);
        }
    }
}