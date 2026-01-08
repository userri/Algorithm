t = int(input())
for _ in range(t):
    s = input()
    curr = 0
    score = []
    for i in range(len(s)):
        if s[i] == 'O':
            curr += 1
            if i == len(s)-1:
                score.append(curr)
        else:
            score.append(curr)
            curr = 0
    result = 0
    for num in score:
        result += num*(num+1)/2
    print(int(result))