
/*

*/
import java.util.*;
public class Main {
    static int N, M, Q;
    static int[][] building, queries;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();
        building = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                building[i][j] = sc.nextInt();
        queries = new int[Q][4];
        for (int i = 0; i < Q; i++)
            for (int j = 0; j < 4; j++)
                queries[i][j] = sc.nextInt() -1;

        for(int q = 0; q < Q; q++) {
            int r1 = queries[q][0];
            int c1 = queries[q][1];
            int r2 = queries[q][2];
            int c2 = queries[q][3];

            int[] temp = new int[2 * (r2 - r1 + 1) + 2 * (c2 - c1 + 1) - 4];
            // System.out.println(temp.length);
            int idx = 0;
            // 영역의 숫자를 temp에 넣어
            for(int j = c1; j <= c2; j++) {
                temp[idx++] = building[r1][j];
            }
            for(int i = r1 + 1; i <= r2; i++) {
                temp[idx++] = building[i][c2];
            }
            for(int j = c2 - 1; j >= c1; j--) {
                temp[idx++] = building[r2][j];
            }
            for(int i = r2 - 1; i >= r1 + 1; i--) {
                temp[idx++] = building[i][c1];
            }

            // System.out.println(Arrays.toString(temp));

            // 맨왼쪽위 지점만 회전시킨값을 직접 넣기
            building[r1][c1] = temp[temp.length - 1];
            idx = 0;
            for(int j = c1 + 1; j <= c2; j++) { // c1이 아닌 c1+1부터 시작
                building[r1][j] = temp[idx++];
            }
            for(int i = r1 + 1; i <= r2; i++) {
                building[i][c2] = temp[idx++];
            }
            for(int j = c2 - 1; j >= c1; j--) {
                building[r2][j] = temp[idx++];
            }
            for(int i = r2 - 1; i >= r1 + 1; i--) {
                building[i][c1] = temp[idx++];
            }
            // System.out.println("회전결과");
            // for(int i = 0; i < N; i++) {
            //     System.out.println(Arrays.toString(building[i]));
            // } 

            wind(q);
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(building[i][j] + " ");
            }
            System.out.println();
        }

    }
    static void wind(int q) {
        int r1 = queries[q][0];
        int c1 = queries[q][1];
        int r2 = queries[q][2];
        int c2 = queries[q][3];
        int[] temp = new int[(r2 - r1 + 1) * (c2 - c1 + 1)];
        int idx = 0;
        for(int i = r1; i <= r2; i++) {
            for(int j = c1; j <= c2; j++) {
                temp[idx++] = around(i, j);
            }
        }
        // for(int j = c1; j <= c2; j++) {
        //     temp[idx++] = around(r1, j);
        // }
        // for(int i = r1 + 1; i <= r2; i++) {
        //     temp[idx++] = around(i, c2);
        // }
        // for(int j = c2 - 1; j >= c1; j--) {
        //     temp[idx++] = around(r2, j);
        // }
        // for(int i = r2 - 1; i >= r1 + 1; i--) {
        //     temp[idx++] = around(i, c1);
        // }
        // System.out.println(Arrays.toString(temp));
        idx = 0;
        for(int i = r1; i <= r2; i++) {
            for(int j = c1; j <= c2; j++) {
                building[i][j] = temp[idx++];
            }
        }
        // 값 넣기
        // for(int j = c1; j <= c2; j++) {
        //     building[r1][j] = temp[idx++];
        // }
        // for(int i = r1 + 1; i <= r2; i++) {
        //     building[i][c2] = temp[idx++];
        // }
        // for(int j = c2 - 1; j >= c1; j--) {
        //     building[r2][j] = temp[idx++];
        // }
        // for(int i = r2 - 1; i >= r1 + 1; i--) {
        //     building[i][c1] = temp[idx++];
        // }
        // System.out.println("평균 결과");
        // for(int i = 0; i < N; i++) {
        //     System.out.println(Arrays.toString(building[i]));
        // } 
        
    }
    static int around(int r, int c) {
        int cnt = 1;
        int sum = building[r][c];
        // System.out.println("시작값: " + sum);
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            cnt++;
            sum += building[nr][nc];
            // System.out.println(building[nr][nc] + " 추가");
        }
        return sum/cnt;
    }

}