import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                grid[i][j] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            int sum = grid[i][0] + grid[i][1] + grid[i][2];
            result = Math.max(result, sum);
            for(int j = 3; j < n; j++) {
                sum -= grid[i][j-3];
                sum += grid[i][j];
                result = Math.max(result, sum);
            }
        }
        System.out.println(result);
    }
}