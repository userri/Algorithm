import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        boolean[][] matrix = new boolean[201][201];
        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int i = x1; i < x2; i++) {
                for(int j = y1; j < y2; j++) {
                    matrix[i + 100][j + 100] = true;
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < 201; i++) {
            for(int j = 0; j < 201; j++) {
                if(matrix[i][j]) cnt++;
            }
        }
        System.out.println(cnt);
        // Please write your code here.
    }
}