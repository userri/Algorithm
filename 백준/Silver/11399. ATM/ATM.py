import sys
input = sys.stdin.readline
n = int(input())
times = list(map(int, input().split()))
times.sort()
total = 0
for i in range(n):
    # 자기 인출시간 + 자기인출시간*자기 뒷사람들이 기다릴 시간(전체 인덱스 - 내 인덱스)
    total += times[i]+times[i]*(len(times)-1-i)
    # print(times[i], total)
print(total)