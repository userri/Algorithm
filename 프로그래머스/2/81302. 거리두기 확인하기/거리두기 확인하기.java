

import java.util.*;

public class Solution {
    // 상하좌우
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        // 2차원 배열이지만 사실상 3차원 배열
        // 한 줄마다 2차원 배열 ex. ["OOOOX", "XOXOX",,,]
        // 한 줄마다 bfs를 해야 한단 뜻
        int t = 0;
        for (String[] room : places) {
            String[][] splitRoom = new String[5][5];
            for (int i = 0; i < 5; i++) {
                String[] splitLine = room[i].split("");
                for (int j = 0; j < 5; j++) {
                    splitRoom[i][j] = splitLine[j];
                }
            }
            // 한 줄로써 하나의 2차원 배열을 채웠음
            // 근데 이제 P마다 bfs를 실행해야 해
            int s = 1;
            boolean[][] visited = new boolean[5][5];
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (splitRoom[r][c].equals("P")) {
                        s = bfs(r, c, splitRoom, visited);
                    }
                    if (s == 0) break;
                }
                if (s == 0) break;
            }
            answer[t] = s;
            t++;
        }
        return answer;
    }

    public int bfs(int row, int col, String[][] room, boolean[][] visited) {
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(row, col, 0));
        visited[row][col] = true;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            // 거리 3이면 안넣고 건너뛰기
            if (cur.dist + 1 >= 3) {
                continue;
            }
            // 상하좌우중에 방문안한곳 검사
            // X면 더이상 이동 X
            // 거리가 3인 지점부터는 거리기준 만족하므로 더이상 이동 X
            // 만약 P에 도달했는데 거리가 2이하면 바로 리턴
            for (int i = 0; i < 4; i++) {
                // 범위검사
                int nrow = cur.row + drow[i];
                int ncol = cur.col + dcol[i];
                if (nrow < 0 || nrow >= 5 || ncol < 0 || ncol >= 5) {
                    continue;
                }
                // 방문 이미 했으면 건너뛰기
                if (visited[nrow][ncol]) {
                    continue;
                }
                // X면 건너뛰기
                if (room[nrow][ncol].equals("X")) {
                    continue;
                }
                // P를 마주쳤을땐 거리 확인하고, 방문체크 안하고(다음 턴을 위해) 지나가기
                if (room[nrow][ncol].equals("P")) {
                    if (cur.dist + 1 <= 2) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
                q.offer(new Point(nrow, ncol, cur.dist + 1));
                visited[nrow][ncol] = true;
            }

        }
        return 1;
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
        return "[" + row + ", " + col + ", " + dist + "]";
    }
}
