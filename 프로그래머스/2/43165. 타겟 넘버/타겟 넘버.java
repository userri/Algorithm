/*
구현전략
백트래킹으로 더해서 들어갔다가 빼서 나왔다가 등등
깊이가 찼을때 for문할까 아니면 파라미터로 sum을 넘길까
 */
class Solution {
    int[] numbers;
    int answer, N, target;
    boolean[] visited;
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.N = numbers.length;
        visited = new boolean[N];
        this.target = target;

        backtracking(0, 0);

        return answer;
    }
    void backtracking(int depth, int sum) {
        if(depth == N) {
            if(sum == target) {
                answer++;
            }
            return;
        }
        backtracking(depth+1, sum + numbers[depth]);
        backtracking(depth + 1, sum - numbers[depth]);
    }
}
