# 절단기에 높이 h ㅣ정
# 높이를 지정하면 톱날이 땅으로부터 h미터 위로 올라감
# 그 다음 한 줄에 연속해 있는 나무를 모두 절단
# 높이가 h 이상인 나무의 윗부분들만 가져가기

import sys
input = sys.stdin.readline

n,m = map(int, input().split())
trees = list(map(int, input().split()))

def cutTree():
    start = 0
    end = max(trees)
    mid = 0
    while start <= end:
        result = 0
        mid = (start + end)//2
        for tree in trees:
            # print(tree, mid)
            result += (tree-mid) if tree > mid else 0
        # print(result)
        if result < m:
            end = mid - 1
        elif result == m:
            return mid
        elif result > m:
            start = mid + 1
        # print(result, start, end)
    
    while result < m:
        result = 0
        mid -= 1
        for tree in trees:
            # print(tree, mid)
            result += (tree-mid) if tree > mid else 0

    return mid
print(cutTree())