import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static String[][] pic;
    static boolean[][] visited;
    // 상, 하, 좌, 우
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pic = new String[N][N];
        visited = new boolean[N][N];
        int normalCnt = 0;
        int oddCnt = 0;
        
        for (int i = 0; i<N; i++) {
            pic[i] = br.readLine().split("");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j<N; j++) {
                if (normal(i,j) != -1) {
                    normalCnt++;
                }
            }
        }
        
        // 방문 그래프 초기화
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j<N; j++) {
                if (odd(i,j) != -1) {
                    oddCnt++;
                }
            }
        }

        System.out.println(normalCnt + " " + oddCnt);
    }

    static int normal(int row, int col) {
        if (visited[row][col]) {
            return -1;
        }
        // 방문한 지점을 기준으로 같은블록 체크
        String start = pic[row][col];
        Queue<Point> queue = new LinkedList<>();
        Point s = new Point(row, col);
        queue.offer(s);
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i<4; i++) {
                int nrow = p.row + drow[i];
                int ncol = p.col + dcol[i];
                if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= N) {
                    continue;
                }
                // 이미 방문했거나 기준점이랑 다르면 continue;
                if (visited[nrow][ncol] || !pic[nrow][ncol].equals(start)) {
                    continue;
                }
                Point q = new Point(nrow, ncol);
                queue.offer(q);
                visited[nrow][ncol] = true;
            }
        }
        return 0;
    }

    
    static int odd(int row, int col) {
        if (visited[row][col]) {
            return -1;
        }
        // 방문한 지점을 기준으로 같은블록 체크
        String start = pic[row][col];
        Queue<Point> queue = new LinkedList<>();
        Point s = new Point(row, col);
        queue.offer(s);
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i<4; i++) {
                int nrow = p.row + drow[i];
                int ncol = p.col + dcol[i];
                if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= N) {
                    continue;
                }
                // 이미 방문했거나 기준점이랑 다르면 continue;
                if (visited[nrow][ncol]) {
                    continue;
                }
                if (start.equals("R") || start.equals("G")) {
                    if (pic[nrow][ncol].equals("B")) {
                        continue;
                    }
                } else if (!pic[nrow][ncol].equals("B")) continue;
                
                Point q = new Point(nrow, ncol);
                queue.offer(q);
                visited[nrow][ncol] = true;
            }
        }
        return 0;
    }
    
}
class Point {
    int row;
    int col;
    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}