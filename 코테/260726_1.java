// 유형: 우선순위큐
import java.util.*;
public class Solution {
    public long solution(long[] dataSize, long[] processingTime) {

        // 일하는 서버 - 끝나는 시간 오름차순으로 정렬해서 사용가능한 서버 빨리 꺼내도록
        // Long 비교는 a[1] - b[1] 아닌 Long.compare로 하도록 주의!
        PriorityQueue<long[]> busy = new PriorityQueue<>((a,b) -> Long.compare(a[1] - b[1]));
        // 쉬는 서버 - 쉬는 순간부터 모든 서버가 평등해짐. 누적처리량 저장 후 오름차순
        PriorityQueue<Long> free = new PriorityQueue<>();

        long maxAccum = 0;
        
        // 인덱스 i가 곧 시간이므로 별도의 변수 필요 없음
        for(int i = 0; i < dataSize.length; i++) {
            // 사용가능해진 서버를 busy -> free로 옮기기
            // peek 할 때는 비었는지 확인해야함을 주의!
            while(!busy.isEmpty() && busy.peek()[1] <= i) free.offer(busy.poll()[0]);

            // 사용가능한 서버가 없으면 누적처리량 0, 있으면 걔의 누적처리량 가져오기
            long accum = free.isEmpty() ? 0 : free.poll();

            accum += dataSize[i]; // 데이터 할당
            // 업데이트된 누적처리량을 busy 에 넣기('현재시간'에 처리시간을 더해야 함)
            busy.offer(new long[]{accum, i + processingTime[i]});
            maxAccum = Math.max(maxAccum, accum);
        }
        return maxAccum;
    }
}
