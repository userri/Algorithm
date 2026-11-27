import java.util.*;
import java.io.*;
class Main {
    // 상좌하우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[][] board;
    static int N, sharkR, sharkC, size = 2, eat = 0, time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 9) {
                    sharkR = i; sharkC = j;
                    continue;
                }
                board[i][j] = n;
            }
        }

        while(true) {
            int[] target = bfs();
            if(target == null) break;
            time += target[2];
            board[target[0]][target[1]] = 0;
            sharkR = target[0]; sharkC = target[1];
            if(++eat == size) {size++; eat = 0;}
        }
        System.out.println(time);
    }

    static int[] bfs() {
        boolean[][] visited = new boolean[N][N];
        visited[sharkR][sharkC] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sharkR, sharkC, 0});

        List<int[]> list = new ArrayList<>();
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(board[cur[0]][cur[1]] != 0 && board[cur[0]][cur[1]] < size) {
                list.add(cur);
            };
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                int nd = cur[2] + 1;
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(visited[nr][nc]) continue;
                if(board[nr][nc] > size) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, nd});
            }
        }
        if(list.size() == 0) return null;
        list.sort((a,b) -> {
            if(a[2] != b[2]) return a[2] - b[2];
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        return list.get(0);
        
    }
}