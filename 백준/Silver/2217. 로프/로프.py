n=int(input())

arr=[0]*(n)

for i in range(n):

    arr[i]=int(input())
arr.sort(reverse = True)
maxweight = arr[0]
currmin = arr[0]
# print(arr)
for i in range(1,n):
    currmin = arr[i] if arr[i]<currmin else currmin
    currmax = currmin*(i+1)
    if currmax>maxweight:
        maxweight = currmax

print(maxweight)