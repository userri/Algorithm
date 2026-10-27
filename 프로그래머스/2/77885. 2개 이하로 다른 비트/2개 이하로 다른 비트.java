
/*


*/

import java.util.*;
class Solution {
    public long[] solution(long[] numbers) {
        
        long[] answer = new long[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            int len = Long.toBinaryString(numbers[i]).length();
            int oneCount = 0;
            // 연속된 1의 개수를 카운트하고 01111 이면 "2^(1의 개수 - 1)"를 더해서 10111로 만들기
            for(int j = 0; j < len; j++) {
                if((numbers[i] & (1L << j)) != 0) {
                    oneCount++;
                // 0이 나오기 시작하면 탈출
                } else break;
            }
            if(oneCount >= 2) {
                answer[i] = numbers[i] + (long)Math.pow(2, oneCount-1);
            } else answer[i] = numbers[i] + 1;
            // System.out.println(Long.toBinaryString(numbers[i]));
            // System.out.println(Long.toBinaryString(answer[i]));
            
        }
        return answer;
    }
}