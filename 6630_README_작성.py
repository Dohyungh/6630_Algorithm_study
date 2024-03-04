import re

filepath = "./README.md"
sites = []

with open(filepath, "r",encoding="UTF8") as f:
    urls = f.read()
    # folder_names = re.findall('\[([^]]+)\]', urls)
    links = re.findall("((http)s?://.*)", urls)
    
f.close()

newLinks = []
for l in links:
    url = re.sub(pattern =r'\)', repl='',string=l[0])
    print(url)




