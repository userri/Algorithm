
import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		for(int test_case = 1; test_case <= 10; test_case++)
		{
        	String s = br.readLine();
            st = new StringTokenizer(br.readLine());
            Deque<Integer> q = new ArrayDeque<>();
            for(int i = 0; i < 8; i++) {
                q.offerLast(Integer.parseInt(st.nextToken()));
            }
            int sub = 1;
            while(q.peekLast() != 0) {
                q.offerLast(Math.max(q.poll() - sub, 0));
                sub = (sub++) % 5 + 1;
            }
            System.out.print("#" + test_case + " ");
            for(int i = 0; i < 8; i++) {
                System.out.print(q.poll() + " ");
            }
            System.out.println();
		}
	}
}