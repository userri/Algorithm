

/*
ICN 출발
주어진 항공권은 모두 사용해야 함
가능한 경로가 2개 이상일 경우, 알파벳 순서가 앞서는 경로를 return

string builder로 추가해가면서 항공티켓 다 썼을때만 리턴?
*/

import java.util.*;

class Solution {

	// key: 출발지, value: 도착지 리스트
	Map<String, List<String>> map = new HashMap<>();
	Map<String, Map<String, Integer>> checked = new HashMap<>(); // 특정 경로 이미 썼는지 체크. => 중복 경로 있을수 있으므로 숫자저장으로 변경!

	StringBuilder sb = new StringBuilder();
	int CNT;
	String[] arr;
	List<String> list = new ArrayList<>();

	public String[] solution(String[][] tickets) {
		arr = new String[tickets.length + 1];

		// map 채우기
		for (String[] t : tickets) {
			map.putIfAbsent(t[0], new ArrayList<>());
			map.putIfAbsent(t[1], new ArrayList<>());
			map.get(t[0]).add(t[1]);
			checked.putIfAbsent(t[0], new HashMap<>());
			checked.get(t[0]).put(t[1], checked.get(t[0]).getOrDefault(t[1], 0) + 1);
		}

		// 미리 정렬해놓으면 알파벳순으로 탐색하므로 별도 정렬 필요없음
		for (List<String> v : map.values())
			Collections.sort(v);

		arr[0] = "ICN";
		dfs("ICN", 1);

		return arr;
	}

	boolean dfs(String cur, int cnt) {
		if (cnt == arr.length)
			return true;
		for (String next : map.get(cur)) {
			if (checked.get(cur).get(next) == 0)
				continue;
			checked.get(cur).put(next, checked.get(cur).get(next) - 1);
			arr[cnt] = next;
			if (dfs(next, cnt + 1))
				return true; // 첫 정답이 최종 정답이므로 바로 리턴
			checked.get(cur).put(next, checked.get(cur).get(next) + 1);
		}
		return false;
	}
}
