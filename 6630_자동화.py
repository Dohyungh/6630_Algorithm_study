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

directory = "C:/Users/SSAFY/Desktop/LUCAS/6630_Algorithm_study/"
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
f.close()

remove_set = {'IM', 'IM-','IM+','A','A-','A+','B','B+','B-','Kakao','G1','G2','G3','G4','G5','S1','S2','S3','S4','S5'}

folder_names = [i for i in folder_names if i not in remove_set]

temps = []
for folder_name in folder_names:
    temps.append(folder_name.split())
categories= [temps.pop(0)[0],temps.pop(0)[0]] #SW_A, BFS,DFS

num_for_each = int(len(temps)/2)
print(num_for_each)
os.chdir(dir_Calender)
os.mkdir('{}_{}_{}'.format(month,date,yoil))
os.chdir('{}_{}_{}'.format(month,date,yoil))
f = open('.gitkeep','w')
f.close()

team = ['민우','예진','성진','도형']



print(temps)
flag = True
for category in categories:
    os.mkdir(category)
    os.chdir(category)
    for idx in range(1,num_for_each+1):
        
        if flag :
            temp = temps.pop(idx)
            site = sites.pop(idx)
        else :
            temp = temps.pop(0)
            site = sites.pop(0)

        num = temp[0]
        name = temp[1]
        team_member = team[idx-1]

        os.mkdir("{}_{}_{}_{}_{}".format(str(idx),site,num,name,team_member))
        os.chdir("{}_{}_{}_{}_{}".format(str(idx),site,num,name,team_member))
        os.mkdir('assets')
        os.chdir('assets')
        f = open('.gitkeep','w')
        f.close()
        os.chdir('..')
        f = open('.gitkeep','w')
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



    

    


