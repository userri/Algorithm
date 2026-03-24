

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        // 장르, 재생수 총합
        Map<String, Integer> genrePlaySum = new HashMap<>();
        // 장르, 고유번호와 재생수 맵을 담은 리스트
        // genre와 [고유번호, 재생수] 쌍들을 담은 리스트를 map으로 저장
        Map<String, List<int[]>> genreSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genrePlaySum.put(genres[i], genrePlaySum.getOrDefault(genres[i], 0) + plays[i]);
            genreSongs.putIfAbsent(genres[i], new ArrayList<>());
            genreSongs.get(genres[i]).add(new int[]{i, plays[i]});
        }
        // 장르 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlaySum.keySet());
        sortedGenres.sort((o1, o2) -> genrePlaySum.get(o2) - genrePlaySum.get(o1));

        List<Integer> resultList = new ArrayList<>();

        for (String genre : sortedGenres) {
            List<int[]> songs = genreSongs.get(genre);

            // 장르 내 같은 노래 정렬( 재생수 내림차순, 같다면 ID 오름차순)
            songs.sort((a, b) -> {
                if (a[1] == b[1]) return a[0] - b[0]; // ID 오름차순
                return b[1] - a[1];
            });

            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                resultList.add(songs.get(i)[0]);
            }
        }

        int[] answer = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }
        return answer;

    }

}