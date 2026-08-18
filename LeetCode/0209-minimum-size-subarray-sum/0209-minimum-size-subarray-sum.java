class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int answer = Integer.MAX_VALUE, sum = 0;
        int l = 0;
        for(int r = 0; r < nums.length; r++) {

            sum += nums[r];
            // l을 더해도 되는 상황일때만 진입해(l = r 등호도 제외)
            while(l < r && sum >= target) {
                answer = Math.min(answer, r - l + 1);
                sum -= nums[l];
                l++;
            }
            
            if(sum >= target) answer = Math.min(answer, r - l + 1);
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}