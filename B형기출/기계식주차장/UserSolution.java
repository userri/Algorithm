import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;

class UserSolution {
	int N, M, L; // 2~26, 2~1000, 500~100
	String[][] parkinglot; // 주차된 차들의 이름을 저장
	Map<String, Map<String, int[]>> parked; // 차량번호별로 [주차된 시각, 구역번호, 슬롯번호] 저장
	int[] emptyCnt; // i번째 구역의 빈 슬롯 개수 저장
	// 우선순위 높은 슬롯번호도 저장하려햇으나 어차피 업데이트될때마다 다음을 탐색해야해서 O(n)
	PriorityQueue<TowCar> timeOrder; // 입차시간 오름차순 정렬

	Map<String, TreeSet<String>> parkedSet; // 주차중인 차
	Map<String, TreeSet<String>> towedSet; // 견인된 차

	class TowCar {
		int enterTime;
		String carNum;

		public TowCar(int enterTime, String carNum) {
			this.enterTime = enterTime;
			this.carNum = carNum;
		}
	}

	public void init(int N, int M, int L) {
//      테스트 케이스에 대한 초기화 함수. 각 테스트 케이스의 맨 처음 1회 호출된다.
		// 구역개수, 구역별 슬롯개수, 최장주차가능시간

		this.N = N;
		this.M = M;
		this.L = L;
		parkinglot = new String[N][M];
		parked = new HashMap<>();
		emptyCnt = new int[N];
		for (int i = 0; i < N; i++)
			emptyCnt[i] = M;

		timeOrder = new PriorityQueue<>((a, b) -> a.enterTime - b.enterTime);

		parkedSet = new HashMap<>();
		towedSet = new HashMap<>();
		return;
	}

	// 새 맵의 버킷을 없으면 만들어두는 헬퍼
	void ensureBucket(String fourNum) {
		parked.putIfAbsent(fourNum, new HashMap<>());
		parkedSet.putIfAbsent(fourNum, new TreeSet<>());
		towedSet.putIfAbsent(fourNum, new TreeSet<>());
	}

	public Solution.RESULT_E enter(int mTime, String mCarNo) {
		// 시각 mTime에 차량 번호가 mCarNo인 차량이 입차한다. 그 결과를 RESULT_E 구조체에 저장하고 반환한다.
		// 출차된 차량 또는 견인된 차량의 번호가 전달될수있음
		// 견인된 차량번호는 견인기록삭제
		// RESULT_E: success-주차의 성공여부(성공1, 실패0), localname-주차된 차량의 위치

		Solution.RESULT_E res_e = new Solution.RESULT_E();
		// 견인차량 먼저 정리
		tow(mTime);
		// 가장우선순위 높은 빈 슬롯 찾는 함수. 구역개수 N이 26개. 근데 슬롯개수가 1000
		int[] empty = findEmptySlot();
		int area = empty[0], slot = empty[1];
		String fourNum = mCarNo.substring(3);
		ensureBucket(fourNum); // parked, parkedSet, towedSet 버킷 보장

		// 여기 있다면 견인된 차임
		boolean wasTowed = parked.get(fourNum).containsKey(mCarNo);

		if (area == -1) { // 주차 실패
			if (wasTowed) { // 실패해도 견인기록은 삭제한다
				parked.get(fourNum).remove(mCarNo);
				towedSet.get(fourNum).remove(mCarNo);
			}
			res_e.success = 0;
			return res_e;
		}

		parked.get(fourNum).put(mCarNo, new int[] { mTime, area, slot });
		parkinglot[area][slot] = mCarNo;
		emptyCnt[area]--;

		// 주차set에 넣고 견인 set에서 빼기
		parkedSet.get(fourNum).add(mCarNo);
		towedSet.get(fourNum).remove(mCarNo); // 없으면 그냥 무시됨

		timeOrder.offer(new TowCar(mTime, mCarNo)); // 한번 견인된애가 재견인될수잇도록 또 넣어줘야 함

		res_e.success = 1;
		// slot이 일의자리면 (ex. 1) 0이 두칸채워지고 뒤에 1이 붙어
		res_e.locname = (char) ('A' + area) + String.format("%03d", slot);

		return res_e;
	}

	int[] findEmptySlot() {
		int maxEmpty = 0, area = -1, slot = -1;
		for (int i = 0; i < N; i++)
			// 빈슬롯 0개 아니고 직전 최대빈슬롯개수보다 크면
			if (maxEmpty < emptyCnt[i]) {
				maxEmpty = emptyCnt[i];
				area = i;
			}
		// 빈칸있는슬롯 없으면 return
		if (area == -1)
			return new int[] { -1, -1 };

		for (int i = 0; i < M; i++) {
			if (parkinglot[area][i] == null) {
				slot = i;
				break;
			}
		}
		return new int[] { area, slot };

	}

	public int pullout(int mTime, String mCarNo) {
		tow(mTime);

		// 시각 mTime에 차량 번호가 mCarNo인 차량을 출차한다.
		// 차량이 주차된 경우 주차된 기간을 반환
		// 차량이 견인된 경우 (주차된 기간 + 견인된 기간 * 5) * (-1)을 반환한다 -> 견인기록삭제
		// 차량이 주차되어있지 않고 견인되지 않는 경우는 -1을 반환한다

		String fourNum = mCarNo.substring(3);
		if (!parked.containsKey(fourNum) || !parked.get(fourNum).containsKey(mCarNo))
			return -1;

		// parkinglot parked emptyCnt 업데이트
		int[] value = parked.get(fourNum).get(mCarNo);
		int enterTime = value[0], area = value[1], slot = value[2];

		int totalTime = mTime - enterTime;
		int parkTime = Math.min(L, totalTime);
		int towTime = Math.max(0, totalTime - L);

		// 견인안된차량이라면 주차해제
		if (area != -1) {
			parkinglot[area][slot] = null;
			emptyCnt[area]++;
		}
		parked.get(fourNum).remove(mCarNo);
		parkedSet.get(fourNum).remove(mCarNo); // 에러없이 그냥무시
		towedSet.get(fourNum).remove(mCarNo);

		// 견인 여부는 towTime이 아니라 area가 -1인지로판별
		return area == -1 ? -1 * (parkTime + towTime * 5) : parkTime;
	}

	public Solution.RESULT_S search(int mTime, String mStr) {

		// 시각 mTime에 주차된 차량 또는 견인된 차량 중
		// 차량 번호의 뒷 4자리가 mStr와 일치하는 차량을 우선 순위 순으로 최대 5대 검색한다.
		// 차량번호 XXYZZZZ 형태일 때
		// 우선순위(pq 생각)
		// 1. 주차된 차량이 견인된 차량보다 우선순위 높음
		// 2. XX를 수로 표현할 때 더 낮은 수
		// 3. Y의 알파벳 순서 빠를 때
		// 검색된 차량의 개수를 RESULT_S.cnt에 저장하고 우선순위 순으로 찾은 i번째 차량번호를 carlist[i-1]에 저장
		tow(mTime);
		Solution.RESULT_S res_s = new Solution.RESULT_S();
		ensureBucket(mStr);

		int idx = 0;
		for (String p : parkedSet.get(mStr)) {
			if (idx >= 5)
				break;
			res_s.carlist[idx++] = p;
		}
		for (String t : towedSet.get(mStr)) {
			if (idx >= 5)
				break;
			res_s.carlist[idx++] = t;
		}
		res_s.cnt = idx;

		return res_s;
	}

	void tow(int now) {
		// pq peek한 애의 시간이 견인시간 지나잇을떄
		while (!timeOrder.isEmpty() && now - timeOrder.peek().enterTime >= L) {
			TowCar cur = timeOrder.poll();
			// parked value: int[주차된 시각, 구역번호, 슬롯번호, 견인 여부(1)]

			// map에서 못찾으면 차뺀거니까 continue
			String fourNum = cur.carNum.substring(3);
			// 원래 hashmap은 없는거찾으면 null 반환하긴 함(이미 출차된 상태)
			if (!parked.containsKey(fourNum) || !parked.get(fourNum).containsKey(cur.carNum))
				continue;
			
			int[] v = parked.get(fourNum).get(cur.carNum);
			if(v[0] != cur.enterTime) continue; // 재입차해서 시간 바뀌었을때
			if(v[1] == -1) continue; // 이미 견인됨
			
			parkinglot[v[1]][v[2]] = null;
			emptyCnt[v[1]]++;
			v[1] = -1; // 견인됨을 의미
			v[2] = -1;
			
			// 주차set에서 빼고 견인set에 추가
			parkedSet.get(fourNum).remove(cur.carNum);
			towedSet.get(fourNum).add(cur.carNum);

		}
	}

}
