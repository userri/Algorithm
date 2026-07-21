import java.util.*;
import java.io.*;

public class Solution {

    static int N, crow, ccol, hrow, hcol;
    static int[][] address; // 손님들 주소 저장
    static int[][][] dp; // 외판원순회 문제와 다르게 그냥 가로세로 길이가 이동거리이므로
    // cost 배열은 불필요
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            address = new int[N][2];
            dp = new int[101][101][(1 << N)]; // 현재위치 i, j 이고 방문상태 k일 때 나머지 고객을 모두 도는 데에 남은 거
            for(int i = 0; i < 101; i++) {
                for(int j = 0; j < 101; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            crow = Integer.parseInt(st.nextToken());
            ccol = Integer.parseInt(st.nextToken());
            hrow = Integer.parseInt(st.nextToken());
            hcol = Integer.parseInt(st.nextToken());
            for (int i = 0; i < N; i++) {
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                // i번째 고객의 행과 열을 address 배열에 저장
                address[i][0] = row;
                address[i][1] = col;
            }
            // 가로 + 세로가 200이니까 가로세로를 2^200으로 할리는 없잖아...?
            // 고객을 탐색해서 이동할때마다 이동거리를 계산하는게 맞는것같음
            System.out.println("#" + test_case + " " + tsp(crow, ccol, 0)); // 틀린이유: 1로 넣고 시작하면 첫번째 1<<0으로 0번째 손님을 방문했단 뜻. 0이어야함!
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // System.out.print(dp[i][j][0] + "," + dp[i][j][1] + " ");
                }
                // System.out.println();
            }
        }
    }

    // 현재 위치가 currow, currol 이고 방문상태가 visited일 때 나머지 집들을 모두 방문하는데에 걸리는 수
    static int tsp(int currow, int curcol, int visited) {
        // 모든 손님을 방문했다면
        if (visited == ((1 << N) - 1)) {
            // 외판원순회와 다르게, 갈수있는지 경로를 따지는 게 아니라 그냥 집까지의 가로세로 거리를 더함
            return Math.abs(currow - hrow) + Math.abs(curcol - hcol);
        }

        // 이미 방문한 경로면
        if (dp[currow][curcol][visited] != -1) {
            return dp[currow][curcol][visited];
        }
        dp[currow][curcol][visited] = INF;

        for (int next = 0; next < N; next++) {
            // 이미 방문했으면(외판원순회와 달리 경로없는 경우는 존재안함)
            if ((visited & (1 << next)) != 0) {
                continue;
            }
            // 현재에서 next까지 가는 이동거리 + next에서 나머지 도시 도는 최소거리를 계산.
            // 더 작으면 업데이트
            int nrow = address[next][0];
            int ncol = address[next][1];
            int ndist = Math.abs(currow - nrow) + Math.abs(curcol - ncol);
            dp[currow][curcol][visited] = Math.min(dp[currow][curcol][visited], ndist + tsp(nrow, ncol, (visited | (1 << next))));
        }
        return dp[currow][curcol][visited];
    }
}
