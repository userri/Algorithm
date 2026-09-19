import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] people = new int[n+1];
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            people[num]++;
            if(people[num] >= k) {
                System.out.println(num);
                return;
            }
        }
        System.out.println(-1);
        // Please write your code here.
    }
}