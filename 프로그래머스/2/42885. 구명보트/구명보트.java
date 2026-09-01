/*

큰순서 정렬이랑, 작은순서 정렬로 하나씩 빼면 되지않을까
정렬한뒤 deque로 앞뒤로 빼면되지않을까
*/
import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int cnt = 0;
        Deque<Integer> q = new ArrayDeque<>();
        
        // 객체배열이 아닌 int 배열은 내림차순정렬 Arrays.sort(arr, Collections.reverseOrder()) 사용불가!
        // 오름차순정렬후 deque에 거꾸로 넣기
        Arrays.sort(people);
        // 큰값이 앞에 오도록
        for(int p: people) q.addFirst(p);
        while(!q.isEmpty()) {
            int sum = q.poll();
            if(!q.isEmpty() && (sum + q.peekLast()) <= limit) 
                q.pollLast();
            cnt++;
        }
        
        return cnt;
    }
}