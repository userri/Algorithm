import java.util.*;
class Solution {
    public int[] solution(int e, int[] starts) {
        // 약수 계수 계산
        int[] countArr = new int[e + 1];
        
        // i의 배수마다 약수 하나 추가
        for(int i = 1; i <= e; i++) {
            for(int j = i; j <= e; j += i) {
                countArr[j]++;
            }
        }
        
        // 뒤에서부터 i ~ e 중 약수가 가장 많은 수 저장
        // best[i] = i 이상 e 이하에서 약수 개수가 가장 많은 수
        int[] best = new int[e + 1];
        best[e] = e; // 구간 [e~e]는 e하나뿐
        
        for(int i = e - 1; i >= 1; i--) {
            if(countArr[i] >= countArr[best[i + 1]]) {
                best[i] = i;
            } else {
                best[i] = best[i + 1]; // 뒤쪽 1등을 그대로 물려받음
            }
        }
        // 시간복잡도: O(e)
        
        // 각 쿼리는 미리 구한 값을 조회만
        int[] answer = new int[starts.length];
        for(int i = 0; i < starts.length; i++) {
            answer[i] = best[starts[i]];
        }
        
        return answer;
        
    }
}