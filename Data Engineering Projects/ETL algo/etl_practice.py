import pandas as pd
import xml.etree.ElementTree as ET
import glob
from datetime import datetime

class etl_event:
    """Class etl event created to log time"""
    logfile = "log.txt"
    timeFormat = "%Y-%h-%d-%H:%M:%S"  # Time format
    now = datetime.now()
    time = now.strftime(timeFormat)

    def __init__(self) -> None:
        pass

    def extraction_process(self):
        with open(self.logfile, "a") as f:
            f.write(self.time + " " + "Extraction completed" + "\n")

    def transformation_process(self):
        with open(self.logfile, "a") as f:
            f.write(self.time + " " + "Transformation completed" + "\n")

    def load_process(self):
        with open(self.logfile, "a") as f:
            f.write(self.time + " " + " Loading completed" + "\n")

transformedData = "etl.csv"
logEvent = etl_event()

def extract_from_csv(file):
    df = pd.read_csv(file)
    return df


def extract_from_json(file):
    df = pd.read_json(file, lines=True)
    return df


def extract_from_xml(file):
    dataframe = pd.DataFrame(
        columns=["car_model", "year_of_manufacture", "price", "fuel"]
    )
    xml = ET.parse(file)
    root = xml.getroot()
    for car in root:
        model = car.find("car_model").text
        year = int(car.find("year_of_manufacture").text)
        price = float(car.find("price").text)
        fuel = car.find("fuel").text
        dataframe = pd.concat(
            [
                dataframe,
                pd.DataFrame(
                    [
                        {
                            "car_model": model,
                            "year_of_manufacture": year,
                            "price": price,
                            "fuel": fuel,
                        }
                    ]
                ),
            ],
            ignore_index=True,
        )
    return dataframe


def extraction():
    """ This function serves to extract all file in the current directory"""
    extrated_dataframe = pd.DataFrame(
        columns=["car_model", "year_of_manufacture", "price", "fuel"]
    )
    # extraction of csv
    for csvfile in glob.glob("*.csv"):
        extrated_dataframe= pd.concat(
            [extrated_dataframe, pd.DataFrame(extract_from_csv(csvfile))], ignore_index=True
        )

    # Extraction of json file
    for jsonfile in glob.glob("*.json"):
        extrated_dataframe= pd.concat(
            [extrated_dataframe, pd.DataFrame(extract_from_json(jsonfile))], ignore_index=True
        )
    # Extraciton of xml file
    for xmlfile in glob.glob("*.xml"):
        extrated_dataframe= pd.concat(
            [extrated_dataframe, pd.DataFrame(extract_from_xml(xmlfile))], ignore_index=True
        )
    logEvent.extraction_process()
    return extrated_dataframe

def transform(data):
    ''' Transform extracted data'''
    data['price'] = round(data.price, 2)
    logEvent.transformation_process()
    return data

def load(data):
    data.to_csv(transformedData)
    logEvent.load_process()
    print("Loading completed")

# #Testing
data = extraction()
transform(data)
print(data)
load(data)


