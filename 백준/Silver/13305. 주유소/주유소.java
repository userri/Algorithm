import java.util.*;
import java.io.*;
/*
1. 문제
- 출발할때는 기름 없음, 주유소에서 기름 넣고 출발
- 도로 이동 시 1km 마다 1리터 기름 사용
- 도시마다 주유소 리터당 가격 다름
- 맨왼쪽 도시부터 맨오른쪽 도시로 가기 위한 최소 비용
*/
class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] lengths = new int[N-1];
        for(int i = 0;i < N-1;i++) {
            lengths[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int[] oils = new int[N]; // 사실상 마지막 도시의 주유가격은 의미없음
        for(int i = 0;i < N;i++) {
            oils[i] = Integer.parseInt(st.nextToken());
        }

        long minOil = Long.MAX_VALUE;
        long totalPrice = 0;
        for(int i = 0;i < N-1;i++) {
            minOil = Math.min(minOil, oils[i]);
            totalPrice += minOil * lengths[i];
        }
        System.out.println(totalPrice);
        
    }
}