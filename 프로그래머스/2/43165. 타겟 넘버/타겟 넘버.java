class Solution {
    int cnt = 0;
    int[] numbers;
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        dfs(numbers.length, 0,target, 0, 0);
        return cnt;
    }
    public void dfs(int N, int depth, int target, int start, int sum) {
        if(depth == N && sum == target) {
            cnt++;
        }
        for(int i = start;i < N;i++) {
            dfs(N, depth+1, target, i+1, sum+numbers[i]);
            dfs(N, depth+1, target, i+1, sum-numbers[i]);
        }
    }
}