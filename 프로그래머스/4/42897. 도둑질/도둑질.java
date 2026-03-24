

import java.math.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int N = money.length;
        int[] dp1 = new int[N];
        // 첫집을 털고 안털고를 나눠서 생각을 해보자
        // case 1
        dp1[0] = money[0];
        dp1[1] = 0; // 첫번째 값을 채웠으니 두번째 값은 없는셈 침
        dp1[2] = dp1[0] + money[2];
        // 첫번째꺼를 포함한 상황이라 마지막칸은 뺌
        for (int i = 3; i < N - 1; i++) {
            dp1[i] = Math.max(
                    dp1[i - 1] // 내 직전꺼를 더하고 나는 안더함
                    , Math.max(dp1[i - 2] + money[i], dp1[i - 3] + money[i]));
        }

        // case 2
        int[] dp2 = new int[N];
        dp2[0] = 0;
        dp2[1] = money[1];
        dp2[2] = dp2[0] + money[2];

        // 첫번째꺼 넣엇으니까 마지막까지 구하기
        for (int i = 3; i < N; i++) {
            dp2[i] = Math.max(
                    dp2[i - 1]
                    , Math.max(dp2[i - 2] + money[i], dp2[i - 3] + money[i]));
        }

        return Math.max(dp1[N - 2], dp2[N - 1]);
    }
}