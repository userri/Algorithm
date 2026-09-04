
// 집합(String)을 튜플로 바꿔서 배열에 담아 리턴
// 
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[][] matrix = new int[1000001][];
        
        s = s.substring(2,s.length()-2);
        // System.out.println(s);
        s = s.replace("},{", " ");
        // System.out.println(s);
        
        
        // 원소별 누적 등장수 저장
        Map<Integer, Integer> map = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(s);
        while(st.hasMoreTokens()) {
            StringTokenizer tempSt = new StringTokenizer(st.nextToken(), ",");
            while(tempSt.hasMoreTokens()) {
                int n = Integer.parseInt(tempSt.nextToken());
                map.put(n, map.getOrDefault(n, 0) + 1);
            }
        }
        int[] result = new int[map.size()];
        for(int key: map.keySet()) {
            // 카운트는 1부터 시작하므로 -1해주기
            int last = map.size() - 1;
            result[last - (map.get(key) - 1)] = key;
        }
        
        
        return result;
        
    }
}