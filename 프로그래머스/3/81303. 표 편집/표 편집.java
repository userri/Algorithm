
import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringTokenizer st; // 두자리 이상의 숫자를 읽기위해 필수!
        
        int[] up = new int[n+2];
        int[] down = new int[n+2];
        for(int i = 1; i <= n; i++) {
            up[i] = i - 1;
            down[i] = i + 1;
        }
        
        Deque<Integer> stack = new ArrayDeque<>();
        int selected = k + 1;
        
        for(String command: cmd) {
            st = new StringTokenizer(command);
            char dir = st.nextToken().charAt(0);
            
            if(dir == 'U') {
                int num = Integer.parseInt(st.nextToken());
                for(int i = 0; i < num; i++) selected = up[selected];
            } else if(dir == 'D') {
                int num = Integer.parseInt(st.nextToken());
                for(int i = 0; i < num; i++) selected = down[selected];
            }else if(dir == 'C') {
                stack.push(selected);
                
                // selected의 아래위끼리를 연결, selected는 과거의 이웃을 가지고 있지만 Z할때 필요
                down[up[selected]] = down[selected];
                up[down[selected]] = up[selected];
                selected = (down[selected] == n + 1) ? up[selected] : down[selected];
            } else {
                int cur = stack.poll();
                up[down[cur]] = cur;
                down[up[cur]] = cur;
            }
            
        }
        char[] result = new char[n];
        Arrays.fill(result, 'O');
        while(!stack.isEmpty()) {
            result[stack.pop() - 1] = 'X';
        }
        return new String(result);
    }
}