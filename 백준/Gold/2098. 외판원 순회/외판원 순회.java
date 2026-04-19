import java.util.*;
import java.io.*;
class Main {
    static int N, fullVisitBit, INF = 987654321;
    static int[][] w, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        w = new int[N][N];
        fullVisitBit = (1 << N) - 1;
        // dp[i][j] : 현재 i번째 도시에 있고, j번째 상태(돌아다닌 도시들)일 때,
        // 나머지 도시를 모두 도는 경우 가장 작은 수를 저장
        // j가 작을수록 경우의 수가 더 많음
        dp = new int[N][fullVisitBit];

        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0번째 도시에서 시작하고 상태도 저장(1 << 0)
        System.out.println(tsp(0, 1));
    }
    static int tsp(int x, int status) {
        if(status == fullVisitBit) {
            // 원형 순환이므로 임의로 0번으로 도착하게 해
            // 근데 경로가 없다면 무효화(큰 수 리턴)
            if(w[x][0] == 0) return INF;
            return w[x][0];
        }

        // 이미 초기화된적 있다면 그 수를 리턴
        if(dp[x][status] != -1) return dp[x][status];

        // 방문중임을 표시
        dp[x][status] = INF;

        // 탐색
        for(int i = 0; i < N; i++) {
            // next: i번째 도시 방문
            int next = status | (1 << i);

            // 경로가 없거나 이미 방문한 도시면 continue
            if(w[x][i] == 0 || ((status & (1 << i)) != 0)) continue;
            dp[x][status] = Math.min(dp[x][status], w[x][i] + tsp(i, next));
        }

        
        return dp[x][status];
    }
}