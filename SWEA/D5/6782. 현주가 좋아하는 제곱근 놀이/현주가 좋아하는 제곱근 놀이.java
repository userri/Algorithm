import java.io.*;
import java.util.*;
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
		{
            long n = Long.parseLong(br.readLine());
            int cnt = 0;
            while (n != 2) {
                long s = (long) Math.sqrt(n);
                if(s*s == n) {
                    n = s;
                    cnt++;
                } else {
                    s += 1;
                    cnt += s*s - n + 1;
                    n = s;
                }
            }
            sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}