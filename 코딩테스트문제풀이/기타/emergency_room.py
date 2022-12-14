#환자가 온 순서에 따라 진료하되 지금 순서의 환자보다 위급한 환자가 있는 경우 맨 뒤로 보낸다.
#주어진 리스트에서 M번째 있는 환자가 몇 번째로 진료받는지를 구하라
#같은 값의 위험도도 있고, 값이 높을수록 더 위험하다.
from collections import deque

def emergency_room(patients, M):
    Q = deque([(idx, val) for idx, val in enumerate(patients)])
    cnt = 0
    
    while True:
        cur = Q.popleft()
        #cur 보다 더 위급한 환자가 있을 때, 
        if any( cur[1] < patient[1] for patient in Q):
            Q.append(cur)
        #cur보다 위급한 환자가 없을 때, 
        else :
            #치료하고
            cnt+=1
            #만약 M번째 환자면 break
            if cur[0] == M:
                break
    return cnt

patients = [60, 50, 70, 80, 90]

print(emergency_room(patients, 2))
