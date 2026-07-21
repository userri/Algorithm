
/*

1. 문제
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째 맵지 않은 음식의 스코빌 지수 * 2)
모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞음
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록.

2. 구현 전략
가장 효율적인 방법
리스트를 매번 정렬하는것이 정녕 맞는지?! 아니면 treemap 또 써야함? 근데 두번째로 라는 인덱스를 가져와야해서 트리는 별로임
priorityqueue를 쓰면 될지도 흠 두개씩 쓰고 정렬시키고
전체에서 K보다 낮은 애들 개수 추적하고잇다가
일단 섞어서 결과 본 다음에 K보다 낮은애들 개수 뺄지말지 결정해. 그다음에 그게 0이 되면 성공!
*/
import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] scoville, int K) {
        // 기본: 작은 순서대로 저장
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int underK = 0;
        for(int s: scoville) {
            if(s < K) underK++;
            q.offer(s);
        }
        int cnt = 0;
        if(underK == 0) return 0;
        while(!q.isEmpty()) {
            int first = q.poll();
            if(first < K) underK--;
            // 더이상 꺼낼 게 없을 때
            if(q.isEmpty() && underK == 0) {
                return -1;
            }
            int second = q.poll();
            if(second < K) underK--;
            int next = first + second * 2;
            
            q.offer(next);
            if(next < K) underK++;
            cnt++;
            if(underK == 0) return cnt;
        }
        return -1;
    }
}