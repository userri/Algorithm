

import java.math.*;
import java.util.*;

class Solution {
    private static int depth = 0;

    public int solution(int n, int[][] wires) {
        int answer = -1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
        boolean[] visited;
        // wires 중에서 하나만 disable 시켜 그리고 bfs 시켜...?
        int minDiff = 1000;
        for (int i = 0; i < wires.length; i++) {
            visited = new boolean[n+1];
            dfs(1, wires[i][0], wires[i][1], graph, visited);
            int remain = n - depth;
            minDiff = Math.min(minDiff, Math.abs(depth - remain));
            depth = 0;
        }
        return minDiff;
    }

    private void dfs(int cur, int left, int right, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        if (visited[cur]) return;
        visited[cur] = true;
        depth++; // 방문하면서 깊이 늘리기
        for (int n : graph.get(cur)) {
            if (!(cur == left && n == right) && !(cur == right && n == left)) {
                dfs(n, left, right, graph, visited);
            }
        }
    }
}