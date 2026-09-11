import java.util.*;
import java.io.*;
public class Main {
    static int white = 0, black = 0, gray = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] x = new int[n];
        char[] dir = new char[n];
        // 흰색 횟수, 검은색 횟수
        int[] map = new int[200_001]; // 흰이면 1xx, 흰두번이면 2xx, 검이면 1x, 검두번이면 2x, 현재색은 일의자리(흰=1, 검=2, 회=3)

        int cur = 100_000;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            dir[i] = st.nextToken().charAt(0);
        }
        // System.out.println(Arrays.toString(x));
        // System.out.println(Arrays.toString(dir));

        for(int i = 0; i < n; i++) {
            // System.out.println(x[i] + ", " + dir[i]);
            if(dir[i] == 'L') {
                for(int j = 0; j < x[i]; j++) {
                    // System.out.print(map[cur-j] + " -> ");
                    if(map[cur-j]/10 == 22) continue;
                    else if(map[cur-j]/10 == 12) {
                        int tempCol = map[cur-j] % 10;
                        if(tempCol == 1) {
                            white--;
                        } else if(tempCol == 2) {
                            black--;
                        }
                        gray++;
                        map[cur-j] = 223;
                    } else {
                        int tempw = map[cur-j]/100;
                        int tempb = map[cur-j]/10 - 10*tempw;
                        int tempCol = map[cur-j]%10;
                        map[cur-j] = Math.min(2, tempw + 1) * 100 + tempb * 10 + 1;
                        if(tempCol != 1) {
                            if(tempCol == 2) black--;
                            white++;
                        }
                    }
                    // System.out.println(map[cur-j]);
                }
                cur -= (x[i] - 1);
            } else {
                for(int j = 0; j < x[i]; j++) {
                    // System.out.print(map[cur+j] + " -> ");
                    if(map[cur + j]/10 == 22) continue;
                    else if(map[cur+j]/10 == 21) {
                        int tempCol = map[cur+j] % 10;
                        if(tempCol == 1) {
                            white--;
                        } else if(tempCol == 2) {
                            black--;
                        }
                        gray++;
                        map[cur+j] = 223;
                    } else {
                        int tempw = map[cur+j]/100;
                        int tempb = map[cur+j]/10 - 10*tempw;
                        int tempCol = map[cur+j]%10;
                        map[cur+j] = tempw * 100 + Math.min(2, tempb + 1) * 10 + 2;
                        if(tempCol != 2) {
                            if(tempCol == 1) white--;
                            black++;
                        }
                    }
                    // System.out.println(map[cur+j]);
                }
                cur += (x[i] - 1);
            }
            // for(int k = 9990; k < 10010; k++) {
            //     System.out.print(map[k] + " ");
            // }
            // System.out.println();
            // System.out.println(white + " " + black + " " + gray);
        }
        System.out.println(white + " " + black + " " + gray);
    }
}