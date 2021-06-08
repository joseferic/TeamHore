# -*- coding: utf-8 -*-
"""test_model.ipynb

# Test the model accuracy from input (fruit object)
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

labels

testImg = plt.imshow(Image.open('/content/my_data/fruits-360/Test/Walnut/85_100.jpg'));

prep_img1 = prepare_img('/content/my_data/fruits-360/Test/Walnut/85_100.jpg')
prep_img1.shape

pred1 = model.predict(prep_img1)

labels[np.argmax(pred1)]
