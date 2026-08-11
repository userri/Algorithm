// 내가 이 문제를 풀 때 필요한 사고순서 코드에서 보완해서 알려줘
import java.util.*;
class Solution {
    String[] users, ban;
    Set<Integer> answer = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        // 유저와 밴 개수가 각각 8개 이하이므로 완탐 가능성(8팩토리얼 ?)
        // 유저를 고르는 순서가 중요하지 않으므로 유저의 조합을 비트마스킹으로 Set으로 중복제거 후 구하기
        users = user_id;
        ban = banned_id;
        dfs(0,0);
        
        return answer.size();
    }
    // idx: 현재 검사하는 ban id 번호, mask: 현재 고른 유저 조합
    void dfs(int idx, int mask) {
        if(idx >= ban.length) {
            answer.add(mask);
            return;
        }
        // ban은 dfs 재귀를 통해서만 증가하고 별도의 for문을 쓰지 않음(아마도 유저 고르는 순서 중요하지 않아서 ban 순차적 탐색으로 고정?)
        for(int j = 0; j < users.length; j++) {
            if((mask & (1 << j)) != 0) continue; // 이미 쓴 유저
            if(!matches(users[j], ban[idx])) continue;
            dfs(idx + 1, mask | (1 << j));
        }
    }
    boolean matches(String u, String b) {
        if(u.length() != b.length()) return false;
        for(int i = 0; i < u.length(); i++) {
            if(b.charAt(i)!='*' && b.charAt(i) != u.charAt(i))
                return false;
        }
        return true;
    }
}