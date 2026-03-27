import java.util.*;
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        // 일단 while문 돌리면서 시침이 분침과 초침을 따라잡는 상황, 분침이 시침을 따라잡는 상황을 다 더해
        // 그리고 나중에 시-분-초 겹치는 상황이 그 범위에 있다면 빼
        int curh = h1;
        int curm = m1;
        int curs = s2;
        int cnt = 0;
        while(curh <= h2 && curm <= m2 && curs <= h2) {
            // 분침 +1 해가면서 초침이 계속 따라잡는 상황
            if(curh == curm && curh == curs) cnt++;
            else {
                
            }
        }
        return answer;
    }
}