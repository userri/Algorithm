import sys
input = sys.stdin.readline
n = int(input())
loss = list(map(int, input().split()))

loss.sort()
last = 0
odd = 0
 # 만약 홀수면 제일 근손실 큰 애를 혼자 하게 하기
if n%2 == 1:
    last = loss[len(loss)-1]
    odd = True

# 일단 절반은 큰 순서대로 채우고 나머지 절반은 역순으로 채워서 
weightLoss = loss[len(loss)-1]
for i in range(n//2):
    sum = 0
    if odd:
        # 홀수는 아까 맨뒤에꺼 뺐으니까 뒤 절반은 한칸씩 앞으로 당기기
        sum = loss[i] + loss[n-1-i-1]
    else: 
        sum = loss[i] + loss[n-1-i]
    if sum > weightLoss :
        weightLoss = sum

weightLoss = max(weightLoss,last)
print(weightLoss)

