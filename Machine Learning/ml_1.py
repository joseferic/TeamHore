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
  
cord = output[1][0]
x_min, y_min = [int(min(idx)) for idx in zip(*cord)]
x_max, y_max = [int(max(idx)) for idx in zip(*cord)]

image = cv2.imread('sample.jpg')
cv2.rectangle(image,(x_min,y_min),(x_max,y_max),(0,0,255),2)
plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
