

import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // map에 방문 여부 체크
        Map<String, Boolean> map = new HashMap<>();
        for (String s : words) {
            map.put(s, false);
        }

        return bfs(begin, target, map);
    }

    int bfs(String begin, String target, Map<String, Boolean> map) {
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(begin, 0));
        map.put(begin, true);
        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.string.equals(target)) return cur.dist;
            for (String key : map.keySet()) {
                if (map.get(key)) continue; // 이미 지나쳤으면 continue;
                if (cur.string.equals(key)) continue; // 지금 꺼낸거랑 같으면 continue;
                if (diffOne(cur.string, key) == 1) {
                    q.offer(new Point(key, cur.dist + 1));
                    map.put(key, true);
                }
            }
        }
        return 0;
    }


    int diffOne(String a, String b) {
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (arrA[i] != arrB[i]) count++;
        }
        return count;
    }
}

class Point {
    String string;
    int dist;

    Point(String string, int dist) {
        this.string = string;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "[str: " + string + ", dist: " + dist + "]";
    }

}