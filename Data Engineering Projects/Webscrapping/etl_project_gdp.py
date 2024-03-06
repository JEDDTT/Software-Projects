import pandas as pd
import sqlite3 as sql
import requests
from bs4 import BeautifulSoup
import numpy as np
from datetime import datetime

# Code for ETL operations on Country-GDP data
csv_path = "Countries_by_GDP.csv"
db_table = "Countries_by_GDP"
db = "World_Economies.db"
table_attribs = ["Country", "GDP_USD_billion"]
url = "https://web.archive.org/web/20230902185326/https://en.wikipedia.org/wiki/List_of_countries_by_GDP_%28nominal%29"
# Importing the required libraries


def extract(url, table_attribs):
    """This function extracts the required
    information from the website and saves it to a dataframe. The
    function returns the dataframe for further processing."""
    df = pd.DataFrame(columns=table_attribs)
    html_page = requests.get(url).text
    data = BeautifulSoup(html_page, "html.parser")
    html_table = data.find_all("tbody")
    html_table_rows = html_table[2].find_all("tr")
    for row in html_table_rows:
        cell = row.find_all("td")
        if len(cell) != 0:
            if cell[0].find("a") is not None and "-" not in cell[2]:
                data_dict = {
                    "Country": cell[0].a.contents[0],
                    "GDP_USD_billion": cell[2].contents[0],
                }
                dict_df = pd.DataFrame(data_dict, index=[0])
                df = pd.concat([df, dict_df], ignore_index=True)

    return df


dataframe = extract(url, table_attribs)
print(dataframe)


def transform(df):
    """This function converts the GDP information from Currency
    format to float value, transforms the information of GDP from
    USD (Millions) to USD (Billions) rounding to 2 decimal places.
    The function returns the transformed dataframe."""

    return df
