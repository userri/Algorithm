/*
야근피로도: 야근시작시점에서 남은 일의 작업량을 제곱하여 더한 값
야근피로도를 최소화하도록.
1시간동안 1처리
퇴근시간까지 남은 N시간과 각 일에 대한 작업량 works에 대해 야근 피로도를 최소화한 값을 리턴하는 함수 solution

그리디? 가장 값 큰거 계속 줄여나가면 될듯. pq?
*/
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int w: works) pq.offer(w);
        for(int i = 0; i < n; i++) {
            if(pq.isEmpty()) break;
            int work = pq.poll() - 1;
            if(work > 0)
                pq.offer(work);
        }
        long answer = 0;
        while(!pq.isEmpty()) {
            long v = pq.poll();
            answer += v * v;
        }
        return answer;
    }
}
