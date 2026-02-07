

import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
배열 크기 고정
6
5
4
3
2
1
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int[] hoeksu = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

        // 전체 행 수
        int rows = 2 * A.length();
        int[][] result = new int[rows][];
        int row = 0;
        // 궁합 첫줄 채우기
        // 0행은 배열길이: 이름길이 2배 - 0
        // 1행은 배열길이: 이름길이 2배 - 1
        result[row] = new int[2 * A.length()];

        for (int j = 0; j < A.length(); j++) {
            int aIdx = A.charAt(j) - 'A';
            int bIdx = B.charAt(j) - 'A';
            result[row][2 * j] = hoeksu[aIdx];
            result[row][2 * j + 1] = hoeksu[bIdx];
        }

        row++;
        // 행 끝까지 내려가지 말고 숫자 2개 남았을 때 멈춰야 함
        for (int i = 0; i < rows - 2; i++) {
            result[row] = new int[2 * A.length() - row];
            // 이전 궁합 줄이 5개였으면 4번 계산해야함
            for (int j = 0; j < result[row - 1].length - 1; j++) {
                // 이전 궁합 줄의 숫자 2개씩 더해서 현재 궁합 줄의 결과에 넣기
                int before = result[row - 1][j];
                int after = result[row - 1][j + 1];
                result[row][j] = (after + before) % 10;
            }
            row++;
        }
        System.out.println(result[rows - 2][0] + "" + result[rows - 2][1]);

    }

}