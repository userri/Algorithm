import sys

input = sys.stdin.readline
n = int(input())
arr = [0] * 10001
for i in range(n):
    k = int(input())
    arr[k] = arr[k] + 1

for i in range(10001):
    if arr[i] != 0:
        for k in range(arr[i]):
            print(i)