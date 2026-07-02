import java.util.*;
class Solution {
    public String[] solution(String[] strings, int n) {
        // 기준점 char를 key로 두고 map 생성, value로는 그 안에서 사전순으로 정렬되게 또 Treemap을 넣기
        Map<Character, Map<String, Integer>> map = new TreeMap<>();
        for(String s: strings) {
            char key = s.charAt(n);
            map.putIfAbsent(key, new TreeMap<>());
            map.get(key).put(s, 0);
        }
        
        String[] result = new String[strings.length];
        int idx = 0;
        for(char key: map.keySet()) {
            for(String s: map.get(key).keySet()) {
                result[idx] = s;
                idx++;
            }
        }
        return result;
    }
}