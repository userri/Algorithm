import java.util.*;
import java.io.*;
public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int totalCnt = 0;;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        visited = new boolean[10_001];
        int[] hasIn = new int[10_001];
        Arrays.fill(hasIn, -1);
        for(int i = 0; i < 10_001; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            if(hasIn[a] == -1) hasIn[a] = 0;
            if(hasIn[b] == -1) hasIn[b] = 1;
            else hasIn[b]++;
        }
        int rootCnt = 0;
        int root = 0;
        for(int i = 1; i < 10_001; i++) {
            if(hasIn[i] == 0) {
                rootCnt++; // 들어오는 간선 없는 노드개수가 두개이상이면 트리아님
                root = i;
            }
        }
        if(rootCnt != 1) {
            System.out.println(0);
            return;
        }
        
        totalCnt = 1;
        visited[root] = true;
        boolean isTree = dfs(root);
        System.out.println((isTree && totalCnt == m + 1 ) ? 1 : 0);
    }

    static boolean dfs(int parent) {
        for(int next: graph.get(parent)) {
            if(visited[next]) return false;
            visited[next] = true;
            totalCnt++;
            if(!dfs(next)) return false;
        }
        return true;
    }
}