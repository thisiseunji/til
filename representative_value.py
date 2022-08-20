def representative_value(students, test_scores):
    test_scores = list(map(int, test_scores.split()))
    average     = round(sum(test_scores) / students)
    tmp         = abs(test_scores[0]-average)
    idx         = 0
    
    for i in range(1, students):
        if tmp > abs(test_scores[i]-average):
            tmp = abs(test_scores[i]-average)
            idx = i
        
        elif tmp == abs(test_scores[i]-average):
            idx = i if test_scores[i] > test_scores[i-1] else idx
    
    return average, idx+1
        
        
print(representative_value(10, "45 73 66 87 92 67 75 79 75 80"))
            
    