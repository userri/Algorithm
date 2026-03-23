import java.util.*;

class Solution {
    public int solution(int N, int number)  {
        
        List<Set<Integer>> countList = new ArrayList<>();
        // 어차피 8 넘어가면 -1 반환해야하므로 8개 버킷까지만 구성
        for(int i = 0; i < 9;i++) {
            countList.add(new HashSet<>());
        }
        countList.get(1).add(N); // N 1개로 구성된 수는 N밖에 없음
    
        for(int i = 2;i < 9;i++) {
            Set<Integer> countSet = countList.get(i);
            // i 안에서 두가지 set을 나눠가지도록
            for(int j = 1;j <= i; j++) {
                Set<Integer> preSet = countList.get(j);
                Set<Integer> postSet = countList.get(i-j);
                for(int preNum: preSet) {
                    for(int postNum: postSet) {
                        countSet.add(preNum + postNum);
                        countSet.add(preNum - postNum);
                        countSet.add(preNum * postNum);
                        if(preNum != 0 && postNum != 0){
                            countSet.add(preNum/postNum);
                        }
                    }
                }
            }
            // N이 i개 반복된것도 넣어줘야함
            countSet.add(Integer.parseInt((N+"").repeat(i)));
        }
        for (Set<Integer> sub: countList) {
            if (sub.contains(number)) {
                return countList.indexOf(sub);
            }
        }
        
        return -1;
    }
}