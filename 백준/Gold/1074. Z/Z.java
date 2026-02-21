import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static int N,r,c,cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        r = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);
        
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