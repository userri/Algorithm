

import java.util.*;

class Solution {
    static StringBuilder sb = new StringBuilder();
    static Map<String, Integer> map = new TreeMap<>();
    char[] dict;

    public int solution(String word) {
        int answer = 0;
        this.dict = new char[]{'A', 'E', 'I', 'O', 'U'};
        for (int i = 1; i <= 5; i++) {
            char[] arr = new char[i];
            dfs(5, i, 0, dict, arr);
        }
        List<String> list = new ArrayList<>(map.keySet());
        answer = list.indexOf(word) + 1;

        return answer;
    }

    void dfs(int N, int M, int depth, char[] dict, char[] arr) {
        if (depth == M) {
            sb.setLength(0);
            for (char c : arr) {
                sb.append(c);
            }
            map.put(sb.toString(), 0);
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[depth] = dict[i];
            dfs(N, M, depth + 1, dict, arr);
        }

    }
}