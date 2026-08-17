
import java.util.*;
import java.io.*;
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            
            // N-1번까지 자를수있음. N이 1이면 bob이 이김. N이 2이면 alice가 이김. 홀수면 bob이 이김
            System.out.println("#"+test_case+" "+(N % 2 == 0 ? "Alice" : "Bob"));
            
        }
    }
}