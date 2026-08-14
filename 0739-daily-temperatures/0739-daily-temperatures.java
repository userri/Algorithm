/**
완탐: i입장에서 나보다 큰 놈을 앞으로 찾아간다.
뒤집으면: j에 도착햇을 때, j가 답을 해결해줄수 잇는과거 놈들이누구인가
순서가 보장되니 앞에서부터 뒤지지 않고 뒤에서만 꺼내면된다 -> 스택
아직 답을 못받은 애들만 들고다녀


 */
 import java.util.*;
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] temp = temperatures;
        int[] result = new int[temp.length];

        int max = 0;
        for(int t: temp) max = Math.max(max, t);

        // 숫자와 인덱스를 배열로 넣어
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.offer(new int[]{temp[0] , 0});
        // 일단 스택을 써보자
        for(int i = 1;i < temp.length; i++) {
            int now = temp[i];
            while(!stack.isEmpty() && stack.peekLast()[0] < now) {
                int[] past = stack.pollLast();
                // 꺼낸 애의 인덱스 자리에 현재인덱스와의 차이를 기록
                result[past[1]] = i - past[1];
                // System.out.println(past[0] + "(idx:"+past[1]+") " +"꺼냄");
            }
            if(now == max || i == temp.length - 1) {
                result[i] = 0;
            } else {
                stack.offer(new int[]{temp[i], i});
                // System.out.println(temp[i] + "(idx:"+i+") " +"넣음");
            }
        }

        // System.out.println(Arrays.toString(result));
        return result;
    }
}