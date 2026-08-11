import java.util.*;
class Solution {
    static final int R=0,C=1,COST=2,DIR=3;
    int[][][] dp;
    // 상하좌우
    int[] drow = {-1,1,0,0};
    int[] dcol = {0,0,-1,1};
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        dp = new int[n][m][4];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
        
        // 우선순위큐에 행,열,비용,방향 저장
        // 비용 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[COST] - b[COST]);
        
        dp[0][0][1] = 0; pq.offer(new int[]{0,0,0,1});
        dp[0][0][3] = 0; pq.offer(new int[]{0,0,0,3});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[R];
            int c = cur[C];
            int cost = cur[COST];
            int dir = cur[DIR];
            if(dp[r][c][dir] < cost) continue;
            for(int nd = 0; nd < 4; nd++) {
                int nr = r + drow[nd];
                int nc = c + dcol[nd];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(board[nr][nc] == 1) continue;
                int ncost = cost + (nd == dir ? 100 : 600);
                if(ncost < dp[nr][nc][nd]) {
                    dp[nr][nc][nd] = ncost;
                    pq.offer(new int[]{nr,nc,ncost,nd});
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++)
            ans = Math.min(ans,dp[n-1][m-1][i]);
        return ans;
    }
}