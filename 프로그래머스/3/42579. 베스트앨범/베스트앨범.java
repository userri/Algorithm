

import java.util.*;
import java.math.*;

// 시작: 5:25~

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        // 장르, 재생수 총합
        Map<String, Integer> mapG = new HashMap<>();
        // 장르, 고유번호와 재생수 맵을 담은 리스트
        // ex. 액션 장르: {1: 300, 2, 400}
        Map<String, Map<Integer, Integer>> mapP = new HashMap<>();

        int N = genres.length;
        for (int i = 0; i < N; i++) {
            mapG.put(genres[i], mapG.getOrDefault(genres[i], 0) + plays[i]);
            mapP.put(genres[i], mapP.getOrDefault(genres[i], new HashMap<>()));
            mapP.get(genres[i]).put(i, plays[i]);
        }

        List<String> genreKeySet = new ArrayList<>(mapG.keySet());
        // 재생수 많은 순서대로 내림차순
        genreKeySet.sort((o1, o2) -> mapG.get(o2) - mapG.get(o1));

        List<Integer> list = new ArrayList<>();

        for (String s : genreKeySet) {
            List<Integer> identitySet = new ArrayList<>(mapP.get(s).keySet());
            // 재생수 많은 순서대로 역순
            identitySet.sort((o1, o2) -> mapP.get(s).get(o2) - mapP.get(s).get(o1));
            for (int i = 0; i < Math.min(2, identitySet.size()); i++) {
                list.add(identitySet.get(i));
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}