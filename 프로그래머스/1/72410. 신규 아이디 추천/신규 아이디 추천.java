/*
1. 문제요약
- 아이디 길이 3자~15자
- 알파벳 소문자, 숫자, 빼기, 밑줄, 마침표만 사용
- 마침표는 처음과 끝에 사용 불가, 연속 불가

1-1. 예시
1. 입력된 아이디의 모든 대문자를 소문자로 치환(아마도 char 차이나는 숫자로 계산하면 될듯)
2. 허용되는 문자들(소문자, 숫자 등) 빼고 다 제거 -> stringBuilder 사용
3. 마침표가 연속된 경우 하나로 치환 -> 방금 들어간걸 저장하고 있거나 peek? sb가 peek이 되나?
4. 마침표가 처음이나 끝이면 제거 -> 인덱스로 0이거나 len - 1인데 마침표면 안넣기
5. 빈 문자열이면 a를 대입
6. 길이가 16자 이상이면 15자로 만들고 위의 과정 진행
7. 2자 이하이면 new_id의 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 붙이기
    근데 a. 이런식이면 어캄? a.이면 4단계에서 제거돼서 a가 됨 aaa를 반환하면 됨
    
*/
import java.util.*;
public class Solution {

    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();
        // 1단계: 대문자 변환
        for (char c : new_id.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char newc = (char) ('a' + c - 'A');
                sb.append(newc);
                // 2단계: 소문자가 아니거나 숫자, 빼기, 밑줄, 마침표가 아니면
            } else if (
                    (c >= 'a' && c <= 'z') ||
                            (c >= '0' && c <= '9') ||
                            c == '-' || c == '_' || c == '.') sb.append(c);
            // 그 외는 건너뛰기
        }
        StringBuilder newsb = new StringBuilder();

        boolean flag = false;
        // 3단계: 연속된 마침표를 하나로 치환
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);

            // 이전에 들어간게 마침표이고 이번차례도 마침표라면 넣을수없어
            if (flag && c == '.') continue;
            else newsb.append(c);

            flag = c == '.';
        }
        sb = new StringBuilder(newsb.toString());
        // 초기화
        newsb.setLength(0);
        // 4단계: 맨앞뒤 마침표 제거
        sb = removeFirstLastDot(sb, newsb);

        // 5단계: 빈문자열이면 a 대입
        if (sb == null) {
            return "aaa";
        }

        // 6단계: 길이 넘치면 자르기
        if (sb.length() >= 16) {
            newsb.append(sb, 0, 15);
            sb = new StringBuilder(newsb.toString());
            newsb.setLength(0);
            sb = removeFirstLastDot(sb, newsb);
        }

        // 7단계: 2자 이하면 반복해서
        char last = sb.charAt(sb.length() - 1);
        while (sb.length() <= 2) sb.append(last);

        return sb.toString();

    }

    private static StringBuilder removeFirstLastDot(StringBuilder sb, StringBuilder newsb) {
        int start = 0;
        int end = sb.length() - 1;
        if (sb.charAt(start) == '.') start += 1;
        if (sb.charAt(end) == '.') end -= 1;
        if (end < 0) end = 0;
        if(start > sb.length() - 1) start = sb.length() - 1;
        if (sb.toString().equals(".")) return null;
        else {
            newsb.append(sb, start, end+1);
            sb = new StringBuilder(newsb.toString());
            newsb.setLength(0);
        }
        return sb;
    }
}
