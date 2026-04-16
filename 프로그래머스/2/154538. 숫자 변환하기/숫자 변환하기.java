/*
1. 문제
- x+n
- x*2
- x*3

- 자연수 x,y,n이 매개변수로 주어질 때, x를 y로 변환하기 위해 
- 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요.
- x를 y로 만들 수 없다면 -1를 리턴
*/
import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        // 커지는 방향이니까 for문으로 단순하게 하면 되는거아님?
        // 아무래도 앞에서 초기화하면 뒤에 초기화되는건 무조건 횟수가 더 클테니 업데이트될 일이 없겠지?
        int[] dp = new int[1_000_001];
        int max = 1_000_001;
        Arrays.fill(dp, max);
        dp[x] = 0;
        for(int i = x; i <= y; i++) {
            if(i+n < max) {
                dp[i+n] = Math.min(dp[i+n], dp[i] + 1);
            }
            if(i*2 < max) {
                dp[i*2] = Math.min(dp[i*2], dp[i] + 1);
            }
            if(i*3 < max) {
                dp[i*3] = Math.min(dp[i*3], dp[i] + 1);
            }
        }
        return dp[y] == max ? -1 : dp[y];
    }
}