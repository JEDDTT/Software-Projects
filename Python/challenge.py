import os
from os import path

# Skip over any directory and processes files
# listing the file in the directory
# Count the byte size of each file and display the total byte size
# Create a result folder and create a result.txt file within the same folder
# List each file name


# def files(path):
#     for file in os.listdir(path):
#         if os.path.isfile(os.path.join(path, file)):
#             yield file


def main():
    totalBytes = 0
    curDir = path.realpath("")
    # curPath = os.getcwd()
    print("The directory path is: " + str(curDir))
    fileList = os.listdir(str(curDir))
    print("The list of files and directory are :" + str(fileList))
    for entry in fileList:
        if os.path.isfile(entry):
            fileByte = os.path.getsize(entry)
            totalBytes += fileByte
    os.mkdir("results")
    resultsfile = open("results/results.txt", "w+")
    if resultsfile.mode == "w+":
        resultsfile.write("Total bytecount:" + str(totalBytes) + "\n")
        resultsfile.write("Files list:\n")
        resultsfile.write("-------------\n")

        for entry in fileList:
            if os.path.isfile(entry):
                resultsfile.write(entry + "\n")
    resultsfile.close()


if __name__ == "__main__":
    main()
