#후위표기식 만들기
#우선순위가 높은 연산이 먼저 수행된다. 연산 우선순위가 같을 경우, 먼저 입력된 연산이 먼저 실행된다. 
#해당 입력의 기대값은 '352+*9-'이다.

def postfix(s):
    answer = ''
    stack = []
    for i in s :
        if i.isdecimal():
            answer += i
        else:
            if i == '(':
                stack.append(i)
            elif i == '*' or i == '/':
                while stack and (stack[-1]=='*' or stack[-1]=='/'):
                    answer += stack.pop()
                stack.append(i)
            elif i =='+' or i =='-':
                while stack and stack[-1] != '(': #괄호가 아닌 모든 연산자를 다 꺼내는 것, 우선순위에서 밀리기 때문에 
                    answer += stack.pop()
                stack.append(i)
            else:
                while stack and stack[-1] != '(':
                    answer += stack.pop()
                stack.pop()
    
    while stack:
        answer += stack.pop()
        
    return answer

s = '3*(5+2)-9'

print(postfix(s))