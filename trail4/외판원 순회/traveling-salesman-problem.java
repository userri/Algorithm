

import java.util.*;
import java.io.*;
public class Main {
    static List<List<int[]>> graph;
    static int N, FULL_BIT;
    static int INF = 978654321;
    static int[][] minCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // 0번째 ~ N-1번째 모두 방문한 상태의 비트
        FULL_BIT = (1 << N) - 1;

        // minCost[a][b] : 직전에 a를 방문했고 현재방문상태비트가 b일 때, 나머지를 다 방문하기 위한 최소 비용을 저장
        minCost = new int[N][1 << N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(minCost[i], -1); // 최대 누적 cost: (N 최대 10) * (최대 간선비용 10000)
        }

        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if(cost == 0) continue;
                graph.get(i).add(new int[]{j, cost});
            }
        }

        // 0번(1번째 지점) 방문을 시작으로 status: 1(2진수)(1 << 0)을 인자로 넘김
        tsp(0, 1);
        
        System.out.println(minCost[0][1]);
    }

    // cur까지 방문했을 때의 상태, 누적값을 전달
    static int tsp(int cur, int status) {
        if(status == FULL_BIT) {
            int lastCost = -1;
            for(int[] next: graph.get(cur)) {
                if(next[0] == 0) {
                    lastCost = next[1];
                    break;
                }
            }
            // 시작지점으로 돌아갈 수 없으므로 INF 리턴(경로 무효화)
            if(lastCost == -1) {
                return INF;
            }
            return lastCost;
        }

        // 이미 초기화된 적 있으면 리턴
        if(minCost[cur][status] != -1) {
            return minCost[cur][status];
        }
        minCost[cur][status] = INF;

        for(int[] next: graph.get(cur)) {
            if((status & (1 << next[0])) != 0) continue; // i번째 도시를 방문한적 있으면 건너뛰기
            // 기존값 vs 현재 next 를 방문하는 경로 중 더 작은 값으로 업데이트
            minCost[cur][status] = Math.min(minCost[cur][status], next[1] + tsp(next[0], (status | (1 << next[0]))));
        }

        return minCost[cur][status];
    }
}