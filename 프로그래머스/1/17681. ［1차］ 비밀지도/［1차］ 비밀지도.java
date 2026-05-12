/*
둘다 1이면 됨(& 연산)
*/
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[] cross = new int[n];
        for(int i = 0; i < n; i++) {
            cross[i] = arr1[i] | arr2[i];
        }
        
        String[] answer = new String[n];
        for(int i = 0; i < n; i++) {
            int num = cross[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                int isOne = num & (1 << (n - (j+1)));
                if(isOne != 0) sb.append("#");
                else sb.append(" ");
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}