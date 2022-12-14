# 필수과목 이수 순서와, 다른 과목을 포함한 수업 이수 계획이 주어졌을 때,
# 수업 이수 계획이 필수과목 이수 순서에 맞춰 작성됐는지 여부를 리턴하라.

def mandatory_check(order, plans):
    answer = {}
    #plans는 다중 리스트
    for i in range(len(plans)):
        tmp = ''
        for j in plans[i]:
            if j in order:
                tmp += j
        if tmp == order:
            answer[i+1] = True
        else:
            answer[i+1] = False
    
    return answer

print(mandatory_check('CBA', ['CBDAGE', 'FGCDAB', 'CTSBDEA']))
