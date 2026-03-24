import java.util.*;

class Solution {
    public boolean solution(String[] pb) {
        
        Map<String, Integer> map = new HashMap<>();
        for(String s: pb) {
            map.put(s, 0);
        }
        for(int i = 0; i < pb.length;i++) {
            for(int j = 0;j < pb[i].length();j++) {
                if(map.containsKey(pb[i].substring(0,j))) {
                    return false;
                }
            }
        }
        return true;
    }
}