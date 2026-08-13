/*
닉네임 변경할때 기존채팅방에 출력되어있던 닉네임도 전부 변경
uid를 해시맵으로 저장해서 바뀐이름도 꺼내오자
리스트로 누가들어오고나갔는지 저장하자*(닉네임변경은 저장안함)
*/
import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        StringTokenizer st;
        Map<String, String> map = new HashMap<>();
        // 그냥 같은 닉네임이 여러번 들어왓는지 해시맵으로 체크? 그럴거면 string배열을 저장하는게 나을지도?
        // 인아웃은 저장필요없고 짝수번째인지 홀수번째인지 따라서 들어오고 나감을 판단하면 될듯
        // 닉네임 변경만 신경쓰고.
        List<String[]> chat = new ArrayList<>();
        Map<String, Boolean> in = new HashMap<>();
        for(String r: record) {
            
            st = new StringTokenizer(r);
            String enter = st.nextToken();
            String uid = st.nextToken();
            String name = "";
            
            
            
            if(!enter.equals("Leave")) 
                name = st.nextToken();
            if(enter.equals("Enter")) {
                map.put(uid, name);
                in.put(uid, true);
                chat.add(new String[]{uid, "true"});
            } else if(enter.equals("Change")) {
                map.put(uid, name);
            } else {
                in.put(uid, false);
                chat.add(new String[]{uid, "false"}); // "true", "false" 저장
            }
        }
        String[] result = new String[chat.size()];
        int idx = 0;
        for(String[] line: chat) {
            String uid = line[0];
            String flag = line[1];
            result[idx++] = map.get(uid) + (flag.equals("true") ? "님이 들어왔습니다." : "님이 나갔습니다.");
        }
        return result;
    }
}