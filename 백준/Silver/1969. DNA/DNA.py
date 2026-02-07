# DNA
# 브루트포스
# 실버 4

a, b = map(int, input().split())

dnas = []
for i in range(a) :
    dnas.append(input())

li = []
# 배열에 자릿수별로 dict 저장
for i in range(b) :
    di = {}
    di['A'] = 0
    di['C'] = 0
    di['G'] = 0
    di['T'] = 0
    li.append(di)

# dna 개수
# 열별로 알파벳 수 저장해야함
for i in range(b) :
    for j in range(a) :
        li[i][dnas[j][i]] = li[i][dnas[j][i]] + 1
    # print(li[i])
    # print("------------")


result = []
for i in li :
    result.append(max(i, key = i.get))
    

resultHd = 0
# 행별로 다른 횟수 카운트
for i in dnas :
    for j in range(b) :
        if result[j] != i[j] :
            resultHd = resultHd + 1
print(''.join(result))
print(resultHd)

