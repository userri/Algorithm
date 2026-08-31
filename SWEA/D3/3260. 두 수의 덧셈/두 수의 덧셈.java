
import java.util.*;
import java.io.*;
import java.math.*;

class Solution
{
	public static void main(String args[]) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
            st = new StringTokenizer(br.readLine());
            BigInteger a, b;
            a = new BigInteger(st.nextToken());
            b = new BigInteger(st.nextToken());
            System.out.println("#"+test_case + " " +a.add(b));
		}
	}
}