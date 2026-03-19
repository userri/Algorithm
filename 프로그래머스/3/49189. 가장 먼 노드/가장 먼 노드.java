
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        // 1번노드로부터 가장 멀리 떨어진 애들
        // 일단 bfs로 현재 최대 거리와 같은 애들을 싹다 count, 근데 새로운 max 나타나면 answer를 0으로 초기화
        // 헉! 이건 트리가 아니라 circle 있는 그래프인데 방문 처리를 어케하지? => 최단거리니까 그냥 하면 될듯
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<Integer>());
        }
        // 양방향
        for (int[] line : edge) {
            arr.get(line[0]).add(line[1]);
            arr.get(line[1]).add(line[0]);
        }

        // bfs
        int cnt = 0;
        int maxCnt = 0;
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(1, 0));
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int curNum = cur.num;
            int curCnt = cur.cnt;
            if (curCnt == maxCnt) {
                answer++;
            } else if (curCnt > maxCnt) {
                maxCnt = curCnt;
                answer = 1; // 새로운 최장거리가 나타난다면 1로 초기화
            }
            for (int next : arr.get(curNum)) {
                if (!visited[next]) {
                    q.offer(new Point(next, curCnt + 1));
                    visited[next] = true;
                }
            }
        }

        return answer;
    }
}

class Point {
    int num;
    int cnt;

    public Point(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}
