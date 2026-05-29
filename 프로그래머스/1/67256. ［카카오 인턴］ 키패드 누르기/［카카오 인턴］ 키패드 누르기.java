/*
1. 문제요약
- 왼손은 *, 오른손은 #에서 엄지 시작
1. 엄지는 상하좌우로만 이동
2. 왼쪽열은 왼손만
3. 오른쪽열은 오른손만
4. 가운데 열은 두 엄지손가락의 현재 키패드 위치에서 더 가까운 엄지손가락 사용
4-1. 두 엄지손가락 거리 같으면 어느쪽손잡이인지따라

2. 제한
3. 구현
- 배열에 키패드를 저장할까 map으로 열과 행을 저장
*/
import java.util.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        Map<Integer, int[]> keypad = new HashMap<>();
        keypad.put(1, new int[]{0, 0});
        keypad.put(2, new int[]{0, 1});
        keypad.put(3, new int[]{0, 2});
        keypad.put(4, new int[]{1, 0});
        keypad.put(5, new int[]{1, 1});
        keypad.put(6, new int[]{1, 2});
        keypad.put(7, new int[]{2, 0});
        keypad.put(8, new int[]{2, 1});
        keypad.put(9, new int[]{2, 2});
        keypad.put(0, new int[]{3, 1});
        int leftRow = 3;
        int leftCol = 0;
        int rightRow = 3;
        int rightCol = 2;
        
        StringBuilder sb = new StringBuilder();
        for(int n: numbers) {
            if(n == 1 || n == 4 || n == 7) {
                sb.append('L');
                leftRow = keypad.get(n)[0];
                leftCol = keypad.get(n)[1];
            }
            else if(n == 3 || n == 6 || n == 9){
                sb.append('R');
                rightRow = keypad.get(n)[0];
                rightCol = keypad.get(n)[1];
            }
            else {
                int nowRow = keypad.get(n)[0];
                int nowCol = keypad.get(n)[1];
                int leftDist = Math.abs(nowRow - leftRow) + Math.abs(nowCol - leftCol);
                int rightDist = Math.abs(nowRow - rightRow) + Math.abs(nowCol - rightCol);
                if(leftDist < rightDist) {
                    sb.append('L');
                    leftRow = keypad.get(n)[0];
                    leftCol = keypad.get(n)[1];
                }
                else if(leftDist > rightDist) {
                    sb.append('R');
                    rightRow = keypad.get(n)[0];
                    rightCol = keypad.get(n)[1];
                }
                else if(hand.equals("left")) {
                    sb.append('L');
                    leftRow = keypad.get(n)[0];
                    leftCol = keypad.get(n)[1];
                } else {
                    sb.append('R');
                    rightRow = keypad.get(n)[0];
                    rightCol = keypad.get(n)[1];
                }
            }
        }
        return sb.toString();
        
    }
}