/*
1. 문제
- 특정프로세스가 몇번째로 실행되는지 알아내자
규칙
1. 실행 대기 큐에서 대기중인 프로세스를 꺼낸다
2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣는다.
3. 만약 그런 프로세스가 없다면 방금 꺼낸 프로세스를 실행한다
3.1. 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료된다.

2. 구현
- PriorityQueue를 써야할까?
- 각 우선순위의 개수를 map으로 써야할까?
- location은 큐에서 꺼낼때마다 업데이트해야할까?

3. 제한
- 우선순위 개수 1이상 100이하
    - 원소는 1이상 9 이하 정수
    - 숫자 클수록 우선순위 높음
- location은 0이상 프로세스개수-1 이하 의 수를 가짐
    - priorities 의 맨앞에 있는게 0
    
4. 경우의수 나누기
- 내가 제일 큰키가 아님
    - 내 앞꺼를 지움
- 내가 제일 큰 키임
    - 내 앞꺼를 지움
    - 나를 지움
*/
import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        
        // 각 우선순위의 개수를 저장해서 현재 가장 큰 우선순위를 업데이트하자
        Map<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        
        // 프로세스를 관리할 Deque 선언
        Deque<Integer> q = new ArrayDeque<>();
        int curMax = 0;
        
        for(int p:priorities) {
            // 없으면 1개 넣기
            map.put(p, map.getOrDefault(p, 0) + 1);
            curMax = Math.max(curMax, p);
            q.offer(p);
        }
        // 내가 가장 큰키일때 지우는 상황은 어케 처리?
        
        int runCnt = 0;
        while(!q.isEmpty()) {
            if(q.peek() == curMax) {
                int n = q.poll();
                map.put(n, map.get(n) - 1);
                // 가장 큰 키 개수 0개 되면 map에서 지우기
                if(map.get(n) == 0) {
                    map.remove(n);
                    // 그다음 큰 key 업데이트
                    for(int key: map.keySet()) {
                        curMax = key;
                        break;
                    }
                }
                // 실행회수 증가
                runCnt++;
                // 방금 실행한게 타겟프로세스였다면(location = 0) break
                if(location == 0) break;
                // 
                else location--;
            } else {
                // 꺼낸 애가 제일 큰 키가 아니었을 때, 실행안하고 맨뒤로 넘김
                q.offer(q.poll());
                if(location == 0) location = q.size()-1;
                else location--;
            }
        }
        
        return runCnt;
    }
}