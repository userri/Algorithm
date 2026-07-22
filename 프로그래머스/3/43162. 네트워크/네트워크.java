
import java.util.*;
class Solution {
    int N, answer = 0;
    int cnt = 0;
    boolean[] visited;
    List<List<Integer>> graph;
    public int solution(int n, int[][] computers) {
        N = computers.length;
        graph = new ArrayList<>();
        visited = new boolean[N];
        
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                // 인접리스트 추가
                if(computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
                
            }
        }
        for(List<Integer> li: graph) {
            System.out.println(li);
        }
        for(int i = 0; i < N; i++) {
            if(dfs(i)) answer++;
        }
        return answer;
    }
    boolean dfs(int x) {
        if(visited[x]) return false;
        
        visited[x] = true;
        for(int i: graph.get(x)) {
            if(visited[i]) continue;
            dfs(i);
        }
        return true;
    }
}