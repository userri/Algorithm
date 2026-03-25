

class Solution {
    private int maxCnt = 0;
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(0, k, dungeons, visited);

        return maxCnt;
    }

    private void dfs(int depth, int curK, int[][] dungeons, boolean[] visited) {
        maxCnt = Math.max(maxCnt, depth);

        for(int i = 0; i < dungeons.length;i++) {
            if(!visited[i] && curK >= dungeons[i][0]) {
                visited[i] = true;
                dfs(depth+1, curK - dungeons[i][1], dungeons, visited);
                visited[i] = false;
            }
        }
    }
}