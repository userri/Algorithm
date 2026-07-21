# Algorithm
This is an auto push repository for Baekjoon Online Judge created with [BaekjoonHub](https://github.com/BaekjoonHub/BaekjoonHub).

![streak heatmap](https://raw.githubusercontent.com/userri/Algorithm/streak/assets/streak.svg)

## 🔥 스트릭 히트맵 사용법

이 잔디는 **실제 문제 풀이 커밋만** 집계합니다. 정직한 거울이에요 — 풀면 차고, 안 풀면 빈칸.

### 스트릭이 차는 조건
- **하루에 문제를 1개 이상 풀어 커밋**하면 그날 칸이 채워집니다.
- BaekjoonHub가 자동으로 올려주는 풀이 커밋(제목이 `[level 2] Title: ...`, `[Gold I] Title: ...` 등)이 대상입니다.
- 연속으로 매일 풀면 **현재 스트릭**이 올라가고, 하루라도 건너뛰면 다음 날 0으로 떨어집니다.
- 날짜 기준은 **KST(한국 시간)** 입니다. 자정 전에만 커밋되면 그날로 인정됩니다.

### 집계에서 제외되는 것
- 봇(`github-actions[bot]`)이 히트맵을 갱신하는 커밋
- `chore:` / `fix:` / `ci:` 등 유지보수용 커밋 (문제 풀이가 아니므로)

### 동작 방식
1. main에 push가 생기거나 매일 자정(KST)에 GitHub Action이 실행됩니다.
2. `git log`를 KST 기준으로 집계해 히트맵 SVG를 생성합니다. → [`.github/scripts/streak_heatmap.py`](.github/scripts/streak_heatmap.py)
3. SVG는 `main`이 아니라 별도 **`streak` 브랜치**에 저장됩니다. (기여 잔디 오염 방지)
4. 위 이미지는 그 `streak` 브랜치의 SVG를 참조합니다.

> 💡 BaekjoonHub가 재제출/오답 후 정답 등으로 커밋을 놓치는 경우가 있습니다. 그럴 땐 하루가 끝나기 전에 수동으로 커밋하면 그날 잔디가 인정됩니다.
