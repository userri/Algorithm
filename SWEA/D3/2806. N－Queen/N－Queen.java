
import java.util.*;
import java.io.*;
class Solution {
    static int N;
    static int cnt = 0;
    static boolean[] cols;
    static boolean[] upright;
    static boolean[] downright;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        

        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            cols = new boolean[N];
            // 대각선 범위 0~ 2(N-1);
            downright = new boolean[2 * N - 1];
            upright = new boolean[2 * N]; // 대각선 범위 행 - 열: 0 - (N-1) ~ N-1 - 0
            // N을 더해서 1~2*N - 1으로 만들기

            backtracking(0);
            
            System.out.println("#"+test_case + " "+cnt);
            cnt = 0;
        }
    }

    static void backtracking(int row) {
        if(row >= N) {
            cnt++;
            return;
        }
        for(int col = 0; col < N; col++) {
            if(cols[col] || downright[row + col] || upright[row - col + N]) continue;
            cols[col] = true;
            downright[row + col] = true;
            upright[row - col + N] = true;
            backtracking(row + 1);
            cols[col] = false;
            downright[row + col] = false;
            upright[row - col + N] = false;
        }
    }
}