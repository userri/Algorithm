import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        // 각 열의 맨위 인형 행 저장. 인형 없으면 -1 저장
        int[] top = new int[board[0].length];
        // 일단 인형이 없음을 표시
        Arrays.fill(top, -1);
        // deque는 queue로 쓸땐 array deque, 스택으로 쓸 땐 linkedList가 유리
        Deque<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                // j열의 0이 아닌 값이 있는 가장 숫자가 작은(위에 있는) 행 저장
                if(top[j] != -1) continue;
                if(board[i][j] != 0) {
                    top[j] = i;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < moves.length; i++) {
            int now = moves[i] - 1;
            // 열에 인형 없으면 건너뛰기
            if(top[now] == -1) continue;
            
            // 각 열의 최상단의 값을 읽어오기
            int doll = board[top[now]][now];
            // 인형 하나 꺼냈으니 최상단 위치 업데이트
            top[now]++;
            // 위치가 최대 행 번호 넘어가면 -1 저장
            if(top[now] >= board.length) top[now] = -1;
            
            // 비었으면 그냥 넣기
            if(q.isEmpty()) q.offerFirst(doll);
            // peek해서 지금꺼낸거랑 같으면 poll
            else {
                if(q.peek() == doll) {
                    q.poll();
                    result += 2;
                } else q.offerFirst(doll);
            }
        }
        
        return result;
    }
}