import java.io.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                nums.remove(nums.size() - 1);
            } else nums.add(n);
        }
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        System.out.println(sum);
    }
}








