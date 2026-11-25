import java.util.*;
class Solution {
    String s;
    public int solution(String s) {
        this.s = s;
        StringBuilder sb = new StringBuilder();
        int answer = 1000;
        
        
        for(int i = 1; i <= s.length(); i++) {
            sb.setLength(0);
            String before = s.substring(0, i);
            int cnt = 1;
            int lastIdx = 0;
            for(int j = i; j + i <= s.length(); j+=i) {
                String now = s.substring(j, j+i);
                if(now.equals(before)) {
                    cnt++;
                } else {
                    if(cnt == 1) {
                        sb.append(before);
                    } else {
                        sb.append(cnt).append(before);
                    }
                    before = now;
                    cnt = 1;
                }
                if(j + i + i > s.length()) {
                    if(cnt == 1) {
                        sb.append(now);
                    } else {
                        sb.append(cnt).append(now);
                    }
                    lastIdx = j + i;
                }
            }
            sb.append(s.substring(lastIdx));
            answer = Math.min(answer, sb.length());
        }
        
        return answer;
    }
}