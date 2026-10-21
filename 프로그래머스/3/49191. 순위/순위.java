
/*

*/
import java.util.*;

class Solution {
    List<List<Integer>> win;
    List<List<Integer>> lose;
    int n;
    public int solution(int n, int[][] results) {
        this.n = n;
        win = new ArrayList<>();
        lose = new ArrayList<>();
        for(int i = 0;i <= n; i++) {
            win.add(new ArrayList<>());
            lose.add(new ArrayList<>());
        }
        
        for(int[] match: results) {
            win.get(match[0]).add(match[1]);
            lose.get(match[1]).add(match[0]);
        }
        
        int result = 0;
        for(int i = 1; i <= n; i++) {
            int cnt = bfs(i);
            if(cnt == n-1) result++;
        }
        return result;
    }
    
    int bfs(int node) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(node);
        boolean[] visited = new boolean[n+1];
        visited[node] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next: win.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    q.offer(next);
                }
            }
        }
        q.offer(node);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next: lose.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    q.offer(next);
                }
            }
        }
        return cnt;
    }
    
    
}