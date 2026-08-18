class Solution {
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;

        // prefix[i] = 0번부터 i-1번째까지 구간합
        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for(int i = 1; i < nums.length+1; i++) 
            prefix[i] = prefix[i-1] + nums[i-1];
        System.out.println(Arrays.toString(prefix));
        
        for(int i = 0; i < nums.length+1; i++) {
            for(int j = i+1; j < nums.length+1; j++) {
                if(prefix[j] - prefix[i] == k) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}