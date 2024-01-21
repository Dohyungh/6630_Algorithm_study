package SWEA._1974_스도쿠검증;

public class Solution_fin {
//	T = int(input())
//			# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
//			함수 tester 작성 (겹치는 원소를 제거한 후에, 길이를 재서 9가 아니면 0으로 만듦. answer에 0이 한번이라도 곱해지면 이후 0이 되는 것을 이용!)
//			def tester(li):
//			    if len(set(li)) != 9 :
//			        return 0
//			    else :
//			        return 1
//			     
//			for test_case in range(1, T + 1):
	
//	9 x 9 행렬 생성		     
//			    li = []
//			    for _ in range(0,9):
//			        row = list(map(int,input().split()))
//			        li.append(row)

//			    answer =1
	
//	행 검사
//			    for row in li:
//			        answer *=tester(row)
	
	
//	열 검사
//			    for i in range(9):
//			        temp = []
//			        for j in range(9):
//			            temp.append(li[j][i])
//			        answer*=tester(temp)
	
//	3*3 구간 탐색
//	좌상 중상 우상 좌중 중중 우중 좌하 중하 우하 순서로 탐색
//			    idx = [[0,1,2],[3,4,5],[6,7,8]]
//			    for i in idx:
//			        for j in idx:
//			            temp = []
//			            for I in i:
//			                for J in j:
//			                    temp.append(li[I][J])
//			            answer *=tester(temp)
//			    print('#{}'.format(test_case),answer)
}
