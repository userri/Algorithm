/**
합이 target이상인 subarray 중에 최소길이인것
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int sum = nums[0], answer = Integer.MAX_VALUE;
        int l = 0,r= 0;
        while(r < nums.length) {
            System.out.println(l + ", " + r + ": " + sum);
            if(sum < target) {
                if(r + 1 < nums.length) {
                    r++;
                    sum += nums[r];
                } else break; // 늘려야되는데 인덱스 넘어가기 시작하면 break;
            } else  { // sum >= target일 때
                answer = Math.min(answer, r - l + 1);
                if(l + 1 <= r) {
                    sum -= nums[l];
                    l++;
                } else {
                    if(r + 1 >= nums.length) break;
                    r++;
                    sum += nums[r];
                }
            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}