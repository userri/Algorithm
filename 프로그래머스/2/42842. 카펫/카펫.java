import java.util.*;
/*
구현전략
한 변이 최소 3 이상
3부터 한 변을 늘려나가면서 (brown + yellow)의 약수일 때만 체크
가로가 세로보다 같거나 기므로 세로를 3부터 늘려나가
(가로-2) * (세로 - 2)가 yellow랑 같을 때 정답
*/
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for(int h = 3; h <= 2_000_000; h++) {
            if((brown + yellow) % h != 0) continue;
            int w = (brown + yellow) / h;
            int nowy = (w-2)*(h-2);
            if(nowy == yellow) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }
        return answer;
    }
}