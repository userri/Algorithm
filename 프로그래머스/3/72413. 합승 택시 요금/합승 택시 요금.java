/*
유형 판단
간선이 있다 -> 다익스트라/mst/bfs/플로이드워셜 후보
간선비용이 다르다 -> bfs 탈락
시작 정점이 고정되어있지 않다 -> 플로이드 워셜! 다익스트라는 고정되어있어야 함. 전체를 하나로 묶는 게 아니라서 mst도 탈락

Q. 10 -> 1 -> 20 -> 11 이렇게 중간에 (1 -> 20 -> 11) 더 큰 경유지가 필요하면
    처음 경유지가 1인 경우를 검사하면서 10->1->11)을 계산할 때 정보가 부족하진 않은지? 
    나중에 10->1->20이랑 1->20->11이랑 잘 통합되는지?
A. "괜찮음"
왜냐면... 
case1) 10 -> 1 -> 20 -> 11 이면?
k=1일때: dist[10][20] = dist[10][1] + dist[1][20] 
k=20일때: dist[10][11] = dist[10][20](이미 최솟값) +dist[20][11]
case2) 10 -> 1 -> 20 -> 13 -> 11이면?
k = 1일 때 dist[10][20]
k = 13일 때 dist[20][11]
k = 20일 때 dist[10][11] = dist[10][20] + dist[20][11]

요약: k = 20이면 본인보다 작은 경유지를 거치는 경로는 이미 최솟값이 확정되어 있다

시간복잡도 계산 습관: for문 3번. 정점 V개 -> O(V^3)

*/

import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int[][] dist = new int[n+1][n+1];
        int INF = 199*100_000; // i -> j로 가기 위한 최대 간선 개수 * 최대 cost
        for(int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);
        for(int i = 1; i <= n; i++) dist[i][i] = 0; // 자기자신으로의 이동은 0
        
        // 주어진 요금 먼저 업데이트
        for(int[] fare: fares) {
            int st = fare[0], end = fare[1], cost = fare[2];
            dist[st][end] = Math.min(dist[st][end], cost);
            dist[end][st] = Math.min(dist[end][st], cost); // 복붙하면서 방향 바꾸는거 깜빡하지 않게 조심!
        }
        
        for(int k = 1; k <= n; k++) { // 경유지
            for(int i = 1; i <= n; i++) { // 시작지
                for(int j = 1; j <= n; j++) { // 도착지
                    // k 지점을 경유하는 게 더 비용이 적다면 업데이트
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int answer = INF;
        for(int k = 1; k <= n; k++) {
            // 원래 최소경로가 따로 있더라도 환승최소값이 되게 하기 위해서 경로가 달라지는 것이 모두 반영됨
            answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
        }
        return answer;
    }
}
