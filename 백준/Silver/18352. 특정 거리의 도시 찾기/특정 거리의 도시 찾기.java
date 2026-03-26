import java.util.*;
import java.io.*;
class Main {
    static List<List<Integer>> graph;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0;i < N+1;i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0;i < M ;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dijkstra(X);
        int cnt = 0;
        for(int i = 1;i <=N;i++) {
            if(dist[i] == K) {
                cnt++;
                System.out.println(i);
            }
        }
        if(cnt==0) System.out.println(-1);
    }
    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> o1.dist - o2.dist);
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;
            // 이미 거리가 더 작게 업데이트된 적 있으면 건너뛰기
            if(dist[curNode] < curDist) continue;
            for(int neighbor: graph.get(curNode)) {
                if(dist[neighbor] > dist[curNode] + 1) {
                    dist[neighbor] = dist[curNode] + 1;
                    pq.offer(new Node(neighbor, dist[neighbor]));
                }
            }
        }
    }
}
class Node {
    int node, dist;
    public Node(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}