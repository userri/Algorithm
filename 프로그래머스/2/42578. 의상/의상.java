

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {

        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String[] one : clothes) {
            map.put(one[1], map.getOrDefault(one[1], new ArrayList<>()));
            map.get(one[1]).add(one[0]);
        }
        System.out.println(map);

        int answer = 1;
        for (String s : map.keySet()) {
            answer *= (map.get(s).size()+1);
        }
        return answer - 1;
    }
}