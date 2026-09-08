
import java.util.*;
import java.io.*;

class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[2001]; // -1000~1000 -> 0~2000
        
        int loc = 1000; // 시작 위치 보정
        int cnt = 0;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            // 영역이 딱 2가 될 때만 생각하면 될 것 같음


            if(dir == 'L') {
                for(int i = 0; i < n; i++) {
                    arr[--loc] += 1;
                    if(arr[loc] == 2) {
                        cnt++; 
                    }
                }
            } else {
                for(int i = 0; i < n; i++) {
                    arr[loc++] += 1;
                    if(arr[loc-1] == 2){ 
                        cnt++; 
                    }
                }
            }

        }
        System.out.println(cnt);
    }
}