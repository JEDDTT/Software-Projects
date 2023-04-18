#Changing the  string to a date  in a list
import datetime

input = ['2021/05/25' , '2020/05/25' , '1999/02/04']
format = '%Y/%m/%d'
print(input)
print(type(input))
for i in input:
  print(datetime.datetime.strptime(i, format).date())
 
print =(input)
#Changing date format
