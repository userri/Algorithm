import java.util.*;
public class Main {
    static int N, M, C;
    static int[][] grid;
    static int[][] gridSum;
    static int[] rowMax;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        C = sc.nextInt();
        grid = new int[N][N];
        gridSum = new int[N][N-(M-1)];
        rowMax = new int[N];
        
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        for(int i = 0; i < N; i++) {
            int max = partMax(i, 0, 0, 0, 0);
            gridSum[i][0] = max;
            for(int j = M; j < N; j++) {
                gridSum[i][j-(M-1)] = partMax(i, j-(M-1), 0, 0, 0);
                max = Math.max(max, gridSum[i][j-(M-1)]);
            }
            rowMax[i] = max;
        }


        int totalMax = 0;
        // 각 열에서 두개 고르는 경우의 수
        for(int k = 0; k < N; k++) {
            for(int i = 0; i + M < gridSum[0].length; i++) {
                for(int j = i + M; j < gridSum[0].length; j++) {
                    totalMax = Math.max(totalMax, gridSum[k][i] + gridSum[k][j]);
                }
            }
        }
        Arrays.sort(rowMax);
        totalMax = Math.max(totalMax, rowMax[N-1] + rowMax[N-2]);
        System.out.println(totalMax);
    }
    static int partMax(int row, int col, int weight, int val, int cnt) {
        if(weight > C) return -1;
        if(cnt >= M) return val;
        if(col >= N) return -1;
        int sum = val;
        sum = Math.max(sum, partMax(row, col + 1, weight, val, cnt + 1));
        sum = Math.max(sum, partMax(row, col + 1, weight + grid[row][col], val + grid[row][col] * grid[row][col], cnt + 1));

        return sum;
    }
}