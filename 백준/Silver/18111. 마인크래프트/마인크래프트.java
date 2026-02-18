import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] nmb = br.readLine().split(" ");
        int N = Integer.parseInt(nmb[0]);
        int M = Integer.parseInt(nmb[1]);
        int B = Integer.parseInt(nmb[2]);
        int[][] matrix = new int[N][M];
        
        int result = Integer.MAX_VALUE;
        int minh = Integer.MAX_VALUE;
        int maxh = Integer.MIN_VALUE;
        Map<Integer, Integer > map = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                minh = Math.min(matrix[i][j], minh);
                maxh = Math.max(matrix[i][j], maxh);
            }
        }

        
        boolean next = false;
        
        for (int height = minh; height<=maxh; height++) {
            int sum = 0;
            next = false;
            int tempb = B;
            next = false;
            // 일단 기준높이보다 높으면 인벤토리에 줍줍
            for (int i = 0; i<N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] <= height) continue;
                    sum += 2*(matrix[i][j] - height);
                    tempb += matrix[i][j] - height;
                }
            }
            // 기준높이보다 모자란 애들 채우기
            for (int i = 0; i<N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] > height) continue;
                    sum += height - matrix[i][j];
                    tempb -= height - matrix[i][j];
                    if (tempb < 0) {
                        next = true;
                        break;
                    }
                }
                if (next) {
                    break;
                }
            }
            if (next) {
                continue;
            }
            result = Math.min(result,sum );
            map.put(height, sum );
        }

        for (int height : map.keySet()) {
            if (map.get(height) == result) {
                System.out.println(result + " " + height);
                return;
            }
        }
        
        
    }
}