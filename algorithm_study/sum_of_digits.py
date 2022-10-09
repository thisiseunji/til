def digit_sum(x):
    answer = 0
    while x > 0 :
        answer += x % 10 
        x = x // 10
        
    return answer # sum(map(int, str(n))) - 속도가 느리다.

#자릿수의 합이 최대인 자연수
def sum_of_degits(n, x):
    num_list = list(map(int, x.split()))
    
    max_num  = 0
    idx      = 0
    for i in range(n):
        tmp = digit_sum(num_list[i])
        if max_num < tmp:
           max_num = tmp
           idx     = i
        else :
            continue
        
    return num_list[idx]

print(sum_of_degits(3, "125 15232 97"))