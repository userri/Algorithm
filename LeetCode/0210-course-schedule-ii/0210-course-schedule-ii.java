import java.util.*;
class Solution {
    List<List<Integer>> graph;
    int[] state; // 0 진행전, 1 진행중, 2 완료
    int[] order;
    boolean cycle = false;
    int idx = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        state = new int[numCourses];
        order = new int[numCourses];

        graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for(int[] pre: prerequisites) graph.get(pre[0]).add(pre[1]); // 후수과목에 선수과목들을 인접리스트로 저장

        for(int i = 0; i < numCourses; i++) {
            if(state[i] == 0) dfs(i);
            if(cycle) return new int[]{};
        }
        return order;

    }
    void dfs(int cur) {
        state[cur] = 1;
        for(int next: graph.get(cur)) {
            if(state[next] == 0) dfs(next);
            else if(state[next] == 1) {
                cycle = true; return;
            }
        }
        state[cur] = 2;
        order[idx++] = cur;
    }
}