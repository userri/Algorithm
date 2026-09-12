import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] tiles = new int[20_0001]; // 흰: 1, 검: 2
        int cur = 10_0000;
        int white = 0, black = 0;
        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            // System.out.println(x + " " + d);
            
            if(d == 'L') {
                for(int i = 0; i < x; i++) {
                    if(tiles[cur-i] == 0) {
                        white++;
                    } else if(tiles[cur-i] == 2) {
                        black--;
                        white++;
                    }
                    tiles[cur-i] = 1;
                }
                cur -= (x - 1);
            } else {
                for(int i = 0; i < x; i++) {
                    if(tiles[cur+i] == 0) {
                        black++;
                    } else if(tiles[cur+i] == 1) {
                        white--;
                        black++;
                    }
                    tiles[cur+i] = 2;
                }
                cur += (x-1);
            }
            // for(int j = 100000-20; j < 100000+20; j++) {
            //     System.out.print(tiles[j] + " ");
            // }
            // System.out.println();
        }
        System.out.println(white + " " + black);
    }
}