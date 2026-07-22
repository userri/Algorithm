/*
전력망을 나누는걸 어케할거냐?! 그냥 그때그때 양방향그래프를 무효화하기?
*/
import java.util.*;
class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    boolean[] visited;
    int[][] wires;
    int cnt = 0;
    public int solution(int n, int[][] wires) {
        
        this.wires = wires;
        
        // v1,v2 범위 N까지이므로 N+1번
        for(int i = 0; i < n+1; i++) 
            graph.add(new ArrayList<>());
        for(int i = 0; i < n-1; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < n-1; i++) {{
            // i번째 쌍을 끊고, 각각 v1, v2로 dfs/bfs 하면서 송전탑 개수 구하기
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            // 끊을 간선의 정보를 넘기기
            
            visited = new boolean[n+1];
            cnt = 0;
            dfs(v1, v2);
            int sum1 = cnt;
            
            visited = new boolean[n+1];
            cnt = 0;
            dfs(v2, v1);
            int sum2 = cnt;
            
            answer = Math.min(answer, Math.abs(sum1 - sum2));
        }
        return answer;
    }
    void dfs(int x) {
        if(visited[x]) return;
        cnt++;
        visited[x] = true;
        for(int i: graph.get(x)) {
            dfs(i);
        }
    }
}
