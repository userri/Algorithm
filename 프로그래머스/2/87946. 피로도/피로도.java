


import java.util.Arrays;

class Solution {
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[][] dungeons;
    static int k;
    static boolean check;
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        this.k = k;
        // 또 백트래킹...?

        int maxCnt = 0;
        visited = new boolean[dungeons.length];
        for(int i = dungeons.length;i >= 1;i--) {
            check = false;
            dfs(dungeons.length, i, 0);
            if(check) {
                maxCnt = i;
                break;
            }
        }

        return maxCnt;
    }
    static void dfs(int N, int M, int depth) {
        if(depth == M) {
            check = true;
        }
        for(int i = 0; i< N;i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                k -= dungeons[i][1]; // 피로도 깎기
                dfs(N, M, depth+1);
                k += dungeons[i][1]; // 피로도 다시 회복
                visited[i] = false;
            }
        }
    }
}