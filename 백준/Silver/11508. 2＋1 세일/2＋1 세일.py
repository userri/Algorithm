import sys
input = sys.stdin.readline

n = int(input())

arr = [0]*(n)
for i in range(n):
    arr[i] = int(input())
arr.sort(reverse=True)

total = 0

for i in range(n//3): 
    total += arr[3*i]+arr[3*i+1]
    
for i in range(3*(n//3),n):
    total += arr[i]

print(total)