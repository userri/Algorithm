

import java.util.*;

/*
피치랑 라이언이 같은 칸에 같은발 점수를 맞히면 어피치가 k점을 가져감
 */
class Solution {
    private Map<Integer, List<int[]>> map = new TreeMap<>(Collections.reverseOrder());
    ;
    private int[] info;
    private int[] arr = new int[11];

    public int[] solution(int n, int[] info) {
        int[] answer = {};
        this.info = info;
        dfs(11, n, 0, 0);

        // map에 점수 들어간적없으면 -1 배열 리턴
        if (map.isEmpty()) return new int[]{-1};

        List<Integer> scores = new ArrayList<>(map.keySet());

//        for (int score : scores) {
//            System.out.println(score + " ");
//            for (int[] arrs : map.get(score)) {
//                System.out.println(Arrays.toString(arrs));
//            }
//        }
//        System.out.println();

        for (int score : scores) {
            List<int[]> scoreArrs = map.get(score);
            scoreArrs.sort((o1, o2) -> {
                // 낮은 점수를 맞힌 수가 큰 순서대로 정렬,
                if (o2[10] != o1[10]) return o2[10] - o1[10];
                else if (o2[9] != o1[9]) return o2[9] - o1[9];
                else if (o2[8] != o1[8]) return o2[8] - o1[8];
                else if (o2[7] != o1[7]) return o2[7] - o1[7];
                else if (o2[6] != o1[6]) return o2[6] - o1[6];
                else if (o2[5] != o1[5]) return o2[5] - o1[5];
                else if (o2[4] != o1[4]) return o2[4] - o1[4];
                else if (o2[3] != o1[3]) return o2[3] - o1[3];
                else if (o2[2] != o1[2]) return o2[2] - o1[2];
                else if (o2[1] != o1[1]) return o2[1] - o1[1];
                return o2[0] - o1[0];
            });
            return scoreArrs.get(0);
        }

        return answer;
    }

    void dfs(int N, int M, int depth, int start) {
        if (depth == M) {
            // 점수 각각 계산
            int apeach = 0;
            int lion = 0;
            for (int i = 0; i < 11; i++) {
                // 라이언이 더 많이 맞혀야만 라이언이 k점을 가져감
                if (info[i] < arr[i]) {
                    lion += 10 - i;
                } else if (info[i] != 0) {
                    // 어피치가 라이언보다 점수가 높거나 같으면서 화살개수가 0이 아니면 k점을 가져감
                    apeach += 10 - i;
                }
            }
            // 어피치 점수 이하면 map에 안넣음
            if (lion > apeach) {
                map.putIfAbsent(lion - apeach, new ArrayList<>());
                map.get(lion - apeach).add(Arrays.copyOf(arr, arr.length));
//                System.out.println("---");
//                for(int key: map.keySet()) {
//                    System.out.print("key:" + key);
//                    for(int[] list:map.get(key)) {
//                        System.out.println(Arrays.toString(list));
//                    }
//                }
//                System.out.println("---");
            }
            return;
        }
        for (int i = start; i < N; i++) { // 맞히는 순서는 상관없음
            arr[i] += 1;
            dfs(N, M, depth + 1, i);
            arr[i] -= 1;
        }
    }
}