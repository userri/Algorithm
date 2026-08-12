import java.util.*;
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0,1); // 아무것도 안 더한 것도 왼쪽 경계 후보

        int sum = 0,answer =0;
        
        for(int x: nums) {
            sum += x;
            answer += seen.getOrDefault(sum - k, 0); // 조건만족 개수 검사
            seen.put(sum, seen.getOrDefault(sum, 0) + 1);
        }
        return answer;
    }
}