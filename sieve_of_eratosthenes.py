def sieve_of_eratosthenes(n):
    prime_n = [2, 3, 5, 7]
    count = 0
    for i in range(1, n + 1):
        for j in prime_n:
            if i != j and i % j == 0:
                count += 1
                break
              
    return n-count-1

print(sieve_of_eratosthenes(20))