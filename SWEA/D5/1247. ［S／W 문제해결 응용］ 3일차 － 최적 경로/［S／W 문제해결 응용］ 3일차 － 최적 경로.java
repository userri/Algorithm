// n명의 고객을 방문하고 자신의 집에 돌아와
// 외판원순회
// 거리는 가로세로 거리합

// dp 배열 행/렬 크기를 100이 아닌 N으로 초기화하고
// -1로 초기화하는것도 N까지만 해서 한참 헤맴

import java.util.*;
import java.io.*;
class Solution {
    // dp[i][j][k] : 현재위치 i행 j열이고 현재 방문상태 k일 때 나머지를 다 도는데에 필요한 최소 cost(여기서는 방문거리)를 저장
    static int[][][] dp;
    static int homeRow, homeCol, N;
    static int INF = 987654321;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            dp = new int[101][101][1 << N];
            for(int i = 0; i < 101; i++) {
                for(int j = 0; j < 101; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            int compRow = Integer.parseInt(st.nextToken());
            int compCol = Integer.parseInt(st.nextToken());
            homeRow = Integer.parseInt(st.nextToken());
            homeCol = Integer.parseInt(st.nextToken());
            arr = new int[N][2];

            for(int i = 0; i < N; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            // 1<<0이면 첫번째 방문하고 시작하는거라서 아예 방문 안한상태(0)로 시작해야함
            System.out.println("#" + test_case + " " + tsp(compRow, compCol, 0));
            
        }
    }
    static int tsp(int curRow, int curCol, int visited) {
        // System.out.println(curRow +", "+curCol+", "+visited + "방문");
        // 1 << 0 부터 1 << (n-1)을 다 방문했으면 11...11 (N << 1 - 1)
        if(visited == ((1<<N) - 1)) {
            // 방문을 다 했다면
            // 목적지(집)로 가야해
            // 무조건 갈수있고 집까지의 가로세로 거리 더해주기
            return Math.abs(curRow - homeRow) + Math.abs(curCol - homeCol);
        }

        // System.out.println(curRow + ", " + curCol + ", " + visited);
        // 한번 방문했을 때 최소값으로 초기화되기 때문에 재방문할 필요 없음
        if(dp[curRow][curCol][visited] != -1) {
            return dp[curRow][curCol][visited];
        }
        dp[curRow][curCol][visited] = INF;

        for(int i = 0; i < N; i++) {
            // 이미 방문했으면 (+ 도달할수없으면-이문제는 해당사항 없음) 건너뛰기
            if((visited & (1 << i)) != 0) continue;
            int nrow = arr[i][0];
            int ncol = arr[i][1];
            int ndist = Math.abs(curRow - nrow) + Math.abs(curCol - ncol);
            // "지금" next를 방문하면 최소값이 업데이트 되냐 안되냐
            int before = dp[curRow][curCol][visited];
            dp[curRow][curCol][visited] = Math.min(dp[curRow][curCol][visited], ndist + tsp(nrow, ncol, visited | (1 << i)));         
            // System.out.println(before + " -> " + dp[curRow][curCol][visited]);
        }
        return dp[curRow][curCol][visited];        
    }
}