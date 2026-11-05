class Solution {
    public int[] solution(int e, int[] starts) {

        // ===== 1. 약수 개수 계산 (에라토스테네스의 체 방식) =====
        // countArr[j] = j의 약수 개수
        int[] countArr = new int[e + 1];

        // i를 약수로 갖는 수 = i의 배수들
        // → i의 배수마다 "약수 하나 추가"
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                countArr[j]++;
            }
        }
        // 예: i=2 → 2, 4, 6, 8 ...에 +1
        //     i=3 → 3, 6, 9, 12 ...에 +1
        // 시간복잡도: e/1 + e/2 + e/3 + ... ≈ O(e log e)

        // ===== 2. 뒤에서부터 "i ~ e 중 약수가 가장 많은 수" 저장 =====
        // best[i] = i 이상 e 이하에서 약수 개수가 가장 많은 수
        int[] best = new int[e + 1];
        best[e] = e; // 구간 [e ~ e]는 e 하나뿐

        for (int i = e - 1; i >= 1; i--) {
            // 뒤쪽 1등(best[i+1])과 지금 수 i를 비교
            // >= 인 이유: 동점이면 더 작은 수(지금의 i)가 답이어야 함
            if (countArr[i] >= countArr[best[i + 1]]) {
                best[i] = i;
            } else {
                best[i] = best[i + 1]; // 뒤쪽 1등을 그대로 물려받음
            }
        }
        // 시간복잡도: O(e)

        // ===== 3. 각 쿼리는 미리 구한 값을 조회만 =====
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = best[starts[i]];
        }
        // 쿼리 하나당 O(1)

        return answer;
    }
}