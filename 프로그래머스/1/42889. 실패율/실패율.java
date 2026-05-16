/*
스테이지에 도달햇으나 아직 클리어못한 플레이어 수/ 도달한 플레이어 수
실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 리턴
실패율을 treemap으로?
실패율이 같은 스테이지가 있따면 작은 번호의 스테이지가 먼저 오도록

1순위: 실패율(내림차순)
2순위: 번호(오름차순)
stage별로 멈춰있는 count를 세자
작은 스테이지부터 누적 카운트를 전체사용자에서 빼 나가
ex. 1스테이지 실패사용자 1명 -> 1/8
2스테이지 실패 사용자 2명 -> 2/(8-1) (1은 이전 스테이지에서 못올라온 사용자의 누적 수)
3스테이지 실패 사용자 2명 -> 2/(8-3)


*/
import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        // map으로 각 스테이지에 머물러 있는 사용자들을 count하자
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 미리 stage 별로 count를 초기화(최고 스테이지 + 1까지 저장)
        for(int i = 1; i <= N+1; i++) {
            map.put(i, 0);
        }
        
        for(int i = 0; i < stages.length; i++) {
            map.put(stages[i], map.get(stages[i]) + 1);
        }
        
        int accum = 0;
        
        PriorityQueue<Failure> pq = new PriorityQueue<>();
        
        int remainPeople = stages.length;
        // 스테이지별로 각 별로 실패율을 저장해(PriorityQueue)
        for(int stage: map.keySet())
        {
            double failureRate = (map.get(stage)*1.0) / remainPeople;
            if(remainPeople == 0) {
                pq.offer(new Failure(stage, 0));
            } else pq.offer(new Failure(stage, failureRate));
            System.out.println(map.get(stage) + " / " + remainPeople + " = " + failureRate);
            // 다음 스테이지로 넘어간 사람들 업데이트
            remainPeople -= map.get(stage);
        }
        System.out.println(pq);
        
        
        int[] result = new int[N];
        int i = 0;
        while(!pq.isEmpty()) {
            Failure f = pq.poll();
            if(f.stage > N) continue;
            result[i] = f.stage;
            i++;
        }
        return result;
    }
}

class Failure implements Comparable<Failure> {
    int stage;
    double failure;
    public Failure(int stage, double failure) {
        this.stage = stage;
        this.failure = failure;
    }
    
    @Override
    public int compareTo(Failure other) {
        if(this.failure != other.failure) return Double.compare(other.failure, this.failure);
        return (this.stage - other.stage);
    }
    
    @Override
    public String toString() {
        return "[stage: " + stage + ", failure: " + failure + "]";
    }
}
