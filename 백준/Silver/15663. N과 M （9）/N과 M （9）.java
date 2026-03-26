import java.util.*;

import java.lang.*;

import java.io.*;

// The main method must be in a class named "Main".

class Main {

    static Map<Integer, Integer> map;

    static ArrayList<Integer> keys;

    static int[] arr;

    static boolean[] visit;

    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        arr=new int[M];

        map=new TreeMap<>();

        

        st=new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {

            int n = Integer.parseInt(st.nextToken());

            map.put(n, map.getOrDefault(n,0)+1);

        }

        keys = new ArrayList<>(map.keySet());

        

        dfs(N,M,0);

        System.out.println(sb);

    }

    static void dfs(int N,int M, int depth) {

        if (depth==M) {

            for (int val : arr) {

                sb.append(val).append(" ");

            }

            sb.append("\n");

            return;

        }

        for (int i = 0;i<keys.size();i++) {

            int key = keys.get(i);

            if (!(map.get(key)==0)) {

                map.put(key,map.get(key)-1);

                arr[depth]=key;

                dfs(N,M,depth+1);

                map.put(key,map.get(key)+1);

            }

        }

    }

}