/*
1. 문제
- prices: 초 단위로 기록된 주식 가격 담긴 배열
- 가격이 떨어지지 않은 기간은 몇초인지 return

2. 제한
- prices의 각 가격은 1 이상 1만 이하 자연수
- prices의 길이는 2 이상 10만 이하

3. 구현
- 배열 내에서 내 이후에 나보다 작은 애들이 몇개 있었는지 세야함
- 그냥 세면 O(N*2) 나올듯
- 그럼 이전 칸이 나랑 같다면 숫자 재활용하고 -1 하기?
- 그래도 숫자가 다다르다면 최악의 시간복잡도... 흠
*/

import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i = 0;i < prices.length-1;i++) {
            int cnt = 0;
            for(int j = i; j < prices.length-1;j++) {
                if(prices[j] >= prices[i]) answer[i]++;
                else break;
            }
        }
        return answer;
    }
}