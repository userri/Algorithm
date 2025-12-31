from collections import deque
import sys

input = sys.stdin.readline

t = int(input())

dx = [1, 2, 2, 1, -1, -2, -2, -1]
dy = [2, 1, -1, -2, -2, -1, 1, 2]


def bfs(l, startX, startY, endX, endY):

    # -1은 방문 전, 그 이후는 방문깊이 카운트
    dist = [[-1] * l for _ in range(l)]
    # 시작점은 방문 깊이 0
    dist[startX][startY] = 0

    q = deque()
    q.append((startX, startY))

    while q:
        x, y = q.popleft()
        if x == endX and y == endY:
            return dist[x][y]
        for i in range(8):
            # 정해진 간격만큼 이동한 다음 노드 구하기
            nx = x + dx[i]
            ny = y + dy[i]
            # 범위 안에 있고 방문한 적이 없다면
            if 0 <= nx < l and 0 <= ny < l and dist[nx][ny] == -1:
                # 방문 횟수 업데이트 하고
                dist[nx][ny] = dist[x][y] + 1
                # 큐에 넣기
                q.append((nx, ny))


for i in range(t):
    l = int(input())
    startX, startY = map(int, input().split())
    endX, endY = map(int, input().split())
    print(bfs(l, startX, startY, endX, endY))
