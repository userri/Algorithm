
import java.util.*;
class Solution {
    public int[] solution(String s) {
        
        // 원소별 누적 등장수 저장
        Map<Integer, Integer> map = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(s, ",{}");
        while(st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int[] result = new int[map.size()];
        int last = map.size() - 1;
        for(int key: map.keySet()) {
            // 카운트는 1부터 시작하므로 -1해주기
            result[last - (map.get(key) - 1)] = key;
        }
        
        
        return result;
        
    }
}
