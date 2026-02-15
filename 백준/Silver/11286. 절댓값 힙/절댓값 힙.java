import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        // 절대값 기준 정렬 위해 map 사용, 같은 절대값 안에서 작은 순서대로 정렬하기 위해 PQ 사용
        Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        for (int i = 0; i <N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                // 맵 비었을 때 0입력이면 0 출력
                if (map.isEmpty()) {
                    sb.append(0).append("\n");
                    continue;
                }
                int firstKey = map.keySet().iterator().next();
                int output = map.get(firstKey).poll();
                sb.append(output).append("\n");
                if (map.get(firstKey).isEmpty()) {
                    map.remove(firstKey);
                }
                continue;
            }
            int ab = Math.abs(n);
            // 아직 키 없으면 pq 넣어주기
            if (!map.keySet().contains(ab)) {
                map.put(ab, new PriorityQueue<>());
            }
            map.get(ab).add(n);
            // for (int key : map.keySet()) {
            //     System.out.print(key + ": ");
            //     System.out.println(map.get(key));
            // }
        }
        System.out.println(sb);
    }
}