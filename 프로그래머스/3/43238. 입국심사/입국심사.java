
/*
파라메트릭 서치라는 힌트는 어디서 얻어야하지?
 -> 1개의 변수에 대해 증가/감소하는 방향으로 됨/안됨이 명백하게 나뉘는 단조성이 보일 때
각 입국심사 심사관마다 걸리는 시간 달라
더 빨리 끝나는 심사관 있으면 그곳으로 가서 심사받아
모든 사람이 심사받는데 걸리는 시간 최소화
*/

import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        // 시간범위: 1~ 가장느린 심사관 * n
        // long타입으로 선언하는게 중요!!
        int max = 0;
        for(int i: times) {
            max = Math.max(max, i);
        }
        long left = 1, right = ((long) max) * n;

        while(left <= right) {
            long mid = (left + right) / 2;
            System.out.println(mid);
            long people = 0L;
            for(int time: times) {
                // 각 시간을 심사관별 시간으로 나눈 몫: 입국심사 받은 사람 수
                people += mid / time;
            }
            if(people >= n) {
                answer = mid;
                right = mid - 1; // mid분이면 충분 -> 더 줄이기
            } else {
                left = mid + 1;
            }
        }

        return answer;
        
    }
}