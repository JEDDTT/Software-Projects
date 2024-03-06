import pandas as pd
from bs4 import BeautifulSoup
import sqlite3 as sql
from datetime import datetime
import requests

csvpath = "Countries_by_GDP.csv"
db_table = "Countires_by_GDP"
db = "World_Economies.db"
attributelist = ["Country", "GDP_USD_billion"]
# df = pd.DataFrame()


class logEvent:
    """Class to log event in the log file"""

    from datetime import datetime

    logfile = "etl_project_log.txt"
    format = "%Y-%h-%d-%H:%M:%S"  # Time format
    event_time = datetime.now().strftime(format)

    def __init__(self) -> None:
        pass

    def extraction_event(self):
        """Extraction event method to log extraction event in the file"""
        with open(self.logfile, "a") as f:
            f.write(
                self.event_time + " , " + "The extraction of data is completed" + "\n"
            )

    def transformation_event(self):
        """Transformation event method to log extraction event in the file"""
        with open(self.logfile, "a") as f:
            f.write(
                self.event_time
                + " , "
                + "The transformation of data is completed"
                + "\n"
            )

    def loading_event(self):
        """Loading event method to log extraction event in the file"""
        with open(self.logfile, "a") as f:
            f.write(self.event_time + " , " + "The loading of data is completed" + "\n")


def data_extraction():
    """Data extraction function to extract data from wikipedia"""
    url = "https://web.archive.org/web/20230902185326/https://en.wikipedia.org/wiki/List_of_countries_by_GDP_%28nominal%29"
    html_page = requests.get(url).text
    data = BeautifulSoup(html_page, "html_parser")
    tables = data.find_all("tbody")
