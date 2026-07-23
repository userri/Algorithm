// dp 풀이(냅색)
import java.util.*;
import java.io.*;
class Solution {
    static int[][] foods;
    // 특정 칼로리까지 허용될 때 최대 선호도를 저장
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            // i번째 음식의 선호도와 칼로리를 저장
            foods = new int[N][2];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int t = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                foods[i][0] = t;
                foods[i][1] = k;
            }
            // 칼로리 기준 초기화
            dp = new int[L+1];
            // 재료를 하나씩 넣어서 dp를 업데이트
            for(int i = 0; i < N; i++) {
                int taste = foods[i][0];
                int cal = foods[i][1];
                for(int w = L; w-cal >= 0; w--) {
                    // w-cal은 w 보다 작음
                    // w는 큰쪽에서 작은쪽으로 이동하고있기 때문에
                    // i번째 음식이 반영되지 않았음이 확실함
                    // 그래서 이제 현재에 i번째 음식을 넣어주고 taste를 더해줌
                    dp[w] = Math.max(dp[w], dp[w - cal] + taste);
                }
            }
            
            System.out.println("#" + test_case+" "+dp[L]);
        }
    }
}