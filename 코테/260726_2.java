import java.util.*;
public class Solution {
    int[] parent;
    public int[] solution(int n, int[][] network, int[][] serverPair) {
        // 비용 큰 순과 작은 순 정렬 한 번씩 필요 -> N log N

        parent = new int[n+1];
        for(int i = 0; i <= n; i++) parent[i] = i; // 자기자신을 부모로 초기화

        // network와 serverPair 각각 최대,최소 정렬이 필요한데
        // serverPair는 최대,최소를 해시맵으로 저장
        // 과연 50000*A + B를 하면 안겹치는것이 맞는가?! 안전하게 50001 곱하기?
        // int는 21억이므로 long으로 key를 선언해야함
        Map<Long, int[]> map = new HashMap<>();

        // 주어진 pair로 최소,최대값 저장
        for(int[] pair: serverPair) {
            long key = pair[0] * 50001L + pair[1];
            // 최대 cost 10000
            map.put(key, new int[]{10000, 0});
        }
        for(int[] net: network) {
            long key = net[0] * 50001L + net[1];
            if(map.containsKey(key)) {
                map.get(key)[0] = Math.min(map.get(key)[0], net[2]);
                map.get(key)[1] = Math.max(map.get(key)[1], net[2]);
            }
        }

        // 5만개 서버 * cost 1만 이므로 최대 5억이라 int 가능
        int minCost = 0;

        // 주어진 pair로 최솟값 먼저 mst 먼저 연결
        for(int[] pair: serverPair) {
            if(find(pair[0]) != find(pair[1])) {
                union(pair[0], pair[1]);
                long key = pair[0]*50001L + pair[1];
                minCost += map.get(key)[0];
            }
        }

        // 나머지 연결. 비용 오름차순 정렬. N log N
        Arrays.sort(network, (a,b) -> a[2] - b[2]);
        for(int[] line: network) {
            if(find(line[0]) != find(line[1])) {
                union(line[0], line[1]);
                minCost += line[2];
            }
        }


        // 이제 최대값구하기
        // 부모 초기화
        parent = new int[n+1];
        for(int i = 0; i <= n; i++) parent[i] = i;

        int maxCost = 0;
        // 주어진 pair 최댓값 먼저 연결
        for(int[] pair: serverPair) {
            if(find(pair[0]) != find(pair[1])) {
                union(pair[0], pair[1]);
                long key = pair[0]*50001L + pair[1];
                maxCost += map.get(key)[1];
            }
        }
        // 나머지 연결
        Arrays.sort(network, (a,b) -> b[2] - a[2]);
        for(int[] line: network) {
            if(find(line[0]) != find(line[1])) {
                union(line[0], line[1]);
                maxCost += line[2];
            }
        }

        return new int[]{minCost, maxCost};

    }
    void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if(aParent == bParent) return;
        parent[aParent] = bParent;
    }
    int find(int i) {
        if(parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
}
