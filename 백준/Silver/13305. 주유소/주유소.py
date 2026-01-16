import sys
input = sys.stdin.readline
n = int(input())
distances = list(map(int, input().split()))
prices = list(map(int, input().split()))

# 제일 기름값 싼 곳에서 먼저 종착지까지 갈만큼 기름 채워(ex. 5~10)
# 아직 기름 안 채운 곳에서(ex. 1~5) 제일 싼 곳 찾아서 또 안채운 뒷부분 싹다채워(ex. 3~5)
minPrice = prices[0]
total = 0
# 도시 배열을 순회하면서 현재까지의 주유소 최소값을 업데이트하기
# 현재기준 최소값*현재 탐색중인 도시간격 만큼 결과에 더해
#마지막 주유소 가격은 의미없음
for i in range(n-1):
    minPrice = min(minPrice, prices[i])
    total += minPrice*distances[i]
    # print(minPrice, distances[i], total)
print(total)