
/*
100만자리 이하...
조합으로 한다면 무조건 터짐
이걸어케함
탐욕법...

뭔가 구간별로... 
첫번째 숫자가 있을수있는 구간에서 최댓값이자 가장 앞에있는 걸 골라
나머지 두번째 숫자가 있을 수 있는 구간에서 최댓값이자 가장 앞에 있는 걸 골라...

*/
import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int remain = number.length() - k - 1;
        
        int max = 0;
        int maxIdx = -1;
        // remain이 0이면 마지막 칸 고르는 단계
        while(remain >= 0) {
            // 첫번째로 큰 숫자를 고르기 위해 maxIdx = 0으로 시작
            maxIdx++;
            max = number.charAt(maxIdx) - '0';
            
            // System.out.println((remain+1)+"개 골라야 할 때 max:"+max+", maxIdx:"+maxIdx);
            // k개를 골라야 한다면, 뒷부분 k - 1개를 빼고 나머지가 첫번째 숫자가 될 수 있음
            // 클때만 업데이트해서 가장 큰 걸 골라
            // 길이가 10이고 k = 2개를 빼야할 때, 8개를 골라야하고 뒤에 최소 7자리를 남긴 뒤 idx는 2까지 탐색 
            for(int i = maxIdx; i <= number.length() - 1 - remain; i++) {
                int num = number.charAt(i) - '0';
                if(max < num) {
                    max = num;
                    maxIdx = i;
                    // System.out.println(i+": "+max+" 로 업뎃");
                }
            }
            sb.append(number.charAt(maxIdx));
            remain--;
        }
        
        return sb.toString();
    }
}