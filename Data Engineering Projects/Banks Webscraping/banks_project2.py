import requests
import pandas as pd
from bs4 import BeautifulSoup
from datetime import datetime
import numpy as np
import sqlite3


url = "https://en.wikipedia.org/wiki/List_of_largest_banks"
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
exchange_rate_path = "exchange_rate.csv"
sql_connection = sqlite3.connect(db)


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
    tables = data.find("tbody")
    rows = tables.find_all("tr")

    for row in rows:
        cells = row.find_all("td")
        if len(cells) > 1:  # Ensuring the row has the required number of cells
            links = cells[1].find_all("a")  # Find all <a> tags in the second cell
            if links:
                bank_name_link = links[-1]  # Selecting the last <a> tag
                bank_name = bank_name_link.text.strip()  # Getting the text content
                market_cap = cells[2].text.strip()
                data_dict = {"Name": bank_name, "MC_USD_Billion": market_cap}
                df = pd.concat([df, pd.DataFrame([data_dict])], ignore_index=True)
    return df


def transform(df, csv_path):
    """This function accesses the CSV file for exchange rate
    information, and adds three columns to the data frame, each
    containing the transformed version of Market Cap column to
    respective currencies"""
    df1 = pd.read_csv(csv_path)
    dict = df1.set_index("Currency").to_dict()["Rate"]
    df["MC_GBP_Billion"] = [
        np.round(float(x) * dict["GBP"], 2) for x in df["MC_USD_Billion"]
    ]
    df["MC_EUR_Billion"] = [
        np.round(float(x) * dict["EUR"], 2) for x in df["MC_USD_Billion"]
    ]
    df["MC_INR_Billion"] = [
        np.round(float(x) * dict["INR"], 2) for x in df["MC_USD_Billion"]
    ]
    return df


def load_to_csv(df, output_path):
    """This function saves the final data frame as a CSV file in
    the provided path. Function returns nothing."""
    df.to_csv(output_path)


def load_to_db(df, sql_connection, table_name):
    """This function saves the final data frame to a database
    table with the provided name. Function returns nothing."""
    df.to_sql(table_name, sql_connection, if_exists="replace", index=False)


def run_query(query_statement, sql_connection):
    """This function runs the query on the database table and
    prints the output on the terminal. Function returns nothing."""

    print(query_statement)
    query_output = pd.read_sql(query_statement, sql_connection)
    print(query_output)


""" Here, you define the required entities and call the relevant
        functions in the correct order to complete the project. Note that this
        portion is not inside any function."""

log_progress("Preliminaries complete. Initiating ETL process")

dataframe = extraction(url, table_attribs_extract)

log_progress("Data extraction complete. Initiating Transformation process")

dataframe = transform(dataframe, exchange_rate_path)
print(dataframe)
print(dataframe["MC_EUR_Billion"][4])

log_progress("Data transformation complete. Initiating loading process")
load_to_csv(dataframe, csvpath)
log_progress("Data CSV loading complete. Initiating SQL loading process")
load_to_db(dataframe, sql_connection, table_name)
log_progress("SQL Data loading complete. Initiating Query process")
query_statement_1 = "SELECT * FROM largest_banks"
run_query(query_statement_1, sql_connection)
query_statement_2 = "SELECT AVG(MC_GBP_Billion) FROM largest_banks"
run_query(query_statement_2, sql_connection)
query_statement_3 = "SELECT Name FROM largest_banks LIMIT 5"
run_query(query_statement_3, sql_connection)
log_progress("Run Query complete")
sql_connection.close()
