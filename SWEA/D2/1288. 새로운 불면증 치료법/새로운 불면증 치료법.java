import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {

            Map<Integer, Integer> map = new HashMap<>(); // 최초로 본 횟수 저장
            int N = Integer.parseInt(br.readLine());
            int sum = 0;
            int mp = 1;
            while(map.size() < 10) {
                sum += N;
                char[] arr = (sum+"").toCharArray();
                // System.out.println(Arrays.toString(arr));
                for(char c: arr) {
                    int num = (int)(c - '0');
                    if(!map.containsKey(num)) map.put(num, mp);
                    if(map.size() >= 10) break;
                }
                // System.out.print(sum+" ");
                // System.out.println(map);
                mp++;
            }
            System.out.println("#"+test_case+" "+(sum));
        }
    }
}