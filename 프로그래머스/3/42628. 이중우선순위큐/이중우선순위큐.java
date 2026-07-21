/*
명령어로 힙에 숫자 넣고 빼고.
*/
import java.util.*;
class Solution {
    public int[] solution(String[] operations){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        TreeMap<Integer, Integer> reverse = new TreeMap<>(Collections.reverseOrder());
        StringTokenizer st;
        for(String s: operations) {

            st = new StringTokenizer(s);
            char c = st.nextToken().charAt(0);
            int n = Integer.parseInt(st.nextToken());
            if(c == 'I') {
                map.putIfAbsent(n, 0);
                reverse.putIfAbsent(n, 0);
                map.put(n, map.get(n) + 1);
                reverse.put(n, reverse.get(n) + 1);
            } else {
                if(map.isEmpty()) continue;
                if(n == 1) {
                    int min = reverse.firstKey();
                    map.put(min, map.get(min) - 1);
                    if(map.get(min) == 0) {
                        map.remove(min);
                        reverse.remove(min);
                    }
                } else {
                    int max = map.firstKey();
                    map.put(max, map.get(max) - 1);
                    if(map.get(max) == 0)  {
                        map.remove(max);
                        reverse.remove(max);
                    }
                }
            }
        }
        
        
        if(map.size() == 0) return new int[]{0,0};
        else return new int[]{reverse.firstKey(), map.firstKey()};
    }
}