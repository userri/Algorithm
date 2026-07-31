# Algorithm
This is an auto push repository for Baekjoon Online Judge created with [BaekjoonHub](https://github.com/BaekjoonHub/BaekjoonHub).

![streak heatmap](https://raw.githubusercontent.com/userri/Algorithm/streak/assets/streak.svg)

## 🔥 스트릭 히트맵 사용법

이 잔디는 **실제 문제 풀이 커밋만** 집계합니다. 정직한 거울이에요 — 풀면 차고, 안 풀면 빈칸.

### 스트릭이 차는 조건
- **하루에 문제 풀이 커밋이 1개 이상** `main` 브랜치에 올라가면 그날 칸이 채워집니다.
- 연속으로 매일 풀면 **현재 스트릭**이 올라가고, 하루라도 건너뛰면 다음 날 0으로 떨어집니다.
- 날짜 기준은 **KST(한국 시간)**. 자정 전에만 커밋되면 그날로 인정됩니다.

### 어떤 커밋이 "풀이 커밋"으로 잡히나
집계는 **커밋 메시지**로 판단합니다. 아래 둘 다 아니면 풀이 커밋으로 인정됩니다.

- ❌ 저자가 `github-actions[bot]` → 제외 (히트맵 자동 갱신용)
- ❌ 메시지가 `chore:` / `fix:` / `ci:` / `docs:` / `refactor:` / `test:` / `build:` / `style:` 로 시작 → 제외 (유지보수용)
- ✅ 그 외 전부 → **풀이 커밋으로 집계**

BaekjoonHub 자동 커밋(예: `[level 2] Title: 타겟 넘버`, `[Gold I] Title: 외판원 순회`)은 `[`로 시작하므로 항상 잡힙니다.

### 직접(수동) 문제를 올릴 때
BaekjoonHub 없이 손으로 커밋해도 잔디에 잡힙니다. **커밋 메시지만 위 제외 접두어로 시작하지 않으면** 됩니다.

```bash
# ✅ 잔디에 잡힘 — 문제 풀이로 인정
git add "백준/Gold/2098. 외판원 순회"
git commit -m "외판원 순회 풀이"        # 또는 "[Gold I] 외판원 순회" 등

# ❌ 잔디에 안 잡힘 — chore:/fix: 로 시작하면 유지보수로 간주
git commit -m "fix: 외판원 순회 오타 수정"
```

> 팁: 헷갈리면 그냥 **문제 이름으로 시작**하는 메시지를 쓰세요. `chore:`·`fix:` 같은 접두어만 피하면 됩니다.

### 동작 방식
1. `main`에 push가 생기거나 매일 자정(KST)에 GitHub Action이 실행됩니다.
2. `git log`를 KST 기준으로 집계해 히트맵 SVG를 생성합니다. → [`.github/scripts/streak_heatmap.py`](.github/scripts/streak_heatmap.py)
3. SVG는 `main`이 아니라 별도 **`streak` 브랜치**에 저장됩니다. (기여 잔디 오염 방지)
4. 위 이미지는 그 `streak` 브랜치의 SVG를 참조합니다.

> 💡 BaekjoonHub가 재제출/오답 후 정답 등으로 커밋을 놓치는 경우가 있습니다. 그럴 땐 하루가 끝나기 전에 위 방식으로 직접 커밋하면 그날 잔디가 인정됩니다.

<!---LeetCode Topics Start-->
# LeetCode Topics
## Array
| Problem Name | Difficulty |
| ------- | ------- |
| [1882-process-tasks-using-servers](https://github.com/userri/Algorithm/tree/main/LeetCode/1882-process-tasks-using-servers/) | Medium |
## Heap (Priority Queue)
| Problem Name | Difficulty |
| ------- | ------- |
| [1882-process-tasks-using-servers](https://github.com/userri/Algorithm/tree/main/LeetCode/1882-process-tasks-using-servers/) | Medium |
<!---LeetCode Topics End-->