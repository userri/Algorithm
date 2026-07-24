/*
섬 사이 다리 연결
최소 비용으로 모든 섬 서로 통행 가능하게
최소비용 순으로 정렬
차례차례 연결해서 그룹으로 묶기

*/
import java.util.*;
import java.io.*;
class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // parent[i] : i의 부모를 저장(여기서는 최종조상 저장)
        parent = new int[101];
        // 부모를 자기자신으로 초기화!
        for(int i = 0; i < 101;i++) {
            parent[i] = i;
        }

        // 비용 오름차순으로 정렬(작은 것부터 연결하기 위해)
        Arrays.sort(costs, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        for(int i = 0; i < costs.length; i++) {
            // 부모 다르면 같은 그룹으로 묶고 공통조상 갱신
            if(find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }

        
        return answer;
    }
    void union(int a, int b) {
        int aParent = parent[a];
        int bParent = parent[b];
        if(aParent != bParent) {
            // 각 조상끼리 부모자식 관계로 만들어버려
            parent[aParent] = bParent;
        }
    }
    
    int find(int i) {
        if(parent[i] == i) 
            return i;
        else {
            // 한번이라도 부모가 바뀐 적이 있다면 최종조상 찾아서 돌려줘(지금은 최종조상 애초에 union에서 저장)
            return parent[i] = find(parent[i]);
        }
    }
}