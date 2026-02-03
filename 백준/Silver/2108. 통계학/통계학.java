import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(br.readLine()));
        }
        //1. 산술평균
//        System.out.print("산술 : ");
        double sum = 0;
        for (int a : nums) {
            sum += a;
        }
        // 1-1. 양수이면서 정수일 때
        // 1-2. 양수이면서 소수일 때
        // 1-3. 음수이면서 정수일 때
        // 1-4. 음수이면서 소수일 때
        double tempAvg = sum / N;
        if (Math.abs(tempAvg%1) >= 0.5) {
            if (tempAvg < 0) {
                System.out.println((int) tempAvg -1);
            }else System.out.println((int) tempAvg +1);
        } else System.out.println((int) (sum/N));

        // 2. 중앙값 출력
//        System.out.print("중앙: ");
        Collections.sort(nums);
        System.out.println(nums.get(nums.size()/2));

        // 최빈값 출력
//        System.out.print("최빈: ");
        HashMap<Integer, Integer> often = new HashMap<>();
        for (int a : nums) {
            if (often.containsKey(a)) {
                often.put(a, often.get(a) + 1);
            } else often.put(a, 1);
        }
        ArrayList<Integer> maxNums = new ArrayList<>();
        int maxOften = 0;
        for (int value : often.values()) {
            maxOften = Math.max(value, maxOften);
        }
        for (int key : often.keySet()) {
            if (often.get(key) == maxOften) {
                maxNums.add(key);
            }
        }
        Collections.sort(maxNums);
        if (maxNums.size() != 1) {
            System.out.println(maxNums.get(1));
        } else System.out.println(maxNums.get(0));

        // 4. 최대최소 차이
//        System.out.print("범위: ");
        if (N == 1) {
            System.out.println(0);
        } else {
            System.out.println(nums.get(nums.size()-1)-nums.get(0));
        }

    }
}








