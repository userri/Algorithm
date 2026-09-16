
/*
흠... 루트가 없어서 루트로부터의 두 점의 거리차를 구하긴 애매해
왜냐면 A - 기준점 - B 이런식으로 하면 A 까지의 거리와 B까지의 거리차를 구하는게 의미가 없으니까
그럼 일단 N*N 배열 만들어서 점부터 점까지의 거리 싹다구해 ㅋㅋ
1000개면 별로 많지도 않음


*/
import java.util.*;
import java.io.*;
public class Main {
    static List<List<int[]>> tree = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) tree.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree.get(u).add(new int[]{v, d});
            tree.get(v).add(new int[]{u, d});
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(u == v) {
                System.out.println(0);
                continue;
            }
            visited = new boolean[n+1];
            visited[u] = true;
            System.out.println(dfs(u, v, 0));
        }
        // Please write your code here.
    }
    // 목표에 도달한 경로와 도달못한 경로 재귀 어케함?
    static int dfs(int st, int end, int accum) {
        for(int[] next: tree.get(st)) {
            if(visited[next[0]]) continue;
            visited[next[0]] = true;
            if(next[0] == end) return accum + next[1];
            int nextDist = dfs(next[0], end, accum + next[1]);
            if(nextDist != -1) return nextDist;
        }
        return -1;
    }
}