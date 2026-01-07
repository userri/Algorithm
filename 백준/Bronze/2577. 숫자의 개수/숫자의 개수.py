mydict = {'0':0,'1':0,'2':0,'3':0,'4':0,'5':0,'6':0,'7':0,'8':0,'9':0}

result = 1
for i in range(3):
    result *= int(input())
mystr = str(result)
# print(mystr)
for i in range(len(mystr)):
    mydict[mystr[i]] += 1
# print(mydict)
for num in mydict.values():
    print(num)