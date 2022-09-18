# #프로그래머스 프린터 스택/큐

# def solution(priorities, location):
#     if len(priorities) == 1:
#         return 1
#     length = len(priorities)
#     cnt = 1
#     candidate = priorities.pop(0) # 0번째 숫자를 deque
#     #큐에서 숫자를 하나씩 꺼낼 때 마다 location -= 1
#     while priorities:
#         if location > 0 : # location이 0보다 크다(아직 내 문서가 나올 차례가 아님)
#             if candidate < max(priorities): # 현재 문서가 나올때가 아니면
#                 priorities.append(candidate)
#             else:# 맥스값과 같다면, print 하므로 cnt늘리고, location -=1
#                 cnt += 1
#             location -= 1
#         else:
#             if candidate < max(priorities): # 현재 문서가 나올때가 아니면
#                 priorities.append(candidate)
#                 location = len(priorities) - 1
#             else :
#                 break
#         candidate = priorities.pop(0)
        
#     if not priorities:
#         answer = length
        
#     else:
#         answer = cnt
        
#     return answer

# print(solution([1, 1, 9, 1, 1, 1], 0))
# print(solution([2, 1, 3, 2], 2))

# 예솔님 코드 고치기

def solution(priorities, location):
    
    answer = []
    
    #중요도에 따라서 리스트로 묶고
    file_list  = [[]]*10
    prior_list = [ (i,v) for i, v in enumerate(priorities)]
    
    for i in range(1, 10):
        if i in priorities:
            file_list[i] = list(filter(lambda x : x[1]==i, prior_list))
            
    # 여기까지 넣으면 file_list 완성
    
    # 최신위치
    standard_idx = -1
    # index가 더 작은 것
    tmp = []
    print(file_list)
    for i in range(9, 0, -1): 
        
        if file_list[i]:
            # 해당 숫자에 값이 있을 때에 index, value를 꺼내 for문을 돌린다.
            # index가 기준 index보다 크면 뒤에 위치하므로 answer에 append()
            # index가 기준 index보다 작으면 앞에 위치하므로 tmp에 넣은 후 나중에 tmp를 answer에 더해줌
            for index, value in file_list[i]:
                if index > standard_idx: # 같은 값인데 인덱스가 작을 경우, 
                    answer.append(index)
                    standard_idx = index
                    print(answer, i, standard_idx, index, "answer")
                else:
                    tmp.append(index)
                    print(answer, i, standard_idx, index, "tmp", tmp)
                    continue
            if tmp != []:
                for m in tmp:
                    answer.append(m)
                    standard_idx = m


    print(answer, "RESULT")
    return answer.index(location)+1

# print(solution([1, 1, 9, 1, 1, 1], 0))
print(solution([2, 3, 3, 2, 9, 3, 3], 3)) # return=6

#현재문제 : tmp를 집어 넣은 후 