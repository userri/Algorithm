import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] mn = br.readLine().split(" ");
        int M = Integer.parseInt(mn[0]);
        int N = Integer.parseInt(mn[1]);

        // 상,하,좌,우
        int[] drow = {-1,1,0,0};
        int[] dcol = {0,0,-1,1};
        
        int[][] box = new int[N][M];

        Queue<Tomato> queue = new LinkedList<>();

        int stock = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M;j++ ) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 0) {
                    // 익어야 하는 토마토 개수 저장
                    stock++;
                }
                if (box[i][j] == 1) {
                    Tomato t = new Tomato(i,j,1);
                    queue.offer(t);
                }
            }
        }
        if (stock == 0) {
            System.out.println(0);
            return;
        }
        
        int day = 1;
        int maxDay = 0;

        // 날짜 순서대로 검사
        while (stock > 0) {
            int temp = stock;
            // 현재 날짜가 아니면 day를 증가시키고 다음 검사
            while (!queue.isEmpty() && queue.peek().day == day) {
                Tomato t = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int newr = t.row + drow[i];
                    int newc = t.col + dcol[i];
                    if (newr < 0 || newr >= N || newc < 0 || newc >= M) {
                        continue;
                    }
                    if (box[newr][newc] != 0) {
                        continue;
                    }
                    Tomato s = new Tomato(newr, newc, day+1);
                    queue.offer(s);
                    box[newr][newc] = day + 1;
                    stock--;
                    maxDay = Math.max(maxDay, day+1);
                }
            }
            // 재고 변화가 없다면 탈출
            if (stock == temp) {
                break;
            }
            // 다음 날짜 변환 위해
            day++;
        }
        if (stock == 0) {
            System.out.println(maxDay - 1);
        } else System.out.println(-1);
    }
}
class Tomato {
    int row;
    int col;
    int day;
    public Tomato(int row, int col, int day) {
        this.row = row;
        this.col = col;
        this.day = day;
    }
}