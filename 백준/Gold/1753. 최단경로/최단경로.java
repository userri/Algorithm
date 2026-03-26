import java.util.*;
import java.io.*;
class Main {
    static List<List<Node>> graph;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i = 0;i < V+1;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0;i < E;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }
        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(K);
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<=V;i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
        
    }
    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> o1.weight - o2.weight);
        pq.add(new Node(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.end;
            int curWeight = cur.weight;

            // 이미 더 작은 가중치로 초기화된적 있다면 건너뛰기
            if(dist[curNode] < curWeight) continue;

            for(Node neighbor: graph.get(curNode)) {
                // 지금 경로로 가중치 더한게 더 작다면 업데이트
                if(dist[neighbor.end] > dist[curNode] + neighbor.weight) {
                    dist[neighbor.end] = dist[curNode] + neighbor.weight;
                    pq.add(new Node(neighbor.end, dist[neighbor.end]));
                }
            }
        }
    }
}
class Node {
    int end, weight;
    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}