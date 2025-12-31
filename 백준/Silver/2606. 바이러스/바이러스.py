# 바이러스
# 그래프
# 실버 3

# 사고과정 반드시 기록
# dps랑 bfs 둘다 상관없을 것 같다
# visited 수만 세면 될 것 같다

from collections import deque
import sys

input = sys.stdin.readline
c = int(input())
pairs = int(input())

# 컴퓨터 쌍 수만큼 n*n 배열 만들기(연결안되면 0, 연결됐으면 1)
linked = [[0] * (c + 1) for _ in range(c + 1)]
# 감염 여부 배열 저장
infected = [False] * (c + 1)

for i in range(pairs):
    x, y = map(int, input().split())
    linked[x][y] = 1
    linked[y][x] = 1


def dfs(v, count):
    infected[v] = True

    # 컴퓨터 수만큼 순회
    for i in range(1, c + 1):
        # 만약 방문한적 없는데 for문 안에서 i가 연결돼있다면
        # print("---", end="")
        # print(v, i, end=" : ")
        if infected[i] == False and linked[v][i] == 1:
            count = count + 1
            # print("현재 감염 수 : ", count)
            infected[i] = True

            count = dfs(i, count)
    return count


virusCount = 0
print(dfs(1, virusCount))
