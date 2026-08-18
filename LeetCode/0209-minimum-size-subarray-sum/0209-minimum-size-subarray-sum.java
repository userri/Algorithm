class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int answer = Integer.MAX_VALUE, sum = 0;
        int l = 0;
        for(int r = 0; r < nums.length; r++) {

            sum += nums[r];
            while(sum >= target) {
                answer = Math.min(answer, r - l + 1);
                sum -= nums[l++];
            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}
