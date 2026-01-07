t = int(input())
cases = []
for i in range(t):
    case = list(map(int, input().split()))
    cases.append(case)

for case in cases:
    if case[2]%case[0] == 0:
        dist = case[2]//case[0]
    else : dist = case[2]//case[0]+1
    floor = case[2]%case[0]
    if floor == 0: floor = case[0]
    print(floor,end='')
    if dist < 10:
        print('0',end='')
        print(dist)
    else: print(dist)