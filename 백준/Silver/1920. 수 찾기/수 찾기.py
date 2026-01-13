import sys

input = sys.stdin.readline
n = int(input())
A = list(map(int, input().split()))
m = int(input())
M = list(map(int, input().split()))
A.sort()


def isInA(num):
    if num < A[0] or num > A[len(A) - 1]:
        return 0
    start = 0
    end = len(A) - 1
    while start <= end:
        mid = (start + end) // 2
        if num < A[mid]:
            end = mid - 1
        elif num > A[mid]:
            start = mid + 1
        elif num == A[mid]:
            return 1
    return 0


for num in M:
    print(isInA(num))
