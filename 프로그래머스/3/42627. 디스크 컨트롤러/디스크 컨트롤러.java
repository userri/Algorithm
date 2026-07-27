import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        
        // 시간 오름차순
        Arrays.sort(jobs, (a,b) -> a[0] - b[0]);
        // 처리 양 작은 순서대로
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        
        // 체크한 작업 수, 완료한 작업 수
        int idx = 0 , done = 0;
        // 현재 시각, 반환시간(처리완료 시각 - 요청 시각) 총합
        int curTime = 0, totalTime = 0;
        
        // 아직 작업이 다 완료 안됐다면
        while(done < jobs.length) {
            // 현재 시각 이전에 들어온 애들만 pq에 넣어
            while(idx < jobs.length && jobs[idx][0] <= curTime)
                pq.offer(jobs[idx++]);
            // 만약 pq에 더 넣을 게 없다면: 남은 건 전부 아직 안 넣은 쪽에 있다 : idx는 범위를 초과하지 않음
            if(pq.isEmpty()) {
                // 다음 작업의 요청시간으로 현재 시간을 업데이트
                curTime = jobs[idx][0];
            } else {
                // pq에 작업 남아있다면 처리해
                int[] job = pq.poll();
                curTime += job[1];
                totalTime += curTime - job[0];
                done++;
            }
            
        }
        // idx 범위 안끊기나?
        
        return totalTime / jobs.length;
    }
}
