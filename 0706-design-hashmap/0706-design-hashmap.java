
import java.util.*;
class MyHashMap {
    
    int head[] = new int[1000]; // head[b] = 버킷 b 체인의 첫 노드 인덱스, 없으면 -1
    int[] keys = new int[200001]; // 노드 i의 key
    int[] vals = new int[200001]; // 노드 i의 value
    int[] next = new int[200001]; // 노드 i의 다음 인덱스, 끝이면 -1
    int cnt = 0; // 다음에 쓸 빈 노드 자리
    // 노드 = 인덱스 하나. 

    public MyHashMap() {
        Arrays.fill(head, -1);
        Arrays.fill(keys, -1);
        Arrays.fill(vals, -1);
        Arrays.fill(next, -1);
    }
    
    public void put(int key, int value) {
        int beforeNode = getNode(key);
        if(beforeNode != -1) {
            vals[beforeNode] = value;
            return;
        }
        // key 등록된 적 없다면
        int i = cnt++;
        keys[i] = key;
        vals[i] = value;
        int b = key%1000;
        // 기존 버킷 첫노드와 next 관계맺기
        next[i] = head[b];
        head[b] = i; // 자기자신을 첫노드로 등록
    }
    
    public int get(int key) {
        int nIdx = getNode(key);
        if(nIdx == -1) return -1;
        return vals[nIdx];
    }
    
    // 지울노드 찾고, 앞뒤 연관노드 next를 업데이트 해야함
    public void remove(int key) {

        int b = key%1000;
        int node = head[b];
        if(node == -1) return; // 키 없으면 아무것도 안함
        int beforeNode = -1, nextNode = -1;

        while(keys[node] != key) {
            // 맞는 키 찾기 직전까지 이전노드값을 계속 저장
            beforeNode = node;
            node = next[node];
            if(node == -1) return; // 끝까지 가도 못찾았으면 리턴
        }
        nextNode = next[node];

        // 자기가 헤드면 헤드만 바꿔주고 나감
        if(head[b] == node) {
            keys[node] = -1;
            vals[node] = -1;
            head[b] = next[node];
            return;
        }

        // System.out.println(beforeNode + ", " + nextNode);
        // 앞뒤노드있으면 그 둘을 연결해야함
        if(nextNode != -1) {
            keys[node] = -1;
            vals[node] = -1;
            next[beforeNode] = nextNode;
        } else {
            // 만약 앞노드만 있으면 뒤 연결할 필요 없음
            keys[node] = -1;
            vals[node] = -1;
            next[beforeNode] = -1;
        }
        // 앞노드 없는 경우(헤드인 경우)는 위에서 이미 처리함
    }
    private int getNode(int key) {
        int b = key%1000; // 버킷번호
        if(head[b] == -1) return -1; // 첫 노드가 없는 경우
        int nIdx = head[b];
        // 노드인덱스로 찾은 애의 키가 내가 찾는애와 일치하지 않는다면
        // 계속해서 next 배열로 찾으러 가
        while(nIdx != -1 && keys[nIdx] != key) {
            nIdx = next[nIdx];
        }
        return nIdx;
    }
}