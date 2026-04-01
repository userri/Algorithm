
class Solution {
    int n;
    int[] times;
    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;
        
        long l = 1L;
        long r = 1_000_000_000 * 1_000_000_000L;
        long answer = 0;
        while(l <= r) {
            long mid = (l+r)/2;
            if(isPossible(mid)) {
                // 만약 mid가 심사인원수가 넉넉하게 남으면
                answer = mid;
                r = mid-1;
            } else l = mid+1;
        }
        
        return answer;
    }
    boolean isPossible(long maxTime) {
        // time을 각 심사관의 심사시간으로 나눈 수의 합이,
        // n(심사받는 사람 수)보다 크거나 같아야 함
        long sum = 0;
        for(int time: times) {
            sum += maxTime/time;
            // System.out.println(maxTime+": "+time + " " + maxTime/time);
        }
        return sum >= n;
        
    }
}