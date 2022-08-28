#1차
def solution(record):
    answer   = []
    saved_id = []
    pair     = {}
    for i in range(len(record)-1, 0, -1) :
        if record[i][0] != 'L': 
            v, uid, nickname = record[i].split()
            if uid not in saved_id:
                saved_id.append(uid)
                pair[uid] = nickname
        else :
            continue
    
    sentences = {'E' : '님이 들어왔습니다.', 'L':'님이 나갔습니다.'}
    
    for r in record:
        v, uid, nickname = r.split()
        if v[0] == 'E' or v[0] == 'L':
            answer.append(pair[uid] + sentences[v[0]])
        else:
            continue
    
    return answer