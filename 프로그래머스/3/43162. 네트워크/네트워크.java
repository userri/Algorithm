
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        // i == j(자기자신)일 때 무조건 연결(1)
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (dfs(i, n, computers, visited)) answer++;
        }
        return answer;
    }

    boolean dfs(int st, int n, int[][] computers, boolean[] visited) {
        System.out.println(Arrays.toString(visited));
        if (visited[st]) return false;
        visited[st] = true;
        for (int i = 0; i < n; i++) {
            if (i == st) continue;
            // 연결되어있고 방문한 적이 없다면 dfs
            if (computers[st][i] == 1 && !visited[i]) {
                dfs(i, n, computers, visited);
                visited[i] = true;
            }
        }
        return true;
    }
}