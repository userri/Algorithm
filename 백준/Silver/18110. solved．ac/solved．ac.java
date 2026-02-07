import java.util.*;

import java.lang.*;

import java.io.*;

// The main method must be in a class named "Main".

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N= Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<Integer>();

        Deque<Integer> q=new LinkedList<>();

        for (int i=0;i<N ; i++) {

            list.add(Integer.parseInt(br.readLine()));

        }

        Collections.sort(list);

        double cut = Math.round(N*0.15);

        //System.out.println(cut);

        for (Integer num:list) {

            q.add(num);

        }

        for (int i=0; i<cut; i++) {

            q.poll();

            q.pollLast();

        }

        double sum=0;

        for (Integer num : q) {

            sum+=num;

        }

        //System.out.println(sum);

        //System.out.println(q.size());

        System.out.println(Math.round((sum/(1.0*q.size()))));

        

    }

}