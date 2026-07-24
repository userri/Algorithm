/* 1번 마을에 있는 음식점에서 각 마을로 음식 배달
N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려고 함
음식 주문을 받을 수 있는 마을의 개수를 return
그러면 마을별 배달 경로가 여러개이되, 고정됨
visited?
다익스트라라는 유형을 봐버리긴 했는데...
dfs로 가야겟군
들어갈때 체크하고 나올떄 체크빼고 흠
;;;
dfs 하면서 각 마을까지 가는 최단 거리를 배열에 저장해놓기

*/
import java.util.*;
class Solution {
    List<List<int[]>> graph = new ArrayList<>();
    int[] minTimes;
    boolean[] visited;
    int N;
    public int solution(int N, int[][] road, int K) {
        // 마을 별 최소 시간 저장
        minTimes = new int[N+1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        // 방문 체크
        visited = new boolean[N+1];
        
        this.N = N;
        
        for(int i = 0; i <= N+1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            graph.get(a).add(new int[]{b,c}); // 양방향 연결
            graph.get(b).add(new int[]{a,c});
        }
        
        // 1번 마을에서 출발
        visited[1] = true;
        dfs(1,0);
        // System.out.println(Arrays.toString(minTimes));
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(minTimes[i] <= K) answer++;
        }
        return answer;
        
    }
    void dfs(int a, int sum) {
        // System.out.println(a + " 방문, 합계: " + sum); 
        // 이미 더 적은비용으로 초기화된적있으면 진행하지 않음
        if(minTimes[a] < sum) {
            // System.out.println(minTimes[a]+" < "+sum+" : 리턴");
            return;
        } else {
            minTimes[a] = sum;
        }
        
        for(int[] bc: graph.get(a)) {
            int b = bc[0];
            int c = bc[1];
            // 방문한적있으면 건너뛰기
            if(visited[b]) continue;
            visited[b] = true;
            // 합을 업데이트해서 같이 넘겨
            dfs(b, sum + c);
            visited[b] = false;
        }
        
    }
}