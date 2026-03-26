/*
n = 1 1가지
n = 2
1 1
2 2가지
n = 3
1 1 1
1 2
2 1
3가지
4가지
n = 4
1 1 1 1
1 1 2 
1 2 1
2 1 1
2 2 -> 
*/

class Solution {
    public int solution(int n) {
        int[] dp = new int[60001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4;i <= 60000;i++) {
            dp[i] = (dp[i-1]%1_000_000_007 + dp[i-2]%1_000_000_007) % 1_000_000_007;
        }
        
        return dp[n];
    }
}