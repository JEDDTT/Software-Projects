import pandas as pd
from bs4 import BeautifulSoup
import sqlite3 as sql
import requests

url = "https://web.archive.org/web/20230902185655/https://en.everybodywiki.com/100_Most_Highly-Ranked_Films"
table_name = "Top_50"
db_name = "Movies.db"
csvfile = "top_50_films.csv"
count = 0

df = pd.DataFrame(columns=["Film", "Year"])

html_page = requests.get(url).text
data = BeautifulSoup(html_page, "html.parser")
tables = data.find_all("tbody")
rows = tables[0].find_all("tr")

for row in rows:
    if count < 10:
        col = row.find_all("td")
        if len(col) != 0:
            data_dict = {"Film": col[1].contents[0], "Year": col[2].contents[0]}
            df1 = pd.DataFrame(data_dict, index=[0])
            df = pd.concat([df, df1], ignore_index=True)
            count += 1
    else:
        break
print(df)
