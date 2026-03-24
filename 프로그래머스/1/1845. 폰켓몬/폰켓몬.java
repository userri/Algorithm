

import java.util.*;

class Solution {
    public int solution(int[] nums) {

        int N  = nums.length/2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        return Math.min(map.keySet().size(), N);
    }
}