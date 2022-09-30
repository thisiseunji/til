# 최대공약수 계산
# 뺄셈
def gcd_sub(a:int, b:int) -> int:
    while a*b != 0 :
        if a > b : a -= b
        else : b -= a
    return a+b

# 나머지
def gcd_mod(a:int, b:int) -> int:
    while a*b != 0 :
        if a > b : a %= b
        else : b %= a
    return a+b

# 재귀
def gcd_rec(a:int, b:int) -> int:
    if a*b != 0 :        
        if a > b : 
            a %= b 
        else : 
            b %= a
        return gcd_rec(a,b)
    else: return a+b

print(gcd_sub(24,50))
print(gcd_mod(24,50))
print(gcd_rec(24,50))