#!/usr/bin/env python3
"""이 레포의 커밋 이력으로 잔디 히트맵 SVG + 스트릭을 생성한다.

git log 날짜를 KST 기준으로 집계해 최근 52주 히트맵과
현재/최장 스트릭을 assets/streak.svg 로 저장한다.
"""
import subprocess
from collections import Counter
from datetime import date, datetime, timedelta, timezone

KST = timezone(timedelta(hours=9))
OUT = "assets/streak.svg"

CELL = 12          # 셀 크기(px)
GAP = 3            # 셀 간격
WEEKS = 52
LEVELS = ["#ebedf0", "#9be9a8", "#40c463", "#30a14e", "#216e39"]
LEVELS_DARK = ["#161b22", "#0e4429", "#006d32", "#26a641", "#39d353"]
MONTHS = ["1월", "2월", "3월", "4월", "5월", "6월",
          "7월", "8월", "9월", "10월", "11월", "12월"]


SKIP_DIRS = (".github/", "assets/")
# 문제마다 폴더를 만들지 않고 파일이 바로 놓이는 곳
LOOSE_ROOTS = ("코테",)
# 유지보수 커밋 접두어 — BaekjoonHub/LeetHub 풀이 커밋은 '[' 나 문제 제목,
# 'Time:' 으로 시작하며 이런 접두어를 쓰지 않으므로 안전하게 거른다.
SKIP_PREFIX = ("chore:", "fix:", "ci:", "docs:", "refactor:",
               "test:", "build:", "style:")


def problem_key(path: str):
    """풀이 파일 경로 -> 문제 식별자. 문제 파일이 아니면 None."""
    path = path.strip().strip('"')
    if not path or "/" not in path or path.startswith(SKIP_DIRS):
        return None                       # 루트 README.md, STUDY.md, 워크플로 등
    parts = path.split("/")
    # 코테/260726_2.java 처럼 폴더 없이 놓인 파일은 파일 자체가 곧 문제다.
    if parts[0] in LOOSE_ROOTS and len(parts) == 2:
        return path
    key = "/".join(parts[:-1])            # 대개 문제마다 폴더가 하나
    # LeetHub는 폴더를 레포 루트에 만들고 봇이 LeetCode/ 아래로 옮긴다.
    # 이동 전후가 같은 문제로 잡히도록 접두어를 떼고 비교한다.
    return key[len("LeetCode/"):] if key.startswith("LeetCode/") else key


def commit_days() -> Counter:
    """하루에 '서로 다른 문제를 몇 개 건드렸나'를 센다.

    커밋 수를 세면 안 된다 — LeetHub/백준허브는 제출 한 번에 커밋을 여러 개
    만들고(README 생성 / 코드 / Topic Tags / stats), 재제출하면 또 늘어난다.
    그러면 한 문제가 3~4로 잡혀 색 농도가 부풀려진다. 커밋이 실제로 건드린
    풀이 폴더로 세면 중복 커밋·재제출·문서 커밋이 한꺼번에 정리된다.
    """
    out = subprocess.check_output(
        ["git", "-c", "core.quotepath=false", "log",
         "--format=%x00%at%x09%an%x09%s", "--name-only"],
        text=True, encoding="utf-8")

    per_day: dict = {}
    for chunk in out.split(chr(0)):
        if not chunk.strip():
            continue
        head, *paths = chunk.splitlines()
        parts = head.split("	")
        ts, author = parts[0], parts[1]
        subject = parts[2].strip() if len(parts) > 2 else ""
        # 봇이 찍은 정리 커밋은 제외 — 실제 풀이만 반영
        if author.strip() == "github-actions[bot]":
            continue
        if subject.startswith(SKIP_PREFIX):
            continue

        problems = {k for k in map(problem_key, paths) if k}
        if not problems:
            continue                      # 루트 README·문서만 건드린 커밋
        d = datetime.fromtimestamp(int(ts), tz=KST).date()
        per_day.setdefault(d, set()).update(problems)

    return Counter({d: len(probs) for d, probs in per_day.items()})


def streaks(days: Counter, today: date) -> tuple[int, int]:
    # 현재 스트릭: 오늘 또는 어제부터 거꾸로 연속된 날 수
    cur = 0
    d = today if today in days else today - timedelta(days=1)
    while d in days:
        cur += 1
        d -= timedelta(days=1)
    # 최장 스트릭
    longest = run = 0
    prev = None
    for d in sorted(days):
        run = run + 1 if prev == d - timedelta(days=1) else 1
        longest = max(longest, run)
        prev = d
    return cur, longest


def level(count: int) -> int:
    if count == 0:
        return 0
    if count <= 2:
        return 1
    if count <= 4:
        return 2
    if count <= 7:
        return 3
    return 4


def build_svg(days: Counter, today: date) -> str:
    cur, longest = streaks(days, today)
    total = sum(days.values())

    # 히트맵은 오늘이 포함된 주의 토요일까지, 거기서 52주 전 일요일부터
    end = today + timedelta(days=(6 - (today.weekday() + 1) % 7))
    start = end - timedelta(weeks=WEEKS) + timedelta(days=1)

    left, top = 34, 40
    width = left + WEEKS * (CELL + GAP) + 10
    height = top + 7 * (CELL + GAP) + 34

    cells, month_labels = [], []
    seen_month = None
    for w in range(WEEKS):
        for r in range(7):
            d = start + timedelta(weeks=w, days=r)
            if d > today:
                continue
            x = left + w * (CELL + GAP)
            y = top + r * (CELL + GAP)
            lv = level(days.get(d, 0))
            cells.append(
                f'<rect class="c{lv}" x="{x}" y="{y}" width="{CELL}" '
                f'height="{CELL}" rx="2"><title>{d} : {days.get(d, 0)}문제'
                f'</title></rect>')
        first = start + timedelta(weeks=w)
        if first.month != seen_month:
            seen_month = first.month
            month_labels.append(
                f'<text x="{left + w * (CELL + GAP)}" y="{top - 8}" '
                f'class="t">{MONTHS[first.month - 1]}</text>')

    weekday_labels = "".join(
        f'<text x="{left - 8}" y="{top + r * (CELL + GAP) + CELL - 2}" '
        f'class="t" text-anchor="end">{lbl}</text>'
        for r, lbl in [(1, "월"), (3, "수"), (5, "금")])

    dark_css = "".join(
        f".c{i}{{fill:{c}}}" for i, c in enumerate(LEVELS_DARK))
    light_css = "".join(
        f".c{i}{{fill:{c}}}" for i, c in enumerate(LEVELS))

    return f'''<svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}" viewBox="0 0 {width} {height}">
<style>
.t{{font:10px sans-serif;fill:#57606a}}
.h{{font:bold 13px sans-serif;fill:#24292f}}
{light_css}
@media (prefers-color-scheme: dark){{
.t{{fill:#8b949e}}.h{{fill:#c9d1d9}}
{dark_css}
}}
</style>
<text x="{left}" y="16" class="h">🔥 현재 스트릭 {cur}일 · 최장 {longest}일 · 총 {total}문제</text>
{"".join(month_labels)}
{weekday_labels}
{"".join(cells)}
</svg>'''


def main():
    days = commit_days()
    today = datetime.now(tz=KST).date()
    import os
    os.makedirs("assets", exist_ok=True)
    with open(OUT, "w", encoding="utf-8") as f:
        f.write(build_svg(days, today))
    cur, longest = streaks(days, today)
    print(f"current={cur} longest={longest} total={sum(days.values())}")


if __name__ == "__main__":
    main()
