/*
1. 문제
- 올바른 괄호문자열 끼리 이어붙인건 올바른 괄호문자열이야
- 문자열을 왼쪽으로 x칸(0~s길이미만)만큼 회전시켰을 때 
    s가 올바른 괄호 문자열이 되게 하는 x의 개수를 리턴

2. 제한
- s 길이: 1이상 1000이하

3. 구현
- 각 괄호를 map으로 저장해서 여는 괄호면 더하고 닫는 괄호면 빼기
- 값이 음수가 되거나 문자열이 끝났는데 0이 아니면 올바르지 않은 괄호
- 괄호개수가 홀수면 올바르지 않은 괄호
- for문으로 회전을 하면서 올바른 괄호일때마다 카운트
- 큐를 쓰자
- map으로 각 짝이 대응되는 닫는 괄호를 저장하자
    


4. 엣지케이스
- 괄호 길이 2의 배수 아님
- 여닫는 개수 안맞음 ex. ()))
- 내부에 안닫힌 괄호있는데 바깥괄호가 닫혀버림 ex. ({)} -> 큐를쓰자
*/

import java.util.*;
class Solution {
    public int solution(String s) {
        if(s.length()%2 != 0) return 0;
        List<Character> list = new ArrayList<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        
        for(int i = 0;i < s.length();i++) {
            list.add(s.charAt(i));
        }
        int answer = 0;
        // i칸씩 회전
        for(int i = 0;i < s.length();i++) {
            Deque<Character> q = new ArrayDeque<>();
            Deque<Character> out = new LinkedList<>();
            boolean isRight = true;
            for(Character c: list) {
                q.add(c);
            }
            // 큐에서 꺼낸게 닫는 괄호일 때, 가장 최근에 꺼낸 거와 짝이 맞아야 해 -> 맞으면 poll
            while(!q.isEmpty()) {
                // 여는 괄호면 일단 out
                if(!map.containsKey(q.peek())) {
                    out.addFirst(q.poll());
                }
                // 닫는 괄호면 가장 최근에 out 된거랑 짝이 맞는지 체크
                else {
                    if(map.get(q.peek()) == out.peek()) {
                        q.poll();
                        out.poll();
                    } else {
                        isRight = false;
                        break;
                    }
                }
            }
            if(isRight) answer++;
            list.add(list.remove(0));
        }
        return answer;
    }
}