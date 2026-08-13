# 알고리즘 학습 관리

> **운영 규칙**
> 1. 문제는 **약점 아래에만** 둔다. 독립된 "풀 문제 리스트"를 만들지 않는다 — 그게 쌓여서 죽는다.
> 2. 약점 하나당 **최대 3문제.** 넣으려면 하나 빼야 한다.
> 3. 다 풀어서 약점이 해소되면 **그 항목째로 삭제**한다. 완료 목록은 남기지 않는다.
> 4. 약점은 문제 이름이 아니라 **패턴**으로 적는다.
> 5. 재사용되는 지식은 약점이 해소돼도 **참조 자료**로 옮긴다.
> 6. **시간 상한은 유형을 아느냐에 따라 다르다.** 처음 보는 유형 → **20분**, 안 되면 답 보고 이해하고 3일 뒤 백지에서 다시. 한 번 본 유형 → **50분**, 이건 진짜 인출이라 붙는 시간이 곧 학습이다.
>    모르는 유형에 오래 붙이는 건 인출이 아니라 **재발명**이다. 비싸고, 그 유형이 싫어진다. 싫어지면 학습은 거기서 끝난다.
>    단, **상한은 개념형에만 건다.** "뭘 해야 할지 아는데 손이 안 따라가는" 구현형은 상한 없음 — 오래 걸리는 게 정상이고 그 시간이 곧 실력이다.
>    구분법: 막힌 게 **발상**인가 **정리**인가. 발상이면 20분, 정리면 끝까지.

---

## W1. 경계 조건에서 반복적으로 죽는다 ⭐ 최우선

로직은 맞는데 **숫자 하나 / 변수명 하나**로 틀린다. 지금 남은 가장 큰 병목.

**실제 사례 (전부 로직이 아니라 부주의)**
```
visited = 1        → 0            (TSP 시작 상태)
w = 1000           → L            (배낭 루프 범위)
초기화 i < N       → 좌표 범위     (TSP dp 초기화)
dp[N-1]            → max(dp)      (LIS 정답 위치)
시작점 방문처리 누락               (게임 맵 BFS)
busy.peek()[0]     → [2]          (1882, 가중치를 종료시각으로 착각)
minCost += ...     → maxCost      (260726 코테 2번, 복붙 실수)
max 초기값 3       → 0            (260726 코테 2번, 임의값이 답에 섞임)
Arrays.fill(dp[n][m]) → dp[i][j]  (경주로, 루프 변수 자리에 크기를 씀)
sum == target      → >=           (LC209, 문제는 '이상'인데 '같음'으로 읽음)
```

**제출 전 체크**
```
초기값   : visited=0? dp 초기화 범위? 배열 크기 +1?
루프 범위 : <N 인가 <=N 인가 / 상수 대신 변수(L, N) 썼나
정답 위치 : 마지막 값(dp[L])인가 최댓값(max(dp))인가
자료형   : 곱셈·누적에 long 필요한가
```

**추가 규칙 3개 (2026-07-30, 하루에 다 당함)**

> **① 복붙한 블록은 변수명을 전수 확인한다.**
> min 블록을 복사해 max 블록을 만들면 `minCost`가 그대로 남는다. 복붙은 빠른 대신 이 부류 버그가 따라온다.

> **② 초기값은 "불가능한 값"으로.** `max`는 `0`(또는 `Integer.MIN_VALUE`), `min`은 `INF`.
> `3` 같은 임의의 숫자를 쓰면 그 값이 답에 섞여 들어간다.

> **③ 정렬 기준과 조건 검사 기준은 같은 인덱스여야 한다.**
> `new PriorityQueue<>((a,b) -> a[2]-b[2])` 로 정렬했으면 `peek()[2]` 로 검사. 다르면 100% 버그.
> 근본 해결: 매직 인덱스 대신 `static final int W=0, ID=1, END=2;`

| 문제 | 경계 포인트 |
|---|---|
| 프로그래머스 64062 징검다리 건너기 | `consecutive` 초기값 0, `>k` vs `>=k` |
| LeetCode 253 Meeting Rooms II | 끝나는 시각 == 시작 시각일 때 겹치나 — 미제출 |

---

## W2. 제출 전 디버그 프린트를 안 지운다

이것만으로 4~5회 오답 / 효율성 실패. 문제 없음, **습관만.**

> **제출 전 `Ctrl+F` → `println` 검색.** 3초.

**안 읽는 변수는 여기 넣지 않는다** — 실패 방식이 다르다. `println` 은 채점기가 잡아 즉사시키고,
안 읽는 변수는 채점을 통과한 뒤 **사람 눈에만** 걸린다. → 참조자료 ⑩

---

## 미학습 유형 (약점 아니라 공백)

지금 비어 있음. 새 유형을 만나면 여기에 적고, 풀고 나면 아래 유형 인덱스로 옮긴다.

---

## 유형 인덱스 — 까먹으면 이 문제를 백지에서 다시 푼다

> 골격 코드를 저장하지 않는 이유: 코드를 "보면" 인출 훈련이 안 된다. 문제를 "풀면" 인출이 강제된다.
> 골격이 필요하면 아래 경로의 **내가 푼 코드**가 곧 골격이다.
>
> **판정 신호**는 역방향 조회용이다. 유형 이름을 모르는 채로 문제 앞에 앉았을 때, 문제에서 보이는 것으로 유형을 찾아 들어간다.
> 새 문제를 풀면 이 열부터 채운다 — **무엇을 보고 이 유형이라고 판단했는지.** 유형 이름만 적으면 잎만 늘고 가지가 안 생긴다.

| 유형 | 판정 신호 — 문제에서 무엇을 보고 골랐나 | 문제 | 내 코드 |
|---|---|---|---|
| BFS (격자 최단) | 격자 + 이동 비용이 **전부 같음**. 최소 칸 수 | [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/1844.%E2%80%85%EA%B2%8C%EC%9E%84%E2%80%85%EB%A7%B5%E2%80%85%EC%B5%9C%EB%8B%A8%EA%B1%B0%EB%A6%AC/%EA%B2%8C%EC%9E%84%E2%80%85%EB%A7%B5%E2%80%85%EC%B5%9C%EB%8B%A8%EA%B1%B0%EB%A6%AC.java) |
| 상태 다익스트라 (`dp[r][c][방향]`) | 격자인데 이동 비용이 **다름**. 직전 상태(방향)가 다음 비용을 바꿈 | [[카카오 인턴] 경주로 건설](https://school.programmers.co.kr/learn/courses/30/lessons/67259) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/67259.%E2%80%85%EF%BC%BB%EC%B9%B4%EC%B9%B4%EC%98%A4%E2%80%85%EC%9D%B8%ED%84%B4%EF%BC%BD%E2%80%85%EA%B2%BD%EC%A3%BC%EB%A1%9C%E2%80%85%EA%B1%B4%EC%84%A4/%EF%BC%BB%EC%B9%B4%EC%B9%B4%EC%98%A4%E2%80%85%EC%9D%B8%ED%84%B4%EF%BC%BD%E2%80%85%EA%B2%BD%EC%A3%BC%EB%A1%9C%E2%80%85%EA%B1%B4%EC%84%A4.java) |
| 다익스트라 | 한 점 → 모든 점 최단. 간선 가중치 양수, V 큼 | [배달](https://school.programmers.co.kr/learn/courses/30/lessons/12978) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/12978.%E2%80%85%EB%B0%B0%EB%8B%AC/%EB%B0%B0%EB%8B%AC.java) |
| 플로이드-워셜 | 경유점을 바꿔가며 **모든 쌍**을 봐야 함. V ≤ 400쯤 | [합승 택시 요금](https://school.programmers.co.kr/learn/courses/30/lessons/72413) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/72413.%E2%80%85%ED%95%A9%EC%8A%B9%E2%80%85%ED%83%9D%EC%8B%9C%E2%80%85%EC%9A%94%EA%B8%88/%ED%95%A9%EC%8A%B9%E2%80%85%ED%83%9D%EC%8B%9C%E2%80%85%EC%9A%94%EA%B8%88.java) |
| MST + 유니온파인드 | 전부 하나로 연결 + 총비용 최소. **경로는 안 물음** | [섬 연결하기](https://school.programmers.co.kr/learn/courses/30/lessons/42861) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/42861.%E2%80%85%EC%84%AC%E2%80%85%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0/%EC%84%AC%E2%80%85%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0.java) |
| 위상정렬 (DFS 후위) | **순서 제약**(A 다음에 B)이 있고 **불가능한 경우가 존재**. 최단거리를 안 물음 | [LeetCode 210](https://leetcode.com/problems/course-schedule-ii/) | [코드](https://github.com/userri/Algorithm/blob/main/LeetCode/0210-course-schedule-ii/0210-course-schedule-ii.java) |
| 두 힙 (스케줄링) | 원소가 두 상태(대기↔작업중)를 **오감**. 시각마다 전환 | [LeetCode 1882](https://leetcode.com/problems/process-tasks-using-servers/) | [코드](https://github.com/userri/Algorithm/blob/main/LeetCode/1882-process-tasks-using-servers/1882-process-tasks-using-servers.java) |
| 힙 스케줄링 (단일) | 도착 시각이 있는 작업. 매 순간 "지금 가능한 것 중 최선" | [디스크 컨트롤러](https://school.programmers.co.kr/learn/courses/30/lessons/42627) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/42627.%E2%80%85%EB%94%94%EC%8A%A4%ED%81%AC%E2%80%85%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC/%EB%94%94%EC%8A%A4%ED%81%AC%E2%80%85%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC.java) |
| DP 배낭 | 넣거나 만다 + **용량 제한** + 가치 최대 | SWEA 5215 햄버거 다이어트 | [코드](https://github.com/userri/Algorithm/blob/main/SWEA/D3/5215.%E2%80%85%ED%96%84%EB%B2%84%EA%B1%B0%E2%80%85%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8/%ED%96%84%EB%B2%84%EA%B1%B0%E2%80%85%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8.java) |
| DP LIS | 순서 유지 부분 수열. 각 원소를 **앞의 모든 원소와** 비교 | SWEA 3307 최장 증가 부분 수열 | [코드](https://github.com/userri/Algorithm/blob/main/SWEA/D3/3307.%E2%80%85%EC%B5%9C%EC%9E%A5%E2%80%85%EC%A6%9D%EA%B0%80%E2%80%85%EB%B6%80%EB%B6%84%E2%80%85%EC%88%98%EC%97%B4/%EC%B5%9C%EC%9E%A5%E2%80%85%EC%A6%9D%EA%B0%80%E2%80%85%EB%B6%80%EB%B6%84%E2%80%85%EC%88%98%EC%97%B4.java) |
| 비트마스크 TSP | 전부 한 번씩 방문 + **순서가 비용에 영향**. n ≤ 20 | SWEA 1247 최적 경로 | [코드](https://github.com/userri/Algorithm/blob/main/SWEA/D5/1247.%E2%80%85%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%EC%9A%A9%EF%BC%BD%E2%80%853%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%EC%B5%9C%EC%A0%81%E2%80%85%EA%B2%BD%EB%A1%9C/%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%EC%9A%A9%EF%BC%BD%E2%80%853%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%EC%B5%9C%EC%A0%81%E2%80%85%EA%B2%BD%EB%A1%9C.java) |
| 파라메트릭 이분탐색 | 답을 직접 못 구함. "X면 되나?" **판정은 쉽고 단조로움** | [입국심사](https://school.programmers.co.kr/learn/courses/30/lessons/43238) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/43238.%E2%80%85%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC/%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC.java) |
| 누적합 + HashMap | 부분 배열 **합**을 세는데 **음수가 섞임**. 구간 → 점 두 개로 바꿔 Two Sum | [LeetCode 560](https://leetcode.com/problems/subarray-sum-equals-k/) | [코드](https://github.com/userri/Algorithm/blob/main/LeetCode/0560-subarray-sum-equals-k/0560-subarray-sum-equals-k.java) |
| 슬라이딩 윈도우 (투 포인터) | 부분 배열 합/길이인데 **전부 양수**. 늘리면 커지고 줄이면 작아짐 | [LeetCode 209](https://leetcode.com/problems/minimum-size-subarray-sum/) | [코드](https://github.com/userri/Algorithm/blob/main/LeetCode/0209-minimum-size-subarray-sum/0209-minimum-size-subarray-sum.java) |
| 백트래킹 + 정규형 중복제거 | 자리에 배정하는데 **순서가 의미 없음**. n ≤ 10 | [불량 사용자](https://school.programmers.co.kr/learn/courses/30/lessons/64064) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/64064.%E2%80%85%EB%B6%88%EB%9F%89%E2%80%85%EC%82%AC%EC%9A%A9%EC%9E%90/%EB%B6%88%EB%9F%89%E2%80%85%EC%82%AC%EC%9A%A9%EC%9E%90.java) |
| 문자열 파싱 + 해시맵 2패스 | 출력이 과거 로그인데 **나중 이벤트가 과거 출력을 바꿈**. 확정을 미루고 마지막에 한 번 더 훑는다 | [오픈채팅방](https://school.programmers.co.kr/learn/courses/30/lessons/42888) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/42888.%E2%80%85%EC%98%A4%ED%94%88%EC%B1%84%ED%8C%85%EB%B0%A9/%EC%98%A4%ED%94%88%EC%B1%84%ED%8C%85%EB%B0%A9.java) |
| 결정 백트래킹 | 원소마다 선택지가 **고정**(+/−). 모든 조합을 셈 | [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/43165.%E2%80%85%ED%83%80%EA%B2%9F%E2%80%85%EB%84%98%EB%B2%84/%ED%83%80%EA%B2%9F%E2%80%85%EB%84%98%EB%B2%84.java) |

### 그래프 4형제 — 언제 뭘 쓰나
```
한 점 → 모든 점 최단,  V 큼          →  다익스트라   O(E log V)
모든 점 → 모든 점 최단,  V ≤ 400쯤   →  플로이드     O(V³)
전부 하나로 연결 + 최소 비용          →  MST(크루스칼) O(E log E)
거리가 아니라 순서. 사이클이면 불가능  →  위상정렬     O(V + E)
```

### 복잡도, 외울 건 3개뿐
```
힙 offer/poll = log  (완전 이진 트리 높이. V=100만이어도 높이 20)
정렬          = N log N  (반씩 쪼개면 log N층 × 층마다 N개 병합)
해시/유니온파인드(경로압축) = 거의 O(1)
```
나머지 복잡도는 이 셋의 조합: 다익스트라 = 간선 E개 × 힙 log / 크루스칼 = 정렬이 지배.

---

## 참조 자료 (약점 아님 — 필요할 때 열어볼 것)

### ① 복잡도 계산 두 줄
```
바깥 루프 횟수  ×  안쪽에서 만지는 원소 수  =  ?
1억 넘으면 구조를 바꿔야 함
```
> 감각을 기르려 하지 말고 **기계적으로 센다.** 세다 보면 감각이 붙는다.

### ② 자료구조 접근 비용
| 연산 | 비용 | 함정 |
|---|---|---|
| `HashMap.get/put` | **O(1)** | 루프 안 탐색은 거의 다 이걸로 대체 가능 |
| `ArrayList.contains` / 전체 순회 | **O(n)** | ⚠️ 루프 안에 있으면 즉시 O(n²) |
| `PriorityQueue.offer/poll` | O(log n) | `peek`은 O(1) |
| `PriorityQueue` 안에서 특정 원소 찾기 | **O(n)** | ⚠️ 힙은 "탐색"용이 아님 |
| `TreeMap.firstKey/lastKey` | O(log n) | 최소·최대 동시에 필요할 때 |
| `Collections.sort` | O(n log n) | ⚠️ 루프 안에 있으면 즉시 폭발 |

**한 문장:** *"힙과 리스트는 꺼내는 도구지 찾는 도구가 아니다. 찾을 거면 HashMap."*

> **"루프 안에서 찾고 있으면, 밖에서 미리 만들어라."**
> 260726 코테 두 문제를 모두 이걸로 날렸다 (유형 판정도 정답성도 맞았는데 시간초과).
> 1번 두 힙: **50분 → 8ms** / 2번 HashMap 전처리: **90초 → 183ms (494배)**

### ③ 두 힙 골격 (시간축 + 선택기준)
```java
PriorityQueue<long[]> busy = new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1])); // 끝나는 시간
PriorityQueue<Long>   free = new PriorityQueue<>();                                  // 누적/우선순위

for (int i = 0; i < n; i++) {
    while (!busy.isEmpty() && busy.peek()[1] <= i) free.offer(busy.poll()[0]); // 경과분 이동
    long cur = free.isEmpty() ? 0 : free.poll();
    // ... 처리 ...
    busy.offer(new long[]{cur, i + duration});
}
```
각 원소가 힙 사이를 **한 방향으로만** 이동 → 전체 O(n log n).
> "뽑았다 도로 넣기"가 보이면 구조가 틀린 것.
> **원소가 상태를 오가면(busy↔free) 상태별로 힙 하나씩.** 같은 집합에서 최소·최대만 필요하면 `TreeMap` 하나.

### ④ 시간 규칙 3질문
두 힙 계열에서 틀리는 건 구조가 아니라 매번 이 셋이다.
```
1. 시각은 어떻게 흐르나?     (1씩? 이벤트마다 점프?)
2. 한 시각에 몇 개 처리되나?  (1개만? 여러 개 동시 가능?)
3. 언제부터 처리 가능한가?    (도착 시각 제약)
```
> 1882에서 2번을 안 물어서 `curTime++`로 틀렸다. 정답은 `curTime = max(curTime, idx)`.
> **시간이 흐르는 이유는 "기다릴 때"뿐. 할당은 시간 0.**

### ⑤ 긴 반례를 짧게 만들기
리트코드가 준 테케가 500칸이라 손으로 못 볼 때.
```
1. 느려도 확실한 브루트포스를 만든다
2. 일부러 작은 랜덤 입력(원소 3~6개)을 수만 개 돌려 비교
3. 첫 불일치 = 손으로 추적 가능한 최소 반례
```

### ⑥ 자바 코테 상수 최적화 (TLE 날 때만)
```
1. int[][] + Comparator 정렬  →  long[] 인코딩 + Arrays.sort   ← 효과 최대 (약 3배)
2. PriorityQueue<int[]>       →  PriorityQueue<Long> (인코딩)
3. HashMap<Integer, X>        →  int[] (키가 작은 정수면)
4. List<Integer>              →  int[]
5. String +=                  →  StringBuilder
6. Scanner                    →  BufferedReader
7. 깊은 재귀                  →  반복문
```
> **알고리즘 개선이 10,000배, 상수 최적화는 2~3배.** TLE면 먼저 알고리즘을 의심할 것.
> 통과했는데 리트코드 퍼센타일이 낮은 건 무시해도 된다 (편차가 큼).

### ⑦ 백트래킹 — 시작할 때 두 개를 적는다

백트래킹은 전부 **칸 채우기**다. 코드를 쓰기 전에 이 둘을 적는다.

| 문제 | 칸 (재귀 깊이) | 후보 (for문) |
|---|---|---|
| 타겟 넘버 | 숫자 n개 | `{+, −}` 2개 |
| 불량 사용자 | `banned_id` 8자리 | 유저 8명 |
| N-Queens | 행 8개 | 열 8개 |
| 순열 만들기 | 자리 n개 | 아직 안 쓴 원소 |

칸은 0번부터 **순서대로 채우고 되돌아가지 않는다** → 재귀 깊이. 후보는 **매번 갈라진다** → for문.

> **종료 조건은 칸으로 건다.** `idx == 칸_개수`.
> 반드시 끝에 닿는 게 칸이고, 닿을지 모르는 건 후보 쪽 누적값(`mask`)이다.
> 64064에서 `mask == FULLBIT`으로 걸려다 막혔다 — 유저를 다 안 써도 끝나는 게 정상이었다.

**순서가 의미 없으면** 결과를 정규형으로 눌러 `Set`에 넣는다. 중복을 미리 막으려 하지 말고 만들고 나서 합친다.
`n ≤ 20`이면 집합은 비트마스크 → `Set<Integer>`.

> 제약이 작으면(`n ≤ 10`) 그건 제약이 아니라 **"완탐해라"는 지시문**이다. 알고리즘을 찾으러 가기 전에 `n!` 부터 계산한다.
> 64064에서 이 계산을 건너뛰고 "확정 짓고 좁혀가기"로 한 시간을 썼다. 실제로는 8P8 × 8 = 30만, 여유롭다.

### ⑧ 부분 배열 — 부호를 먼저 본다

| 신호 | 도구 |
|---|---|
| 전부 **양수** (늘리면 커지고 줄이면 작아짐) | 슬라이딩 윈도우 |
| **음수 섞임** (단조성이 깨짐) | 누적합 + HashMap |

제약의 부호 한 줄이 도구를 바꾼다. 문제를 열면 이것부터 확인한다.

**슬라이딩 윈도우 골격** — 바깥 for 가 넓히고, 안쪽 while 이 좁힌다.
```java
int l = 0, sum = 0, best = Integer.MAX_VALUE;
for (int r = 0; r < n; r++) {
    sum += a[r];                       // 오른쪽 확장
    while (sum >= target) {            // 충분한 동안 계속 좁힌다
        best = Math.min(best, r - l + 1);
        sum -= a[l++];
    }
}
```
> 안쪽이 `if` 가 아니라 `while` 인 게 핵심. 한 번 좁혀도 여전히 충분하면 또 좁혀야 한다.
> 두 포인터 다 **오른쪽으로만** 간다. `l++` 은 창을 넓히는 게 아니라 **좁히는** 것 — 여기서 헷갈렸다.

**누적합 + HashMap 골격** — 구간을 점 두 개로 바꾸면 Two Sum 이 된다.
```java
map.put(0, 1);                         // 아무것도 안 더한 시점도 왼쪽 경계 후보
for (int x : nums) {
    sum += x;
    answer += map.getOrDefault(sum - k, 0);   // 먼저 조회
    map.merge(sum, 1, Integer::sum);          // 그다음 자기 등록
}
```
> `prefix[r] - prefix[l] == k` → `prefix[l] == prefix[r] - k`.
> map 에 든 값 하나하나가 **가능한 왼쪽 경계**다. 안쪽 for 문이 조회 한 번으로 접힌 것.
> 순서를 바꾸면 자기 자신을 세고, `map.put(0,1)` 을 빠뜨리면 0번째부터 시작하는 구간을 놓친다.

---

## 보유 무기

TSP(비트마스크 DP) · **두 힙(스케줄링)** · 힙/PQ · BFS · DFS/백트래킹 · MST+유니온파인드(경로압축)
· DP(배낭·LIS·격자) · 파라메트릭 이분탐색 · 다익스트라 · 그리디

**유형 판정은 이미 된다** — 260726 코테 두 문제 모두 유형(힙 스케줄링 / MST+필수간선)을 정확히 잡았다.

### ⑨ 위상정렬 — 시작점을 고르지 않는다

**유형 판단**
```
"A 다음에 B" 순서 제약   +  불가능한 경우가 답에 있음(빈 배열/-1)
거리·비용을 안 물음                    →  위상정렬
```
비용을 물으면 다익스트라·MST 쪽이다. 순서만 물으면 여기.

**사고 흐름 4단계 (210에서 실제로 걸린 순서)**

**1. 간선 방향을 정한다.** `[a,b]` = a 전에 b.
`graph[a] = a의 선수과목들` 로 잡으면 DFS가 **더 깊은 선행 과목**으로 들어간다.

**2. 시작점을 고르려 하지 마라.** ← 210에서 여기서 20분 날림.
"아무도 요구하지 않는 과목부터" 같은 조건을 세우면 외톨이 정점이 통째로 빠진다.
정답은 **전부 돌면서 아직 안 본 것만** DFS. 덩어리가 여러 개여도 자동으로 커버된다.
(유니온파인드와 같은 감각 — 시작점을 고르지 않고 전부 훑는다)

**3. 결과에 넣는 타이밍 = 재귀가 되돌아 나오는 순간(후위).**
그 시점엔 그 정점의 선행 정점이 전부 이미 들어가 있다. 이게 정당성의 전부다.
간선을 1번처럼 잡았으면 **뒤집을 필요 없이** 후위 순서가 곧 답.

**4. 순환 판정 — 상태는 boolean 이 아니라 3개.**
```
0 안 봄  /  1 지금 이 DFS 경로 위(재귀 스택)  /  2 끝나서 결과에 들어감
1을 만나면 → 순환.   2를 만나면 → return, 순환 아님.
```
`0→1→3, 0→2→3` 처럼 **합류하는 DAG** 에서 3을 두 번 만난다. 상태가 2개뿐이면
이걸 순환으로 오판한다. "다시 만남"과 "지금도 스택에 있음"은 다른 사건이다.

**BFS 버전(indegree + 큐)** 도 같은 문제를 푼다. 순환 판정이 더 직관적:
결과 개수 < V 면 순환. 복습 때 이쪽으로 다시 풀면 한 유형을 두 각도로 갖는다.

### ⑩ 버린 접근은 그 자리에서 지운다

중간에 다른 방법으로 갈아타면 앞의 자료구조가 **선언만 남고 안 읽히는 채로** 남는다.
채점은 통과한다. 코드를 사람이 보는 자리(기업 코테·리뷰)에서만 감점된다.

```
println          → 채점기가 잡는다. 오답·효율성 실패로 즉사        → W2
안 읽는 변수      → 채점기는 통과시킨다. 사람이 볼 때만 감점        → 여기
```

**제출 직전에 찾지 마라. 비싸다.** `println` 처럼 검색으로 안 걸린다.
- **갈아타는 그 순간에 지운다.** "이제 이거 안 쓰네"는 그때는 확실히 알고 30분 뒤엔 잊는다.
- 놓쳤으면 **IntelliJ 가 회색으로 죽여서 보여준다.** 검색이 아니라 3초 훑기.
  단 프로그래머스 웹 에디터에는 없다 — 로컬에서 짜고 붙여넣으면 공짜로 얻는다.

실제 사례: 42888 오픈채팅방의 `Map<String,Boolean> in` — `put` 3번, `get` 0번.
입퇴장 여부를 `chat` 에 넣기로 바꾸면서 필요가 없어졌는데 선언이 남았다.
