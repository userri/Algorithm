/*
1. 문제
- 집을 빨강, 초록, 파랑 중 하나의 색으로 칠해야 함
- 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 규칙 만족하면서 모든 집 칠하는 최소비용 구하자
    - 각 집 색은 이전 집, 다음 집의 색과 같지 않아야 한다.
2. 구현
- dp를 해야할것같군..
- 음... 만약 내가 현재단계에서 최소 값을 골랐어(ex. 빨강)
- 근데 그러면 다음 단계에서 빨강이 최솟값일지라도 못고르게 돼(나보다 더 작은 수의 빨강이더라도)
- 이럴땐 어떡해야할까?!!?!?!?!?
- 그러게... 정말 어떡해야할까?
dp로 하면 될듯?
*/

import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] homes = new int[N][3];
        int[][] dp = new int[N][3];
        for(int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            homes[i][0] = Integer.parseInt(st.nextToken());
            homes[i][1] = Integer.parseInt(st.nextToken());
            homes[i][2] = Integer.parseInt(st.nextToken());
        }
        dp[0][0] = homes[0][0];
        dp[0][1] = homes[0][1];
        dp[0][2] = homes[0][2];
        for(int i = 1;i < N;i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + homes[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + homes[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + homes[i][2];
        }
        int min = Integer.MAX_VALUE;
        for(int num : dp[N-1]) {
            min = Math.min(min, num);
        }
        System.out.println(min);
    }
}