/*
1. 문제
- 여벌 체육복이 있는 학생들이 다른 애들에게 체육복 빌려줌
- 바로 앞 번호/바로 뒷 번호의 학생에게만 체육복을 빌려줄 수 있음
- 체육복을 적절히 빌려 최대한 많은 학생이 수업을 들어야 한다

- 전체 학생수 n, 체육복을 도난당한 학생들의 번호 배열 lost, 여벌체육복이 있는 학생들 번호 배열 reverse
- 체육수업을 들을 수 있는 학생의 최댓값을 return(여벌체육복 있는 학생들 포함)

2. 제한
- 학생수는 2명이상 30명 이하
- 도난당한 학생수 1명 이상 n명 이하, 중복되는 번호 없음
- 여벌 체육복을 가져온 학생이 도난당하면 더이상 빌려줄수없음

3. 구현
- 일단 전체 학생 체육복 배열을 1로 채우고 lost에 있으면 -1, reverse에 있으면 +1 해서 개수를 저장
- 마지막에 체육복이 1개 이상인 학생 수를 리턴
*/
import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n+1];
        Arrays.fill(students, 1);
        for(int l:lost) {
            students[l]--;
        }
        int zero = 0;
        for(int r:reserve) {
            students[r]++;
        }
        for(int s:students) {
            if(s == 0) zero++;
        }
        // 체육복이 0개인 애들중에 한쪽으로부터만 체육복을 빌릴수있는 애들부터 채우기?
        // 더이상 빌려줄수 없게 되면(숫자가 변하지 않으면) 리턴하기
        
        // 수업을 들을수있는 학생수: n명에서 체육복 0개인 학생수 빼기
        int answer = n - zero;
        // 양끝쪽(1번, n번)인 애들 우선으로 빌려주기
        if(students[1] == 0 && students[2] == 2) {
            students[1] = 1;
            students[2] = 1;
            zero--;
            answer++;
        }
        if(students[n] == 0 && students[n-1] == 2) {
            students[n] = 1;
            students[n-1] = 1;
            zero--;
            answer++;
        }
        while(true) {
            // System.out.println(Arrays.toString(students));
            int preAnswer = answer;
            for(int i = 2;i<=n-1;i++) {
                // 0개인 애들 중에서
                if(students[i] != 0) continue;
                // 한쪽만 2일때만 우선해서 빌려주기(최대한 많은 애들이 빌리기 위해)
                if(students[i-1] == 2 && students[i+1] != 2) {
                    students[i] = 1;
                    students[i-1] = 1;
                    zero--;
                    answer++;
                } else if(students[i-1] != 2 && students[i+1] == 2) {
                    students[i] = 1;
                    students[i+1] = 1;
                    zero--;
                    answer++;
                }
            }
            // 
            if(answer == preAnswer) break;
        }
        // 만약  2 0 2 0 2 0 이런식으로 0 양쪽에 2만 잇어서 고착화된 상태라면 그냥 바로 옆꺼 넣어주기
        if(zero != 0) {
            for(int i = 2;i < n;i++) {
                if(students[i] == 0 && students[i-1] == 2) {
                    students[i] = 1;
                    students[i-1] = 1;
                    answer++;
                }
            }
        }
        // System.out.println(Arrays.toString(students));
        // 0번째 학생 빼기
        return answer;
    }
}