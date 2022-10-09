def reverse(x):
    str_x = str(x)
    tmp = ''
    for i in range(len(str_x)-1, -1, -1):
        tmp += str_x[i]
    return int(tmp)

def isPrime(x):
    prime_n = [2, 3, 5, 7]
    for i in prime_n:
        if x % i == 0:
            return False
    return True
    
def solution(n, numbers):
    answer = []
    for i in numbers:
        reversed_i = reverse(i)
        if isPrime(reversed_i):
            answer.append(reversed_i)
        else:
            continue
    
    return answer

print(solution(5, [32, 55, 62, 3700, 250]))