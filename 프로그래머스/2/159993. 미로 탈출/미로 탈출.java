
import java.util.*;
class Solution {
    // 상하좌우
    int[] drow = {-1,1,0,0};
    int[] dcol = {0,0,-1,1};
    String[] maps;
    public int solution(String[] maps) {
        this.maps = maps;
        
        int strow = 0, stcol = 0;
        int lrow = 0, lcol = 0;
        int erow = 0, ecol = 0;
        
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                if(maps[i].charAt(j) == 'S') {
                    strow = i; stcol = j;
                } else if (maps[i].charAt(j) == 'E') {
                    erow = i; ecol = j;
                } else if (maps[i].charAt(j) == 'L') {
                    lrow = i; lcol = j;
                }
            }
        }
        int before = bfs(strow, stcol, lrow, lcol);
        if(before < 0) return -1;
        int after = bfs(lrow, lcol, erow, ecol);
        if(after < 0) return -1;
        
        return before + after;
    }
    int bfs(int startRow, int startCol, int endRow, int endCol) {
        
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        Queue<int[]> q = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        q.offer(new int[]{startRow, startCol, 0});
        
        int result = -1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == endRow && cur[1] == endCol) {
                result = cur[2];
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + drow[i];
                int nc = cur[1] + dcol[i];
                int nmove = cur[2] + 1;
                if(nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length()) continue;
                if(maps[nr].charAt(nc) == 'X') continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, nmove});
            }
        }
        return result;
    }
}