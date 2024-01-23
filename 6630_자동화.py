import os
import shutil
import inspect

dir_archive = "C:/Users/Dohyung/Desktop/ssafy/한도형/6630_Algorithm_study/archive" 
file_list = os.listdir(dir_archive)
for file in file_list:
    filepath = dir_archive+'/'+file
    readme = open(filepath, "r",encoding="UTF8")
idx = readme.read().find("https")

# print(idx)

readme.close()

import re
with open(filepath, "r",encoding="UTF8") as f:
    urls = f.read()
    links = re.findall("((http)s?://.*)", urls)
for url in links:
    print(url)
# print(urls)