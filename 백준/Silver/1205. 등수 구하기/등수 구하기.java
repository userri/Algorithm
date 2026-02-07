


import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        // 태수의 새로운 점수. 이전점수보다 좋을 때만 점수 바뀜
        int taesu = Integer.parseInt(st.nextToken());
        // p 밖의 등수면 -1 출력
        int P = Integer.parseInt(st.nextToken());

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
        } else {
            System.out.println(1);
            return;
        }
        ArrayList<Integer> scores = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            scores.add(num);
        }
        scores.add(taesu);
        // 점수 정렬
        scores.sort(Collections.reverseOrder());

        ArrayList<Integer> grades = new ArrayList<>();
        // 1등을 미리 저장
        grades.add(1);
        for (int i = 1; i < N+1; i++) {
            // 이전꺼보다 뒤에꺼가 크다면 인덱스+1를 저장
            if (scores.get(i) < scores.get(i - 1)) {
                grades.add(i + 1);
            } else {
                // 같다면 이전 애의 등수를 그대로 저장
                grades.add(grades.get(i-1));
            }
        }
//        System.out.println(scores);
//        System.out.println(grades);
        boolean check = false;
        for (int i = 0; i < N + 1; i++) {
            // 일단 같은 점수 나오기 시작할 때 check
            if (taesu == scores.get(i)) {
                check = true;
            }
            // 한번 태수와 같은 점수가 나왔었고 다른 점수가 나오기 시작했거나 마지막이면 출력
            if (check){
                if (taesu != scores.get(i)) {
                    if (i > P) {
                        System.out.println(-1);
                        break;
                    } else {
                        System.out.println(grades.get(i - 1));
                        break;
                    }
                } else if (i == grades.size() - 1) {
                    if (i+1 > P) {
                        System.out.println(-1);
                        break;
                    } else {
                        System.out.println(grades.get(i));
                        break;
                    }
                }
            }
        }
    }
}