// 새 마음 새 뜻 새 다익스트라
// 전형적인 다익스트라 문제!
import java.util.*;
class Solution {
    List<List<int[]>> graph = new ArrayList<>();
    int[] dist;
    int N;
    public int solution(int N, int[][] road, int K) {
        this.N = N;
        for(int i = 0; i <= N+1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < road.length; i++) {
            int start = road[i][0];
            int end = road[i][1];
            int cost = road[i][2];
            graph.get(start).add(new int[]{end, cost}); // 양방향 연결
            graph.get(end).add(new int[]{start, cost});
        }
        
        dijkstra();
        int answer = 0;
        // 경계주의: 0번마을제외
        for(int i = 1; i <= N; i++) {
            if(dist[i] <= K) answer++;
        }
        return answer;
    }
    void dijkstra() {
        // i번째 집까지의 최소거리를 저장
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        // {도착점, 비용} 저장. 비용 오름차순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{1,0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            // 근데 for문안에서 어차피 초기화될텐데 굳이? 아 넣는건 여러번 넣고 뺄때 또 검사?
            // 더 작은 값으로 초기화된 적 있으면 건너뛰기
            if(cost > dist[node]) continue;
            
            for(int[] next: graph.get(node)) {
                int nx = next[0];
                int nc = next[1];
                if(cost + nc < dist[nx]) {
                    pq.offer(new int[]{nx, cost + nc});
                    dist[nx] = cost + nc;
                }
            }
        }
            
    }
}