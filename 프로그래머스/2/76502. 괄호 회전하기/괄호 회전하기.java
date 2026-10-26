
/*
길이 -1 만큼 회전시켜보고 매번 큐로 확인
*/

import java.util.*;
class Solution {
    public int solution(String s) {
        int N = s.length();
        int answer = 0;
        Deque<Character> q = new ArrayDeque<>();
        List<Character> list = new ArrayList<>();
        for(char c: s.toCharArray()){
            list.add(c);
        }
        
        // 0번 회전부터 N-1 회전까지 검사
        for(int i = 0; i < s.length(); i++) {
            boolean isRight = true;
            
            q.clear();
            for(char c: list) {
                if(c == '(' || c == '[' || c == '{') {
                    q.offer(c);
                    continue;
                } else if(c == ')' || c == ']' || c == '}') {
                    if(q.isEmpty()) {
                        isRight = false;
                        break;
                    }
                    if((q.peekLast() == '(' && c == ')') || (q.peekLast() == '[' && c == ']') || (q.peekLast() == '{' && c == '}')) {
                        q.pollLast();
                    }
                // 괄호 외에는 건너뛰기
                } else continue;
            }
            // 회전
            list.add(list.remove(0));
            
            if(!isRight) continue;
            if(!q.isEmpty()) continue;
            answer++;
        }
        
        return answer;
        
        
        
    }
}