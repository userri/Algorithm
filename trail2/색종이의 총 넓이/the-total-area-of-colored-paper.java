
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        boolean[][] matrix = new boolean[201][201];
        int cnt = 0;

        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for(int i = x; i < x + 8; i++) {
                for(int j = y; j < y + 8; j++) {
                    if(matrix[i + 100][j + 100]) continue;
                    else {
                        matrix[i + 100][j + 100] = true;
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
        // Please write your code here.
    }
}