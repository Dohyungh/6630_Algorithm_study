import os
from datetime import date as dateC
import re

year = 2024
days = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun']
month = int(input("달을 숫자로 입력하세요. : ")) # 월
date = int(input("날짜를 숫자로 입력하세요. : ")) # 날짜
yoil = days[dateC(year,month,date).weekday()] # 요일

# single
if month <=9:
    month = '0' + str(month)
if date <=9:
    date = '0' + str(date)


directory = "C:/Users/Dohyung/Desktop/ssafy/Github/6630_Algorithm_study/"
# directory = "C:/Users/SSAFY/Desktop/ssafy/github/6630_Algorithm_study/"
# directory = "/Users/handohyung/SSAFY/github/6630_Algorithm_study/"
dir_archive = directory+"archive/"
dir_Calender = directory+"Calender/"

filepath = dir_archive+"{}_{}_README.md".format(month,date)

sites = []
with open(filepath, "r",encoding="UTF8") as f:
    urls = f.read()
    folder_names = re.findall('\[([^]]+)\]', urls)
    links = re.findall("((http)s?://.*)", urls)
for url in links:
    if "acmicpc" in url[0] :
        sites.append("BOJ")
    elif "swexpertacademy" in url[0] :
        sites.append("SWEA")
    elif "jungol" in url[0] :
        sites.append("JOL")
    elif "school.programmers" in url[0] :
        sites.append("PRGS")
    elif "softeer" in url[0] :
        sites.append("SFTR")
f.close()

remove_set = {'IM', 'IM-','IM+','A','A-','A+','B','B+','B-','Kakao','Hyundai', 'Pccp', 'G1','G2','G3','G4','G5','S1','S2','S3','S4','S5','D1','D2','D3','D4','D5','D6','D7','D8','L1','L2','L3','L4','L5','P1','P2','P3','P4','P5'}

folder_names = [i for i in folder_names if i not in remove_set]

temps = []
for folder_name in folder_names:
    temps.append(folder_name.split())

categories= [temps.pop(0)[0],temps.pop(0)[0]] #SW_A, BFS,DFS


os.chdir(dir_Calender)
os.mkdir('{}_{}_{}'.format(month,date,yoil))
os.chdir('{}_{}_{}'.format(month,date,yoil))
f = open('.gitkeep','w')
f.close()

# team = ['민우','예진','성진','세하','도형']
team = ['민우','예진','성진','도형']



print(temps)
print(categories)
print(categories[-1])



flag = True
for category in categories:
    os.mkdir(category)
    os.chdir(category)
    if flag :
        num_for_each = 2 # 공통 총 개수
    else :
        num_for_each = 4 # 개인 총 개수
        
    for idx in range(1,num_for_each+1):

        temp = temps.pop(0)
        site = sites.pop(0)

        num = temp[0]
        name = temp[1]
        team_member = "_" + team[idx-1]
        if flag :
            team_member = ""

        os.mkdir("{}_{}_{}_{}{}".format(str(idx),site,num,name,team_member))
        os.chdir("{}_{}_{}_{}{}".format(str(idx),site,num,name,team_member))
        os.mkdir('assets')
        os.chdir('assets')
        f = open('.gitkeep','w')
        f.close()
        os.chdir('..')
        f = open('README.md','w')
        f.close()
        os.chdir('..')

        
    os.chdir('..')
    flag = False



    # for idx,idx in team:
    #     f = open('.gitkeep','w')
    #     f.close()
        
    #     for idx in range(1,num_for_each+1):
    #         temp = temps.pop(0)
    #         site = sites.pop(0)
    #         num = temp[0]
    #         name = temp[1]
    #         print(temp)
    #         os.mkdir("{}_{}_{}_{}_{}".format(str(idx),site,num,name,member))
    #         os.chdir("{}_{}_{}_{}_{}".format(str(idx),site,num,name,member))
    #         f = open('.gitkeep','w')
    #         f.close()
    #         os.chdir('..')
    #     os.chdir('..')



    

    


