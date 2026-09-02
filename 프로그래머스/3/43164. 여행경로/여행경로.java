

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
	Map<String, Map<String, Integer>> checked = new HashMap<>(); // 특정 경로 이미 썼는지 체크. 다익스트라처럼

	StringBuilder sb = new StringBuilder();
	int CNT;
	String[] arr;
	List<String> list = new ArrayList<>();

	public String[] solution(String[][] tickets) {
		CNT = tickets.length + 1;
		arr = new String[tickets.length + 1];

		// map 채우기
		for (String[] path : tickets) {
			map.putIfAbsent(path[0], new ArrayList<>());
			map.putIfAbsent(path[1], new ArrayList<>());
			map.get(path[0]).add(path[1]);
			checked.putIfAbsent(path[0], new HashMap<>());
			checked.get(path[0]).put(path[1], checked.get(path[0]).getOrDefault(path[1], 0) + 1);
		}

		arr[0] = "ICN";
		dfs("ICN", 1);

		Collections.sort(list);
		String[] result = new String[tickets.length + 1];
		int idx = 0;
		for (int i = 0; i + 3 <= list.get(0).length(); i += 3) {

			result[idx++] = list.get(0).substring(i, i + 3);
		}
		return result;
	}

	void dfs(String cur, int cnt) {
		if (cnt == CNT) {
			sb.setLength(0);
			for (String s : arr)
				sb.append(s);
			list.add(sb.toString());
		}
		for (String next : map.get(cur)) {
			if (map.get(cur).size() == 0)
				continue;
			if (checked.get(cur).get(next) == 0)
				continue;
			checked.get(cur).put(next, checked.get(cur).get(next) - 1);
			arr[cnt] = next;
			dfs(next, cnt + 1);
			checked.get(cur).put(next, checked.get(cur).get(next) + 1);
		}
	}
}