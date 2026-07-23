
import java.util.*;
class Solution {
    int[] parent; 
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // 비용 적은 거 먼저 연결하기 위해 비용 오름차순으로 정렬
        Arrays.sort(costs, (o1, o2) -> {
            return o1[2] - o2[2];
        });
        
        // 부모 초기화, 섬 100개 존재, 자기자신을 부모로 함.
        parent = new int[101];
        for(int i = 0; i < 101; i++) {
            parent[i] = i;
        }
        
        for(int i = 0; i < costs.length; i++) {
            if(find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }

        return answer;
    }
    // 공통부모인지 검사
    public void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        
        // 부모가 같으면 아무것도 안해
        if(aParent == bParent) {
            return;
        } else {
            // 부모가 다르다면 하나를 다른애의 부모로 만들어버려
            parent[aParent] = bParent;
        }
    }
    public int find(int i) {
        if(parent[i] == i) {
            return i;
        // 초기화된 적이 한번이라도 있다면 재귀를 통해 상위 부모까지 찾아가
        } else {
            // i가 아니라 i의 부모를 넣어야 함을 주의!
            return find(parent[i]);
        }
    }
}