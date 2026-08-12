class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int l = 0, r = 0, sum = nums[0];
        int count = 0;
        int len = Integer.MAX_VALUE;
        while(r < nums.length) {
            
            if(sum >= target){
                len = Math.min(len, r - l + 1);
                count++;
                // 만약 왼쪽 줄이려고 햇는데 r넘으면 r을 늘려
                if(l + 1 > r) {
                    if(r + 1 >= nums.length) break;
                    r++;
                    sum += nums[r];
                } else {
                    sum -= nums[l];
                    l++;
                }
            } else if(sum < target) {
                if(r+1 >= nums.length) break;
                r++;
                sum += nums[r];
            }
        }
        return count == 0 ? 0 : len;
    }
}