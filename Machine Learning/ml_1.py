import matplotlib.pyplot as plt
import cv2
import easyocr
from pylab import rcParams
from IPython.display import Image

rcParams['figure.figsize'] = 8, 16

reader = easyocr.Reader(['en'])
Image("sample.jpg")


output = reader.readtext('sample.jpg')

for i in range(len(output)):
  print((output[i][1]))

from google.colab import files                                  #import dataset allergen
uploaded = files.upload()

import pandas as pd                                             #convert to dict
import math
data = pd.read_csv("FoodData.csv")
data_dict = {col: list(data[col]) for col in data.columns}

df = pd.DataFrame(data_dict, columns = ['Class', 'Type', 'Group', 'Food', 'Allergy'])

print(df['Allergy'].where(df['Food'] == input()).dropna() )     #print out alergi
  
#Tambahan
cord = output[1][0]
x_min, y_min = [int(min(idx)) for idx in zip(*cord)]
x_max, y_max = [int(max(idx)) for idx in zip(*cord)]

image = cv2.imread('sample.jpg')
cv2.rectangle(image,(x_min,y_min),(x_max,y_max),(0,0,255),2)
plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
