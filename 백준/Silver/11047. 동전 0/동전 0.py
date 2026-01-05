import sys

input = sys.stdin.readline
n, k = map(int, input().split())
coins = []
for i in range(n):
    coins.append(int(input()))
coins.sort(reverse=True)

div = 0
count = 0
curr = k
for i in coins:
    div = int(curr / i)
    if div == 0:
        continue
    count += div
    curr = curr % i
print(count)