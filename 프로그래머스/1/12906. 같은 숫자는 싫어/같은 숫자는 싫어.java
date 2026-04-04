import java.util.*;

import java.util.*;
public class Solution {
    public int[] solution(int []arr) {
        
        // 리스트를 만들어서 prev와 같으면 넣지 않아
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        int prev = arr[0];
        for(int i = 1 ; i < arr.length; i++) {
            if(arr[i] == prev) {
                continue;
            } else {
                list.add(arr[i]);
                prev = arr[i];
            }
        }
        int[] answer = new int[list.size()];
        
        for(int i = 0;i < list.size();i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}