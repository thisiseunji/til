def most_probable_value(rp1, rp2):
    sum_values = {} # key : 더한 값, value : 빈도
    for i in range(1, rp1+1):
        for j in range(1, rp2+1):
            if i+j in sum_values:
                sum_values[i+j] += 1
            else :
                sum_values[i+j] = 1

    max_value = max(sum_values.values())
    
    answer = []
    
    for i, j in sum_values.items():
        if j == max_value:
            answer.append(i)
        else :
            continue
    
    return answer  
        
print(most_probable_value(4,6))   

