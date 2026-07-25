/*
오른쪽, 아래 먼저
bfs로 도착지 먼저 도달하면 answer 업데이트
*/

import java.util.*;
class Solution {
    // 우하좌상
    int[] drow = {0,1,0,-1};
    int[] dcol = {1,0,-1,0};
    int[][] maps;
    boolean[][] visited;
    int answer = -1;
    public int solution(int[][] maps) {
        this.maps = maps;
        visited = new boolean[maps.length][maps[0].length];
        bfs();
        return answer;
    }
    void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        // 행,열,이동한 칸 수 전달
        q.offer(new int[]{0,0,1});
        visited[0][0] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int moved = cur[2];
            if(row == maps.length-1 && col == maps[0].length-1) {
                answer = moved;
                return;
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = row + drow[i];
                int nc = col + dcol[i];
                int nm = moved + 1;
                if(nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length) continue;
                if(maps[nr][nc] == 0 || visited[nr][nc]) continue;
                q.offer(new int[]{nr,nc,nm});
                visited[nr][nc] = true;
                if(nr == maps.length-1 && nc == maps[0].length-1) {
                    answer = nm;
                    return;
                }
            }
        } 
    }
}