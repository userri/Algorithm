
import java.util.*;
import java.io.*;
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] dp = new int[N];
            // 원소 1개는 길이 1로 초기화
            Arrays.fill(dp, 1);
            
            // 뒤에 나보다 큰 애 있으면 무조건 1씩 더해줘
            for(int i = 0; i < N; i++) {
                for(int j = i + 1; j < N; j++) {
                    if(arr[j] > arr[i]) dp[j] = Math.max(dp[j],dp[i] + 1);
                }
            }

            int answer = 0;
            for(int i : dp) {
                answer = Math.max(answer, i);
            }
            System.out.println("#"+test_case+" "+answer);
        }
    }
}