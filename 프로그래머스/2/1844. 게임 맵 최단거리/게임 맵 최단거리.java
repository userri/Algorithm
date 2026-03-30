

import java.util.*;

class Solution {
    int[][] dp;
    // 상하좌우
    int[] drow = {-1, 1, 0, 0};
    int[] dcol = {0, 0, -1, 1};
    int[][] maps;

    public int solution(int[][] maps) {
        this.maps = maps;

        int N = maps.length;
        int M = maps[0].length;
        dp = new int[N][M];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        bfs(0, 0, N, M);
        return dp[N - 1][M - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1][M - 1];
    }

    private void bfs(int row, int col, int N, int M) {
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(row, col, 1));
        boolean[][] visited = new boolean[N][M];
        visited[row][col] = true;
        dp[row][col] = 1;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nrow = cur.row + drow[i];
                int ncol = cur.col + dcol[i];
                if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= M) {
                    continue;
                }
                if (maps[nrow][ncol] == 0) {
                    continue;
                }
                if (visited[nrow][ncol]) {
                    continue;
                }
                // 이미 더 적은 수로 초기화된 적 있다면 넘어가
                if (dp[nrow][ncol] < cur.dist + 1) {
                    continue;
                }
                q.offer(new Point(nrow, ncol, cur.dist + 1));
                dp[nrow][ncol] = cur.dist + 1;
                visited[nrow][ncol] = true;
            }

        }
    }
}

class Point {
    int row;
    int col;
    int dist;

    public Point(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "[row:" + row + ", col:" + col + ", dist:" + dist + "]";
    }
}