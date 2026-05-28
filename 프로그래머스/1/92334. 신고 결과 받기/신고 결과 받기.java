/*
1. 문제요약
- 한 유저 여러번 신고는 신고횟수 1회로 처리
- k번 이상 신고되면 게시판 정지, 해당 유저를 신고한 모든 유저에게 정지사실을 메일로 발송
- 각 유저별로 처리결과 메일을 받은 횟수를 배열에 담아

2. 제한

3. 구현
- map으로 key는 신고당한사람, value는 신고당한사람 리스트로 저장
- 신고메일 횟수 받은 건 신고자를 map으로 key는 이름, value는 메일 받은횟수로 저장 
-> 나중에 숫자만 배열로 리턴

*/
import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Set<String>> reportMap = new HashMap<>();
        Map<String, Integer> emailMap = new HashMap<>();
        for(String id: id_list) {
            emailMap.put(id, 0);
        }
        
        for(String id: report) {
            String[] names = id.split(" ");
            String reporter = names[0];
            String accused = names[1];
            reportMap.putIfAbsent(accused, new HashSet<>());
            reportMap.get(accused).add(reporter);
        }
        // reportMap 순회하면서 mail 보내기
        for(String accused: reportMap.keySet()) {
            // k번 이상 신고됐으면 emailMap에서 이메일받은횟수 증가시키기
            if(reportMap.get(accused).size() >= k) {
                for(String reporter: reportMap.get(accused)) {
                    emailMap.put(reporter, emailMap.get(reporter) + 1);
                }
            }
        }
        
        int[] result = new int[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            result[i] = emailMap.get(id_list[i]);
        }
        
        return result;
    }
}