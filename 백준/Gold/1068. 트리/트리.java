import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int remove;
    static ArrayList<ArrayList<Integer>> graph;
    static int leafCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int root=0;
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a == -1) {
                root = i;
                continue;
            }
//           자식으로 가는 방향이니까 단방향
            // 입력받은 애의 자식으로 현재 인덱스인 i를 추가
            graph.get(a).add(i);
        }

        remove = Integer.parseInt(br.readLine());

        if (remove == root) {
            System.out.println(0);
            return;
        }
//        // 초기 graph 출력
//        for (ArrayList<Integer> a : graph) {
//            System.out.println(a);
//        }


        boolean[] visited = new boolean[N + 1];
        removeNode(root, visited);

//        System.out.println();
//        // 노드 삭제 후 출력
//        for (ArrayList<Integer> a : graph) {
//            System.out.println(a);
//        }
        visited = new boolean[N + 1];

        findLeaf(root, visited);
        System.out.println(leafCount);
    }

    private static void findLeaf(int v, boolean[] visited) {
        visited[v] = true;
        if (graph.get(v).isEmpty()) {
            leafCount++;
        }
        for (int a : graph.get(v)) {
            if (!visited[a]) {
                visited[a] = true;
                findLeaf(a, visited);
            }
        }
    }

    private static void removeNode(int v, boolean[] visited) {
        visited[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            if (graph.get(v).get(i) == remove) {
                // 자식노드를 삭제하고 바로 탈출(어차피 다른애랑은 연결 안돼있을테니)
                graph.get(v).remove(i);
                return;
            }
            if (!visited[graph.get(v).get(i)]) {
                visited[graph.get(v).get(i)] = true;
                removeNode(graph.get(v).get(i), visited);
            }
        }
    }


}