import java.util.*;
/*
1. 문제
- 각 기능은 진도가 100%일 때 서비스에 반영 가능
- 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있음
    - 뒤에 있는 기능은 앞에 있는 기능이 배포될때 함께 배포됨
- progresses: 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열
- speeds: 각 작업의 개발 속도가 적힌 정수 배열
- 각 배포마다 몇 개의 기능이 배포되는지 return 해라

2. 제한
- 작업의 개수(입력 배열들 길이): 100개 이하
- 작업 진도와 작업속도는 100 미만 자연수
- 배포는 하루에 한 번만, 하루에 끝에 이루어짐
    ex. 95%인 작업의 개발속도가 하루에 4%면 배포는 2일 뒤
    
3. 구현
- 각 작업이 남은 분량을 각 speed로 나눠보고, 나누어떨어지면 그대로, 나누어 떨어지지 않으면 나눈값 +1 해서 작업끝나는 날짜를 구하자
- 작업들이 뭉쳐서 나오는 list를 선언하자
- 각 작업이 끝나는 날짜들을 배열에 넣어놓고, for문으로 순회하면서 내가 내 다음 작업보다 빨리 끝난다면 list에 나만 넣고, 다음작업과 같이, 또는 더 늦게끝난다면 누적해서 다음 턴에 넘기자(5, 3, 1일 순서대로 작업이 끝난다면 5일째에 3개의 작업이 배포됨)

*/

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        // 각 기능의 작업일수 담을 배열
        int[] days = new int[speeds.length];
        for(int i = 0; i < speeds.length;i++) {
            // 나누어 떨어지지 않으면 작업일수 하루 추가해야함
            if((100-progresses[i]) % speeds[i] == 0) {
                days[i] = (100-progresses[i]) / speeds[i];
            } else days[i] = (100-progresses[i]) / speeds[i] + 1;
        }
        // System.out.println(Arrays.toString(days));
        List<Integer> deploy = new ArrayList<>();
        // 배포되지 않은 이전 작업일수 기록할 변수
        int curMax= 0;
        int undeployed = 0;
        for(int i = 0;i < speeds.length-1; i++) {
            // 내 다음 작업보다 빨리 끝나서 deploy 리스트에 넣을 수 있게 된다면,
            // 이전에 누적된 작업 있는지 확인하고 더 큰 걸로 넣어줘야해(이전작업 끝나기를 기다린 상황)
            // 근데 그 누적된 작업이 내 다음 작업보다 크다면? 못넣고 또 누적되는거임
            curMax = Math.max(curMax, days[i]);
            undeployed++;
            // 만약 10 3 11 5
            // 현재를 포함해서 
            // 10이 들어왔어 근데 3보다 커 -> curMax = 현재로 저장하고 다음세대에 맡기는 거야
            // i = 1일 때 10 보다 11이 크니까 넣을수있게 되었어! undeployed는 2니까 2가 맞음
            // curMax는 초기화해서 다음세대부터 다시시작
            // System.out.println("curMax: "+curMax+", 다음: "+days[i+1]);
            if(curMax < days[i+1]) {
                curMax = 0;
                // System.out.println(undeployed+" 들어감");
                deploy.add(undeployed);
                undeployed = 0;
            }
        }
        // 여태 안들어간거 + 맨마지막꺼 한번에 넣어주기
        deploy.add(undeployed+1);
        int[] answer = new int[deploy.size()];
        for(int i = 0;i < deploy.size();i++) {
            answer[i] = deploy.get(i);
        }
        
        return answer;
    }
}