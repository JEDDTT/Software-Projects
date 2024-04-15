import requests
import pandas as pd
from bs4 import BeautifulSoup
from datetime import datetime
import numpy as np
import sqlite3
import lxml
import html5lib


# url = "https://en.wikipedia.org/wiki/List_of_largest_banks"
url = "https://web.archive.org/web/20230908091635/https://en.wikipedia.org/wiki/List_of_largest_banks"
table_attribs_extract = ["Name", "MC_USD_Billion"]
table_attribs_final = [
    "Name",
    "MC_USD_Billion",
    "MC_GBP_Billion",
    "MC_EUR_Billion",
    "MC_INR_Billion",
]
csvpath = "Largest_banks_data.csv"
db = "Banks.db"
table_name = "Largest_banks"
logfile = "code_log.txt"
exchange_rate = "exchange_rate.csv"


def log_progress(message):
    time_format = "%Y-%h-%d-%H:%M:%S"
    now = datetime.now()
    timestamp = now.strftime(time_format)
    with open(logfile, "a") as f:
        f.write(timestamp + " : " + message + "\n")


def extraction(url, table_attribs_extract):
    df = pd.DataFrame(columns=table_attribs_extract)
    html_page = requests.get(url).text
    data = BeautifulSoup(html_page, "html.parser")
    tables = data.find_all("tbody")
    rows = tables[0].find_all("tr")
    for row in rows:
        cell = row.find_all("td")
        if len(cell) != 0:
            data_dict = {
                "Name": cell[1].find_all("a")[1].contents[0],
                "MC_USD_Billion": float(cell[2].contents[0][:-1]),
            }
            dict_df = pd.DataFrame(data_dict, index=[0])
            df = pd.concat([df, dict_df], ignore_index=True)
    # By using pd.read_html
    # dfs = pd.read_html(url)
    # df = dfs[1]
    # df["Market cap (US$ billion)"] = (
    #     df["Market cap (US$ billion)"].replace("\n", "").astype(float)
    # )
    return df


def transform(df, csv_path):
    # rates_df = pd.read_csv(csv_path)
    # rates_dict = rates_df.set_index("Currency").to_dict()["Rate"]
    # df = df.rename(
    #     columns={"Market cap (US$ billion)": "MC_USD_Billion", "Bank Name": "Name"}
    # )

    # df["MC_GBP_Billion"] = round(df["MC_USD_Billion"] * rates_dict["GBP"], 2)
    # df["MC_EUR_Billion"] = round(df["MC_USD_Billion"] * rates_dict["EUR"], 2)
    # df["MC_INR_Billion"] = round(df["MC_USD_Billion"] * rates_dict["INR"], 2)
    # Second peer testing
    exchange_df = pd.read_csv(csv_path)
    USD_list = df["MC_USD_Billion"].to_list()
    EUR_list = [np.round(float(x) * exchange_df["Rate"].iloc[0], 2) for x in USD_list]
    GBP_list = [np.round(float(x) * exchange_df["Rate"].iloc[1], 2) for x in USD_list]
    INR_list = [np.round(float(x) * exchange_df["Rate"].iloc[2], 2) for x in USD_list]
    df["MC_GBP_Billion"] = GBP_list
    df["MC_EUR_Billion"] = EUR_list
    df["MC_INR_Billion"] = INR_list
    print(f"Value for MC_EUR_Billion: {df['MC_EUR_Billion'][4]}")
    return df


dataframe = extraction(url, table_attribs_extract)
# print(dataframe)
dataframe = transform(dataframe, exchange_rate)
print(dataframe)
