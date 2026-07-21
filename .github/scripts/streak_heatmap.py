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


def commit_days() -> Counter:
    # 봇이 찍은 히트맵 갱신 커밋은 제외 — 실제 풀이 커밋만 스트릭에 반영
    out = subprocess.check_output(
        ["git", "log", "--format=%at%x09%an",
         "--invert-grep", "--grep=streak heatmap"], text=True)
    days = Counter()
    for line in out.splitlines():
        if not line.strip():
            continue
        ts, _, author = line.partition("\t")
        if author.strip() == "github-actions[bot]":
            continue
        d = datetime.fromtimestamp(int(ts), tz=KST).date()
        days[d] += 1
    return days


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
                f'height="{CELL}" rx="2"><title>{d} : {days.get(d, 0)}커밋'
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
<text x="{left}" y="16" class="h">🔥 현재 스트릭 {cur}일 · 최장 {longest}일 · 총 {total}커밋</text>
{"".join(month_labels)}
{weekday_labels}
{"".join(cells)}
</svg>'''


def main():
    days = commit_days()
    today = datetime.now(tz=KST).date()
    import os
    os.makedirs("assets", exist_ok=True)
    with open(OUT, "w") as f:
        f.write(build_svg(days, today))
    cur, longest = streaks(days, today)
    print(f"current={cur} longest={longest} total={sum(days.values())}")


if __name__ == "__main__":
    main()
