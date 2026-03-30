

/*1과 2 -> 1
1, 3 -> 2
1,4->2
1,5 ->
*/
class Solution
{
    public int solution(int n, int A, int B)
    {
        // while문으로 무조건 다 나눠버리면 안되겠지?
        int answer = 0;
        while(true) {
            if (A%2 == 0 && A != 0) {
                A = A/2;
            } else A = A/2 +1;
            if (B%2 == 0&& B != 0) {
                B = B/2;
            } else B = B/2+1;
            answer++;
            if (A==B) break;
        }
        return answer;
    }
}