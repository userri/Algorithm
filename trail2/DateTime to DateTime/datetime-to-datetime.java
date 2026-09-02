import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        // Please write your code here.

        if(C < 11) {
            C += 60;
            B--;
            }
        if(B < 11) {
            B += 24;
            A--;
        }
        if (A < 11) System.out.println(-1);
        else System.out.println(1440*(A-11) + 60*(B-11) + C-11);
    }
}