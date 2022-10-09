def solution(n, lost, reserve):
    total = []
    reserve.sort()
    
    for i in range(1, n+1):
        if i not in lost:
            total.append(i)
            continue
        if i in reserve:
            total.append(i)
            reserve.remove(i)

    for i in reserve:
        if i-1 > 0 and (i-1 not in total):
            total.append(i-1)
            continue
        if i<n and (i+1 not in total):
            total.append(i+1)
            continue

    return len(total)

print(solution(3,[3],[1]))