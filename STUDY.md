# 알고리즘 학습 관리

> **운영 규칙**
> 1. 문제는 **약점 아래에만** 둔다. 독립된 "풀 문제 리스트"를 만들지 않는다 — 그게 쌓여서 죽는다.
> 2. 약점 하나당 **최대 3문제.** 넣으려면 하나 빼야 한다.
> 3. 다 풀어서 약점이 해소되면 **그 항목째로 삭제**한다. 완료 목록은 남기지 않는다.
> 4. 약점은 문제 이름이 아니라 **패턴**으로 적는다.
> 5. 재사용되는 지식은 약점이 해소돼도 **참조 자료**로 옮긴다.

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
| 프로그래머스 60058 괄호 변환 | u = 앞에서 훑어 `(`,`)` 개수가 **처음 같아지는 지점**에서 자르기 — 미제출 |
| LeetCode 253 Meeting Rooms II | 끝나는 시각 == 시작 시각일 때 겹치나 — 미제출 |

---

## W2. 제출 전 디버그 프린트를 안 지운다

이것만으로 4~5회 오답 / 효율성 실패. 문제 없음, **습관만.**

> **제출 전 `Ctrl+F` → `println` 검색.** 3초.

---

## 미학습 유형 (약점 아니라 공백)

| 유형 | 대표 문제 |
|---|---|
| 백트래킹 응용 (조합 + 중복 제거) | 프로그래머스 64064 불량 사용자 ← **다음** |

---

## 유형 인덱스 — 까먹으면 이 문제를 백지에서 다시 푼다

> 골격 코드를 저장하지 않는 이유: 코드를 "보면" 인출 훈련이 안 된다. 문제를 "풀면" 인출이 강제된다.
> 골격이 필요하면 아래 경로의 **내가 푼 코드**가 곧 골격이다.

| 유형 | 문제 | 내 코드 |
|---|---|---|
| BFS (격자 최단) | [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/1844.%E2%80%85%EA%B2%8C%EC%9E%84%E2%80%85%EB%A7%B5%E2%80%85%EC%B5%9C%EB%8B%A8%EA%B1%B0%EB%A6%AC/%EA%B2%8C%EC%9E%84%E2%80%85%EB%A7%B5%E2%80%85%EC%B5%9C%EB%8B%A8%EA%B1%B0%EB%A6%AC.java) |
| 상태 다익스트라 (`dp[r][c][방향]`) | [[카카오 인턴] 경주로 건설](https://school.programmers.co.kr/learn/courses/30/lessons/67259) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/67259.%E2%80%85%EF%BC%BB%EC%B9%B4%EC%B9%B4%EC%98%A4%E2%80%85%EC%9D%B8%ED%84%B4%EF%BC%BD%E2%80%85%EA%B2%BD%EC%A3%BC%EB%A1%9C%E2%80%85%EA%B1%B4%EC%84%A4/%EF%BC%BB%EC%B9%B4%EC%B9%B4%EC%98%A4%E2%80%85%EC%9D%B8%ED%84%B4%EF%BC%BD%E2%80%85%EA%B2%BD%EC%A3%BC%EB%A1%9C%E2%80%85%EA%B1%B4%EC%84%A4.java) |
| 다익스트라 | [배달](https://school.programmers.co.kr/learn/courses/30/lessons/12978) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/12978.%E2%80%85%EB%B0%B0%EB%8B%AC/%EB%B0%B0%EB%8B%AC.java) |
| 플로이드-워셜 | [합승 택시 요금](https://school.programmers.co.kr/learn/courses/30/lessons/72413) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/72413.%E2%80%85%ED%95%A9%EC%8A%B9%E2%80%85%ED%83%9D%EC%8B%9C%E2%80%85%EC%9A%94%EA%B8%88/%ED%95%A9%EC%8A%B9%E2%80%85%ED%83%9D%EC%8B%9C%E2%80%85%EC%9A%94%EA%B8%88.java) |
| MST + 유니온파인드 | [섬 연결하기](https://school.programmers.co.kr/learn/courses/30/lessons/42861) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/42861.%E2%80%85%EC%84%AC%E2%80%85%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0/%EC%84%AC%E2%80%85%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0.java) |
| 두 힙 (스케줄링) | [LeetCode 1882](https://leetcode.com/problems/process-tasks-using-servers/) | [코드](https://github.com/userri/Algorithm/blob/main/LeetCode/1882-process-tasks-using-servers/1882-process-tasks-using-servers.java) |
| 힙 스케줄링 (단일) | [디스크 컨트롤러](https://school.programmers.co.kr/learn/courses/30/lessons/42627) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/42627.%E2%80%85%EB%94%94%EC%8A%A4%ED%81%AC%E2%80%85%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC/%EB%94%94%EC%8A%A4%ED%81%AC%E2%80%85%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC.java) |
| DP 배낭 | SWEA 5215 햄버거 다이어트 | [코드](https://github.com/userri/Algorithm/blob/main/SWEA/D3/5215.%E2%80%85%ED%96%84%EB%B2%84%EA%B1%B0%E2%80%85%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8/%ED%96%84%EB%B2%84%EA%B1%B0%E2%80%85%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8.java) |
| DP LIS | SWEA 3307 최장 증가 부분 수열 | [코드](https://github.com/userri/Algorithm/blob/main/SWEA/D3/3307.%E2%80%85%EC%B5%9C%EC%9E%A5%E2%80%85%EC%A6%9D%EA%B0%80%E2%80%85%EB%B6%80%EB%B6%84%E2%80%85%EC%88%98%EC%97%B4/%EC%B5%9C%EC%9E%A5%E2%80%85%EC%A6%9D%EA%B0%80%E2%80%85%EB%B6%80%EB%B6%84%E2%80%85%EC%88%98%EC%97%B4.java) |
| 비트마스크 TSP | SWEA 1247 최적 경로 | [코드](https://github.com/userri/Algorithm/blob/main/SWEA/D5/1247.%E2%80%85%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%EC%9A%A9%EF%BC%BD%E2%80%853%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%EC%B5%9C%EC%A0%81%E2%80%85%EA%B2%BD%EB%A1%9C/%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%EC%9A%A9%EF%BC%BD%E2%80%853%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%EC%B5%9C%EC%A0%81%E2%80%85%EA%B2%BD%EB%A1%9C.java) |
| 파라메트릭 이분탐색 | [입국심사](https://school.programmers.co.kr/learn/courses/30/lessons/43238) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/3/43238.%E2%80%85%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC/%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC.java) |
| 결정 백트래킹 | [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165) | [코드](https://github.com/userri/Algorithm/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4/2/43165.%E2%80%85%ED%83%80%EA%B2%9F%E2%80%85%EB%84%98%EB%B2%84/%ED%83%80%EA%B2%9F%E2%80%85%EB%84%98%EB%B2%84.java) |

### 그래프 3형제 — 언제 뭘 쓰나
```
한 점 → 모든 점 최단,  V 큼          →  다익스트라   O(E log V)
모든 점 → 모든 점 최단,  V ≤ 400쯤   →  플로이드     O(V³)
전부 하나로 연결 + 최소 비용          →  MST(크루스칼) O(E log E)
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

---

## 보유 무기

TSP(비트마스크 DP) · **두 힙(스케줄링)** · 힙/PQ · BFS · DFS/백트래킹 · MST+유니온파인드(경로압축)
· DP(배낭·LIS·격자) · 파라메트릭 이분탐색 · 다익스트라 · 그리디

**유형 판정은 이미 된다** — 260726 코테 두 문제 모두 유형(힙 스케줄링 / MST+필수간선)을 정확히 잡았다.
