

import java.lang.*;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 후보가 1명이면(다솜이 혼자면) 0출력하고 리턴
        if (N == 1) {
            System.out.println(0);
            return;
        }
        ArrayList<Integer> votes = new ArrayList<>();
        int dasom = Integer.parseInt(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            votes.add(Integer.parseInt(br.readLine()));
        }
        votes.sort(Comparator.reverseOrder());
        // 이미 나머지 후보들의 최댓값보다도 크면 0출력하고 리턴
        if (dasom > votes.get(0)) {
            System.out.println(0);
            return;
        }
        int maesuCnt = 0;
        while (dasom <= votes.get(0)) {
            dasom += 1;
            votes.set(0, votes.get(0) - 1);
            votes.sort(Comparator.reverseOrder());
            maesuCnt++;
        }
        System.out.println(maesuCnt);
    }

}