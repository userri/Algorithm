class Solution {
    int[][][] dp = new int[2][200][200];
    char[] opers;
    int[] nums;
    String[] arr;
    int tmp = 987654321;
    
    public void init() {
        int n = arr.length/2;
        for(int i = 0;i < n+1; i++) {
            for(int j = 0; j < n+1; j++) {
                dp[0][i][j] = -1*tmp;
                dp[1][i][j] = tmp;
            }
        }
        opers = new char[n];
        nums = new int[n+1];
        
        for(int i = 0;i < arr.length;i++) {
            if (i %2 == 0) {
                nums[i/2] = Integer.parseInt(arr[i]);
            } else {
                opers[i/2] = arr[i].charAt(0);
            }
        }
    }
    
    public int solve(int flag, int start, int end) {
        if(start == end) {
            return dp[flag][start][end] = nums[start];
        }
        // 이미 계산한 결과라면
        if (flag == 0 && dp[flag][start][end] != -1*tmp) {
            return dp[flag][start][end];
        }
        if (flag ==1 && dp[flag][start][end] != tmp) {
            return dp[flag][start][end];
        }
        
        // flag에 따라 초기값 다르게 주기
        int ret = flag == 0?-1*tmp:tmp;
        
        if(flag == 0) {
            for(int mid = start; mid < end; mid++) {
                if(opers[mid] == '-') { // 빼기면 최대 - 최소여야 함
                    ret = Math.max(ret, solve(0, start, mid) - solve(1, mid+1, end));
                } else { // 최대 + 최대 여야 함
                    ret = Math.max(ret, solve(0, start, mid) + solve(0, mid+1, end));
                }
            }
        } else {
            for(int mid = start; mid< end; mid++) {
                if(opers[mid] == '-') { // 빼기면 최소 - 최대여야 함
                    ret = Math.min(ret, solve(1, start, mid) - solve(0, mid+1, end));
                } else { // 최소 + 최소 여야 함
                    ret = Math.min(ret, solve(1, start, mid) + solve(1, mid+1, end));
                }
            }
        }
        return dp[flag][start][end] = ret;
    }
    
    
    public int solution(String arr[]) {
        int answer = -1;
        this.arr = arr;
        init();
        return answer = solve(0, 0, arr.length/2);
    }
}