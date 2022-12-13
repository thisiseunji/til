# def sieve_of_eratosthenes(n):
#     prime_n = [2, 3, 5, 7]
#     count = 0
#     for i in range(1, n + 1):
#         for j in prime_n:
#             if i != j and i % j == 0:
#                 count += 1
#                 break
              
#     return n-count-1

# print(sieve_of_eratosthenes(20))

n=1000

a = [False,False] + [True]*(n-1)
primes=[]

for i in range(2,n+1):
  if a[i]:
    primes.append(i)
    for j in range(2*i, n+1, i):
        a[j] = False
        
print(primes)