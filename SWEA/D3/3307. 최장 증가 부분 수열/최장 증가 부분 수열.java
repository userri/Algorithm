
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
            // 원소 범위 1~2^31 - 1 : int
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 일단 경우의 수가 많을것같으므로 dp를 써보자
            // 최장 부분 증가 수열의 길이를 알기 위해 알아야 할 최소한의 정보...
            // 클로드 힌트: i번째 원소로 끝나는 증가 부분수열의 최대 길이
            int[] dp = new int[N];
            // 모든 원소 1개는 증가개수 1
            Arrays.fill(dp, 1);
            for(int i = 0; i < N; i++) {
                // 0번째 원소로 끝나는 dp[0] = 1
                // 1번째 원소로 끝나는 dp[1] = 0번째, 1번째 증가되는지 검사: 2 or 1
                // 2번째 원소로 끝나는 dp[2] = 숫자가 더 커진다면 이전 dp값들 중 최대값 + 1
                for(int j = i+1; j < N; j++) {
                    // i번째 원소 이후로 커지는 숫자 한개라도 있으면 max값 업데이트해나가
                    if(arr[i] < arr[j]) {
                        dp[j] = Math.max(dp[j], dp[i] + 1);
                    }
                }
            }
            int answer = 0;
            for(int i: dp) {
                answer = Math.max(answer, i); // 냅색은 dp[L]이 답, LIS는 Math.max(dp[N])이 답!!!
            }
            // 예외: 수열 길이 1개, 같은것만, 감소만
            System.out.println("#" + test_case + " " + answer);
            
        }
    }
}
