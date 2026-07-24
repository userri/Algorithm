class Solution {
    int k;
    int[] stones;
    public int solution(int[] stones, int k) {
        this.k = k;
        this.stones = stones;
        
        int minStone = Integer.MAX_VALUE;
        int maxStone = 200_000_000;
        for(int s:stones) {
            minStone = Math.min(minStone, s);
            maxStone = Math.max(maxStone, s);
        }

        long answer = 0;
        long left = minStone;
        long right = 200_000_000;
        while(left <= right) {
            long mid = (left + right)/2;
            if(check(mid)) {
                answer = mid;
                // 최대를 찾아야 하므로 커지는 방향으로
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) answer;
    }
    boolean check(long people) {
        int consecutive = 0; // 연속적으로 0인 경우를 계산
        for(int s: stones) {
            if(s < people) {
                consecutive++;
                if(consecutive >= k) return false;
            } else {
                consecutive = 0;
            }
        }
        return true;
    }
}