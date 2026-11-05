
class Solution {
    public String solution(String p) {
        if(check(p)) return p;
        else return dfs(p);
    }
    
    boolean check(String s) {
        int cnt = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') cnt++;
            else cnt--;
            if(cnt < 0) return false;
        }
        return cnt == 0 ? true : false;
    }
    
    String dfs(String s) {
        if(s.length() == 0) return s;
        
        int cnt1 = 0, cnt2 = 0;
        String u = "", v = "";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == '(') cnt1++;
            else cnt2++;
            if(cnt1 > 0 && cnt2 > 0 && cnt1 == cnt2) {
                // 균형잡혀있을 때 문자열 분리
                u = s.substring(0, i+1);
                if(i < s.length() - 1) v = s.substring(i + 1, s.length());
                break;
            }
        }
        
        if(check(u)) return u + dfs(v);
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(dfs(v)).append(")");
            for(int i = 1; i < u.length() - 1; i++) {
                sb.append(u.charAt(i) == '(' ? ')' : '(');
            }
            return sb.toString();
        }
    }
}