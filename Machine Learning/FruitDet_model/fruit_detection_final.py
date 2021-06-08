"""
# Import Data from Kaggle
"""

!pip install kaggle

from google.colab import files  #Import kaggle.json
files.upload()

!mkdir -p ~/.kaggle
!cp kaggle.json ~/.kaggle/
!chmod 600 ~/.kaggle/kaggle.json

!kaggle datasets download -d moltean/fruits  #Download dataset

!unzip fruits.zip -d my_data  #Unzip the dataset

"""# Modelling

> Setup libraries & imports
"""

import cv2
import keras_preprocessing
import numpy as np
import pandas as pd
import tensorflow as tf
import matplotlib.pyplot as plt
import matplotlib.image as implt

import warnings
warnings.filterwarnings('ignore')

from sklearn.datasets import load_files
from keras_preprocessing import image
from keras_preprocessing.image import ImageDataGenerator
from tensorflow import keras
from tensorflow.keras import layers
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,Conv2D,Activation,MaxPooling2D,SpatialDropout2D,Flatten,Dropout
from tensorflow.keras.utils import to_categorical

# fix 'DirectoryIterator' object has no attribute 'shape'
from tensorflow.python.keras.utils.data_utils import Sequence
from keras.preprocessing.image import array_to_img, img_to_array, load_img

"""

> Load test & training folder

"""

test_path = "/content/my_data/fruits-360/Test"
train_path = "/content/my_data/fruits-360/Training"

img_height, img_width = 56, 56

"""

> Image Processing

"""

train_ImageGen = ImageDataGenerator(
    rescale = 1./255,
)

train_data = train_ImageGen.flow_from_directory(directory = train_path,
                                                      target_size = (100, 100),
                                                     batch_size = 64,
                                                        shuffle=True,
                                                       color_mode = 'rgb',
                                                     class_mode = 'categorical'
)

valid_datagen = ImageDataGenerator(rescale = 1./255)



test_data = valid_datagen.flow_from_directory(directory = test_path,
                                                 target_size = (100, 100),
                                               class_mode = 'categorical'
)

"""

> Building Model

"""

model = Sequential()
model.add(Conv2D(16, (3,3),input_shape=(100,100,3)))
model.add(Activation('relu'))
model.add(SpatialDropout2D(0.3))
model.add(MaxPooling2D(2,2))

model.add(Conv2D(32,(3,3),activation='relu'))
model.add(SpatialDropout2D(0.3))
model.add(MaxPooling2D(2,2))

model.add(Conv2D(64,(3,3),activation='relu'))
model.add(SpatialDropout2D(0.3))
model.add(MaxPooling2D(2,2))

model.add(Flatten())

model.add(Dense(262))
model.add(Dropout(0.5))
model.add(Dense(131,activation = 'softmax'))

model.compile(loss='categorical_crossentropy',
              optimizer='Adam',
              metrics=['accuracy'])
model.summary()

early_stop = keras.callbacks.EarlyStopping(
            monitor="val_loss",
            min_delta=1e-2,
            patience=4,
            verbose=1,
        )

history = model.fit(train_data, validation_data=test_data, shuffle=True, epochs=30, 
                    verbose=1, workers=3,callbacks = [early_stop])

"""

> Accuracy

"""

score = model.evaluate_generator (test_data,verbose=0)
print("Test loss:", score[0])
print("Test accuracy:", score[1])

history_frame = pd.DataFrame(history.history)
history_frame.loc[:, ['loss', 'val_loss']].plot()
history_frame.loc[:, ['accuracy', 'val_accuracy']].plot();

"""# Save the model

> Save the model (.pb)
"""

import tensorflow as tf
fruitmodel = "/content/drive/MyDrive/Project/Fruit/Savedmodel/SavedmodelFruit1"
tf.saved_model.save(model, fruitmodel)

"""

> Convert Model from saved model to Tflite

"""

converter = tf.lite.TFLiteConverter.from_saved_model(fruitmodel) # path to the SavedModel directory
tflite_model = converter.convert()

"""

> Save Tflite model (.tflite)

"""

with open('/content/drive/MyDrive/Project/Fruit/tflitefruit/modelFruit13.tflite', 'wb') as f:
  f.write(tflite_model)

"""# Predicting images

> Image preprocess
"""

from PIL import Image
from keras.preprocessing import image

#preprocess file
def prepare_img(path):
    img = image.load_img(path,target_size=(100,100))
    img_arr = image.img_to_array(img)
    img_arr_expnd  = np.expand_dims(img_arr,axis=0)
    img = keras.applications.mobilenet_v2.preprocess_input(img_arr_expnd)
    
    return img

labels = list(test_data.class_indices.keys())

"""

> Prediction 1

"""

testImg1 = plt.imshow(Image.open('/content/my_data/fruits-360/Test/Apple Red Yellow 1/32_100.jpg'));

prep_img1 = prepare_img('/content/my_data/fruits-360/Test/Apple Red Yellow 1/32_100.jpg')
pred1 = model.predict(prep_img1)
labels[np.argmax(pred1)]

"""

> Prediction 2

"""

testImg2 = plt.imshow(Image.open('/content/my_data/fruits-360/Test/Corn/171_100.jpg'));

prep_img2 = prepare_img('/content/my_data/fruits-360/Test/Corn/171_100.jpg')
pred2 = model.predict(prep_img2)
labels[np.argmax(pred2)]
