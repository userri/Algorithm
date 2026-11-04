import java.util.*;
public class Main {
    static int N, K, M, maxMove;
    static int[][] grid;
    static int[][] startPoints;
    static List<int[]> stones;
    static boolean[] stoneVisit;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) {
        maxMove = 1;
        stones = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1) {
                    stones.add(new int[]{i, j});
                }
            }
        }
    
        stoneVisit = new boolean[stones.size()];

        startPoints = new int[K][2];
        for (int i = 0; i < K; i++) {
            startPoints[i][0] = sc.nextInt() - 1;
            startPoints[i][1] = sc.nextInt() - 1;
        }

        comb(0, 0);
        System.out.println(maxMove);

    }

    private static void comb(int cur, int cnt) {
        if(cnt >= M) {
            // 최대 도달 칸수 계산
            bfs();
            return;
        }

        for(int i = cur; i < stones.size(); i++) {
            if(stoneVisit[i]) continue;
            stoneVisit[i] = true;
            grid[stones.get(i)[0]][stones.get(i)[1]] = 0;
            comb(i+1, cnt+1);
            stoneVisit[i] = false;
            grid[stones.get(i)[0]][stones.get(i)[1]] = 1;
        }
    }
    
    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        
        int visitCnt = 0;
        
        // 시작점 순회
        for(int j = 0; j < K; j++) {
            int stRow = startPoints[j][0];
            int stCol = startPoints[j][1];
            // 이미 다른 시작점에서 방문했으면 건너뛰기
            if(visited[stRow][stCol]) continue;
            visited[stRow][stCol] = true;
            q.offer(new int[]{stRow, stCol});
            visitCnt++;

            
            while(!q.isEmpty()) {
                
                int[] cur = q.poll();
                
                for(int i = 0; i < 4; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if(grid[nr][nc] != 0) continue;
                    if(visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    visitCnt++;
                    maxMove = Math.max(maxMove, visitCnt);
                }
            }

        }
    }
}