import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static int N,r,c,cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        find(r,c,(int)Math.pow(2,N));
        System.out.println(cnt);
    }
    static void find(int x, int y, int size) {
        if (size == 1) return;

        if (x < size/2 && y < size/2) {
            find(x, y, size/2);
        } else if (x < size/2 && y >= size/2) {
            cnt += (size*size/4);
            find(x, y - size/2, size/2);
        } else if (x >= size/2 && y < size/2) {
            cnt += (size * size/4)*2;
            find(x - size/2, y, size/2);
        } else {
            cnt += (size*size/4) * 3;
            find(x - size/2, y - size/2, size/2);
        }
    }
    
}