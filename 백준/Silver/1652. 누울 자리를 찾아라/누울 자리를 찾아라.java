


import java.lang.*;
import java.io.*;

/*
정사각형 방
연속해서 2칸 이상 빈칸 찾기 (가로 or 세로)
한 줄에 두 번 누울수도 있음 ..X..
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[][] room = new String[N][N];
        for (int i = 0; i < N; i++) {
            room[i] = br.readLine().split("");
        }
        int row = 0; // 가로눕기 개수
        int col = 0; // 세로눕기 개수
        // 가로니까 열 증가시키면서 검사
        for (int i = 0; i < N; i++) {
            boolean extra = false;
            for (int j = 0; j < N - 1; j++) {
                if (extra) {
                    // 짐 나오면 다시 리셋(새로운자리 셀 수 있음)
                    if (room[i][j].equals("X")) {
                        extra = false;
                    } else {
                        // 연속된 빈자리는 세지않고 넘어감
                        continue;
                    }
                }
                if (room[i][j].equals(".") && room[i][j + 1].equals(".")) {
                    row++;
                    // ..*.. 이런식으로 중간에 짐 나올때까지는 열을 계속 증가시키기. 안나오면 다음 줄로 넘어감
                    extra = true;
//                    System.out.println(i + " " + j);
                }
            }

        }
//        System.out.println();
        // 세로니까 행 증가시키면서 검사
        for (int j = 0; j < N; j++) {
            boolean extra = false;
            for (int i = 0; i < N - 1; i++) {
                if (extra) {
                    // 짐 나오면 다시 리셋(새로운자리 셀 수 있음)
                    if (room[i][j].equals("X")) {
                        extra = false;
                    } else {
                        // 연속된 빈자리는 세지않고 넘어감
                        continue;
                    }
                }
                if (room[i][j].equals(".") && room[i + 1][j].equals(".")) {
                    // 세로 한 줄 체크하고 다음 행(j)으로 넘어가기
                    col++;
                    extra = true;
//                    System.out.println(i + " " + j);
                }

            }
        }
        System.out.println(row + " " + col);

    }

}