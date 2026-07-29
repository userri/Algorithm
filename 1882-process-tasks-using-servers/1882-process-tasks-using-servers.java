import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        
        // 가중치, 작업 인덱스, 종료 시간 저장. 종료시간 오름차순
        PriorityQueue<int[]> busy = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        // 가중치, 작업인덱스 저장.
        PriorityQueue<int[]> free = new PriorityQueue<>((a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0]; // 가중치 오름차순
            return a[1] - b[1];
        });

        for(int i = 0; i < servers.length; i++) free.offer(new int[]{servers[i], i});

        int[] answer = new int[tasks.length];
        // 사실 의미상으로는 '현재 시각'이 아닌 '이 task를 시작할 수 있는 가장 이른 시각'
        long curTime = 0; 
        /**
        서버와 task가 둘 다 도착해있어야 작업을 시작할 수 있다
        경우 1. 서버는 있지만 task가 도착안해서 기다려야함
        경우 2. task는 아까 도착했지만 사용가능한 서버가 없어서 기다려야 함
        그래서 task를 시작할 시간을 Math.max를 통해 두 시간들 중 더 큰 것으로 보정해야 한다
         */
        for(int idx = 0; idx < tasks.length; idx++) {
            // curTime이 더 뒤로 밀렷으면 보정해주기
            curTime = Math.max(curTime, idx); // task가 도착하기를 기다리느라 시간 증가
            // 작업 끝난 서버 회수
            while(!busy.isEmpty() && busy.peek()[2] <= curTime) {
                int[] s = busy.poll();
                free.offer(new int[]{s[0], s[1]});
            }
            // 작업 끝난 서버 없으면 시간 업데이트 후 바로 회수
            if(free.isEmpty()) {
                // free가 비었으면 busy는 무조건 들어있음(peek() 가능)
                curTime = busy.peek()[2]; // 서버가 나기를 기다리느라 현재시간 증가
                while(!busy.isEmpty() && busy.peek()[2] <= curTime) {
                    int[] s = busy.poll();
                    free.offer(new int[]{s[0], s[1]});
                }
            }
            // 무조건 free 하나는 만든 상태
            int[] s = free.poll();
            answer[idx] = s[1];
            busy.offer(new int[]{s[0], s[1], (int)(curTime + tasks[idx])});
        }
        return answer;
        
    }
}