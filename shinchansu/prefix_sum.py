# arr의 index a에서 b까지의 합을 리턴하는 함수
def prefix_sum(arr, a, b):
    return sum(arr[:b+1])-sum(arr[:a])

arr = [0,1,2,3,4,5,6,7,8,9,10]
a = 3
b = 5

print(prefix_sum(arr, a, b)) #12