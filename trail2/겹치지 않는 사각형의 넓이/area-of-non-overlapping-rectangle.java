import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[][] matrix = new boolean[2001][2001];
        int cnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int ax1 = Integer.parseInt(st.nextToken());
        int ay1 = Integer.parseInt(st.nextToken());
        int ax2 = Integer.parseInt(st.nextToken());
        int ay2 = Integer.parseInt(st.nextToken());
        for(int i = ax1; i < ax2; i++) {
            for(int j = ay1; j < ay2; j++) {
                matrix[i + 1000][j + 1000] = true;
                cnt++;
            }
        }
        st = new StringTokenizer(br.readLine());
        int bx1 = Integer.parseInt(st.nextToken());
        int by1 = Integer.parseInt(st.nextToken());
        int bx2 = Integer.parseInt(st.nextToken());
        int by2 = Integer.parseInt(st.nextToken());
        for(int i = bx1; i < bx2; i++) {
            for(int j = by1; j < by2; j++) {
                matrix[i + 1000][j + 1000] = true;
                cnt++;
            }
        }
        st = new StringTokenizer(br.readLine());
        int mx1 = Integer.parseInt(st.nextToken());
        int my1 = Integer.parseInt(st.nextToken());
        int mx2 = Integer.parseInt(st.nextToken());
        int my2 = Integer.parseInt(st.nextToken());
        for(int i = mx1; i < mx2; i++) {
            for(int j = my1; j < my2; j++) {
                if(matrix[i + 1000][j + 1000])
                    cnt--;
            }
        }
        System.out.println(cnt);
    }
}