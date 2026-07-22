/*
좌상단 시작, 우하단 종료이므로 위보다 아래먼저, 왼보다 오른먼저 우선순위 정하면 될듯
근데 미로가 볼록한 식으로 되어있으면 그건 어케 탐색해야하지 내 코드로 되나
0 1 1 0
0 0 1 1
1 0 0 0
1 0 1 0
우선순위를 바꿔서 한번더 계산해야하나?(아래>오른에서 오른>아래)
일단 테케 되는지 확인

-> 틀림
bfs로 전략변경
-> 효율성테스트 틀림
큐에서 꺼낼때가 아닌 큐에 넣을때 즉시 방문처리

*/

import java.util.*;
class Solution {
    // 아래오른위왼
    int[] drow = {1,0,-1,0};
    int[] dcol = {0,1,0,-1};
    boolean[][] visited;
    int N,M;
    int[][] maps;
    boolean flag = false;
    public int solution(int[][] maps) {
        this.maps = maps;
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
    
        return bfs(0,0);
    }
    int bfs(int row, int col) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(row, col , 1));
        while(!q.isEmpty()) {
            Point cur = q.poll();
            // System.out.println(cur.row +", " + cur.col + " 방문");
            if(cur.row == N-1 && cur.col == M-1) return cur.cnt;
            for(int i = 0; i <4; i++) {
                int nr = cur.row + drow[i];
                int nc = cur.col + dcol[i];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue; // 인덱스 범위 초과
                if(maps[nr][nc] == 0) continue; // 갈 수 없는 경로
                if(visited[nr][nc]) continue; // 이미 방문
                q.offer(new Point(nr, nc, cur.cnt+1));
                visited[nr][nc] = true;
            }
        }
        return -1;
        
    } 
}
class Point {
    int row, col, cnt;
    public Point(int row, int col, int cnt) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
}