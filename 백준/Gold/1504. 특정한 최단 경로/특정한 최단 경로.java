import java.util.*;
import java.io.*;
// 백트래킹으로 풀자~
class Main {
    static List<List<int[]>> graph;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        visited = new boolean[N+1];
        for(int i = 0;i < N+1;i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0;i < E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b,c});
            graph.get(b).add(new int[]{a,c});
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int middleDist = bfs(v1,v2);

        // System.out.println("-------");
        // 1번부터 v1까지
        int dist1 = bfs(1, v1);
        
        // v2부터 N까지
        int dist2 = bfs(v2, N);
        
        // 1부터 v2까지
        int dist3 = bfs(1, v2);
        
        // v1부터 N까지
        int dist4 = bfs(v1, N);
        
        int sum1 = (dist1 == -1 || dist2 == -1)?-1:dist1 + middleDist + dist2;
        int sum2 = (dist3 == -1 || dist4 == -1)?-1:dist3 + middleDist + dist4;
        // System.out.println(dist1+", "+dist2+", "+dist3+", "+dist4 + " with " + middleDist);
        if(sum1 <=0 && sum2 <=0) {
            System.out.println(-1);
        } 
        else if(sum1 <=0) System.out.println(sum2);
        else if(sum2 <=0) System.out.println(sum1);
        else System.out.println(Math.min(sum1, sum2));
        
    }
    static int bfs(int start, int target) {
        Deque<Point> q = new ArrayDeque<>();
        int[] visited = new int[N+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;
        q.offer(new Point(start, 0));
        int totalWeight = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            if(cur.end == target) {
                visited[target] = Math.min(visited[target], cur.dist);
            }
            // 이미 더 작게 초기화된적있으면 건너뛰기
            if(visited[cur.end] < cur.dist) continue;

            // System.out.println(cur.end+" 방문");
            for(int[] node: graph.get(cur.end)) {
                if(visited[node[0]] > cur.dist + node[1]) {
                    visited[node[0]] = cur.dist + node[1];
                    q.offer(new Point(node[0], visited[node[0]]));
                }
            }
        }
        return visited[target] == Integer.MAX_VALUE?-1:visited[target];
    }
}
class Point {
    int end, dist;
    public Point(int end, int dist) {
        this.end = end;
        this.dist = dist;
    }
}
