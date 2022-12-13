#프로그래머스 가장 큰 수 : 내 코드
def solution(numbers):
    answer = ''
    numbers.sort(key=lambda x : str(x)*3, reverse=True)
    for i in numbers:
        answer += str(i)
    if int(answer) == 0:
        return '0'
    return answer

#인상깊은 코드
# def solution(n):
#     return str(int("".join(sorted(map(str,n),key= lambda x : (x*3),reverse = True))))


# # cmp_to_key가 뭘까
# import functools

#     t1 = a+b
#     t2 = b+a
#     return (int(t1) > int(t2)) - (int(t1) < int(t2)) #  t1이 크다면 1  // t2가 크다면 -1  //  같으면 0

# def solution(numbers):
#     n = [str(x) for x in numbers]
#     n = sorted(n, key=functools.cmp_to_key(comparator),reverse=True)
#     answer = str(int(''.join(n)))
#     return answer

# # cmp_to_key를 구현한 사람

# from io import StringIO

# def cmp_to_key(mycmp):
#     'Convert a cmp= function into a key= function'
#     class K:
#         def __init__(self, obj, *args):
#             self.obj = obj

#         def __lt__(self, other):
#             return mycmp(self.obj, other.obj) < 0

#         def __gt__(self, other):
#             return mycmp(self.obj, other.obj) > 0

#         def __eq__(self, other):
#             return mycmp(self.obj, other.obj) == 0

#         def __le__(self, other):
#             return mycmp(self.obj, other.obj) <= 0

#         def __ge__(self, other):
#             return mycmp(self.obj, other.obj) >= 0

#         def __ne__(self, other):
#             return mycmp(self.obj, other.obj) != 0
#     return K


# def comparator(x, y):
#     x = str(x)
#     y = str(y)
#     x_y = int(x + y)
#     y_x = int(y + x)

#     if x_y < y_x:
#         return -1
#     elif y_x < x_y:
#         return 1
#     else:
#         return 0


# def solution(numbers):

#     numbers = sorted(numbers, key=cmp_to_key(comparator), reverse=True)

#     string_buffer = StringIO()
#     for number in numbers:
#         string_buffer.write(str(number))

#     answer = int(string_buffer.getvalue())
#     return str(answer)