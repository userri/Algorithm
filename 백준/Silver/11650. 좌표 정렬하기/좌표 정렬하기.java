import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] list = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            list[i][0] = Integer.parseInt(input[0]);
            list[i][1] = Integer.parseInt(input[1]);
        }
        Arrays.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]!=o2[0]?o1[0]-o2[0]:o1[1]-o2[1];
            }
        });
        for (int[] row : list) {
            System.out.println(row[0] + " " + row[1]);
        }
    }
}