class Solution {
    public int solution(int storey) {
        int cur = storey;
        int cnt = 0;
        while(cur != 0) {
            int remain = cur % 10;
            if(remain == 0) {
                cur /= 10;
                continue;
            }
            if(remain > 5 || remain == 5 && (cur/10)%10 >= 5) {
                cnt += (10 - remain);
                cur += (10 - remain);
            } else {
                cnt += remain;
                cur -= remain;
            }
            cur /= 10;
        }
        return cnt;
    }
}