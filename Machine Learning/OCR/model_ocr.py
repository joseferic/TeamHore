"""
> Setup libraries & imports
"""

import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
import cv2

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout, Activation, Flatten, Conv2D, MaxPooling2D

"""# Import Dataset from tensorflow dataset"""

mnist = tf.keras.datasets.mnist

(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train.shape

"""

> Display one of the images and normalize it

"""

plt.imshow(x_train[1])
plt.show()

plt.imshow(x_train[1], cmap = plt.cm.binary)

x_train = tf.keras.utils.normalize (x_train, axis = 1)
x_test = tf.keras.utils.normalize (x_test, axis = 1)
plt.imshow(x_train[1], cmap = plt.cm.binary)

print(y_train[1])

"""# Train the model"""

IMG_SIZE = 28
x_trainr = np.array(x_train).reshape(-1, IMG_SIZE, IMG_SIZE, 1)
x_testr = np.array(x_test).reshape(-1, IMG_SIZE, IMG_SIZE, 1)
print("Training sample dimension", x_trainr.shape)
print("Training sample dimension", x_testr.shape)

model = Sequential()
model.add(Conv2D(64, (3,3), input_shape = x_trainr.shape[1:]))
model.add(Activation("relu"))
model.add(MaxPooling2D(pool_size=(2,2)))

model.add(Conv2D(64, (3,3)))
model.add(Activation("relu"))
model.add(MaxPooling2D(pool_size=(2,2)))

model.add(Conv2D(64, (3,3)))
model.add(Activation("relu"))
model.add(MaxPooling2D(pool_size=(2,2)))

model.add(Flatten())
model.add(Dense(64))
model.add(Activation("relu"))

model.add(Dense(32))
model.add(Activation("relu"))

model.add(Dense(10))
model.add(Activation("softmax"))

model.compile(loss = "sparse_categorical_crossentropy", 
              optimizer = "adam", 
              metrics = ["accuracy"])

model.fit(x_trainr,y_train, epochs=5, validation_split= 0.3)

test_loss, test_acc = model.evaluate(x_testr, y_test)
print("test loss on 10000 test samples:", test_loss)
print("Validation accuracy on 10.000 test samples:", test_acc)

"""# Save the model"""

OCRmodel = "/content/drive/MyDrive/Project/Dataset OCR/SavedmodelOCR1"
tf.saved_model.save(model, OCRmodel)

"""

> Convert Model from saved model to Tflite

"""

converter = tf.lite.TFLiteConverter.from_saved_model(OCRmodel) # path to the SavedModel directory
tflite_model = converter.convert()

"""

> Save Tflite model (.tflite)

"""

with open('/content/drive/MyDrive/Project/Dataset OCR/modelOCR1.tflite', 'wb') as f:
  f.write(tflite_model)

"""# Predict some number from internet and test the accuracy"""

predicions = model.predict([x_testr])

pip install gTTS

"""

> import an image

"""

img = cv2.imread('0.png')
plt.imshow(img)

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

resized = cv2.resize(gray, (28,28), interpolation = cv2.INTER_AREA)
newimg = tf.keras.utils.normalize (resized, axis = 1)
newimg = np.array(newimg).reshape(-1, IMG_SIZE, IMG_SIZE, 1)
predicions = model.predict(newimg)
hasil = np.argmax(predicions)
hasilstr = str(hasil)

"""

> Prediction result

"""

print(hasilstr)

"""

> Perform text-to-speech

"""

from gtts import gTTS
from IPython.display import Audio
tts = gTTS(hasilstr)
tts.save('1.wav')
sound_file = '1.wav'
Audio(sound_file, autoplay=True)
