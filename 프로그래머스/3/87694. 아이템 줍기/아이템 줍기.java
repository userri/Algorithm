
/*
직사각형 바깥 영역이 무조건 하나는 이어져 있어야 해
무조건 우회전만 하면서 이어지는 블록 있는지 검사.

일단 좌표값은... 양쪽값중 하나만 채우는 걸로 함


칸이 아닌 좌표가 색칠된 개념으로 생각
*/
import java.util.*;
class Solution {
    // 우하좌상
    int[] drow = {0,1,0,-1};
    int[] dcol = {1,0,-1,0};
    
    // 나를 둘러싸는 0 검사용 방향배열
    // 0 0 0
    // 0 1 0
    // 0 0 0
    int[] aroundR = {-1,-1,-1,0,0,1,1,1};
    int[] aroundC = {-1,0,1,-1,1,-1,0,1};
    
    int[][] grid;
    // int[][] distance;
    final int RECTANGLE = 1, CHARACTER = 2, ITEM = 3, INSIDE = 4;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 와... 두배를 해야한다네
        for(int[] line: rectangle) {
            for(int i = 0; i < line.length; i++) line[i] *= 2;
        }
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        grid = new int[52*2][52*2];
        // distance = new int[52*2][52*2];
        
        
        for(int[] rec: rectangle) {
            int leftUpRow = rec[0], leftUpCol = rec[1], rightDownRow = rec[2], rightDownCol = rec[3];
            for(int i = leftUpRow; i <= rightDownRow; i++) {
                for(int j = leftUpCol; j <= rightDownCol; j++) {
                    if (i == leftUpRow || i == rightDownRow || j == leftUpCol || j == rightDownCol) {
                        grid[i][j] = RECTANGLE;
                    }
                    else grid[i][j] = INSIDE;
                }
            }
        }
        
        grid[characterX][characterY] = CHARACTER;
        grid[itemX][itemY] = ITEM;
        
        // for(int[] line: grid) System.out.println(Arrays.toString(line));
        
        // dfs? bfs? 일단 상하좌우로 이동해 본 다음에 주변에 색칠안된 칸이 하나도 없으면 걘 내륙이니까 건너뛰어
        // bfs로 하다보면 짧은쪽이 먼저 도착할테니 그걸 리턴
        // 아... 내륙빈공간 어쩌냐... visited로 체크하면 알아서 소멸될듯
        
        int answer = bfs(characterX, characterY);
        // for(int[] line: distance) System.out.println(Arrays.toString(line));
    
        return answer/2;
    }
    int bfs(int characterX, int characterY) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{characterX, characterY, 0});
        boolean[][] visited = new boolean[52*2][52*2];
        visited[characterX][characterY] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int dist = cur[2];
            // distance[row][col]=dist;
            
            // System.out.println(Arrays.toString(cur) + " 방문");
            
            // 만약 주변에 0이 인접해있지 않다면 내륙이니까 건너뛰기
            boolean isMargin = false;
            for(int i = 0; i < 8; i++) {
                int nr = row + aroundR[i];
                int nc = col + aroundC[i];
                // 색칠여부와 범위검사, 방문처리는 큐에 넣을 때 이미 되어있음 다시 안넣음
                if(grid[nr][nc] == 0) {
                    isMargin = true;
                    break;
                }
            }
            
            if(!isMargin) {
                // System.out.println("내륙이라 건너뜀");
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = row + drow[i];
                int nc = col + dcol[i];
                if(nr < 1 || nr > 100 || nc < 1 || nc > 100) continue;
                if(grid[nr][nc] == 0) continue;
                if(visited[nr][nc]) continue;
                
                if(grid[nr][nc] == 3) return dist + 1;
                q.offer(new int[]{nr, nc, dist + 1});
                visited[nr][nc] = true;
            }
        }
        return -1;
        
    }
}