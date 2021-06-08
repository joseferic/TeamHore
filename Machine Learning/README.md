# Machine Learning Div:
Riefky Arif Ibrahim M0101084 and Suci Ananda Sholihat M1721795

# table of contents
  1. 1
  2. 2
  3. 3
  4. 4
  5. 5
  6. 6
  7. 7

# Dataset source (Fruit):
https://www.kaggle.com/moltean/fruits

properties:
 - Total number of images: 90483.
 - Training set size: 67692 images (one fruit or vegetable per image).
 - Test set size: 22688 images (one fruit or vegetable per image).
 - Number of classes: 131 (fruits and vegetables).
 - Image size: 100x100 pixels.
Filename format: imageindex100.jpg (e.g. 32100.jpg) or rimageindex100.jpg (e.g. r32100.jpg) or r2imageindex100.jpg or r3imageindex100.jpg. "r" stands for rotated fruit. "r2" means that the fruit was rotated around the 3rd axis. "100" comes from image size (100x100 pixels). Different varieties of the same fruit (apple for instance) are stored as belonging to different classes.

Research papers: Horea Muresan, Mihai Oltean, Fruit recognition from images using deep learning, Acta Univ. Sapientiae, Informatica Vol. 10, Issue 1, pp. 26-42, 2018.
The paper introduces the dataset and implementation of a Neural Network trained to recognize the fruits in the dataset.

# Dataset source (Digit):
https://www.tensorflow.org/datasets/catalog/mnist

# Goals:
the goal of our model is how to match the input image with the machine learning model that has been created using the dataset. if the image matches, it will display the name/label of the image (string), in which case our label uses the name of the fruit.

# technique used:
 - ImageDataGenerator: For Data Augmentation. It is a technique of creating new data from existing data by applying some transformations such as flips, rotate at a various angle, shifts, zooms and many more. Training the neural network on more data leads to achieving higher accuracy. In real-world problem, we may have limited data. Therefore, data augmentation is often used to increase train dataset. An ImageDataGenerator class function provides a range of transformations such as: Translations, Rotations, Shearing, Changes in scale, Image fliping, and Zooming.
 - keras_preprocessing: Keras Preprocessing is the data preprocessing and data augmentation module of the Keras deep learning library. It provides utilities for working with image data, text data, and sequence data.
 - Sequential: Model is appropriate for a plain stack of layers where each layer has exactly one input tensor and one output tensor.

# Problems:
 - This machine learning model has Test loss: 0.34004560112953186 and Test accuracy: 0.9353843331336975. Our problem is that the test loss is still large, which is 0.3 and there is still overfitting in our model.

# Results:
 - Digit (OCR)

   Model Accuracy:
   
   ![image](https://user-images.githubusercontent.com/80331973/121185949-c6bc3100-c890-11eb-8072-9ff6dca0c63d.png)
   
   image input:
   
   ![image](https://user-images.githubusercontent.com/80331973/121185761-9aa0b000-c890-11eb-805c-647216697fc1.png)
   
   image prediction:
   
   ![image](https://user-images.githubusercontent.com/80331973/121185865-b2783400-c890-11eb-81a2-e0c1b236d152.png)

 - Fruit Detection

   Model Accuracy: 
   
   ![image](https://user-images.githubusercontent.com/80331973/121186154-fc611a00-c890-11eb-821d-1f0cf17c5f30.png)
   
   ![image](https://user-images.githubusercontent.com/80331973/121186396-3fbb8880-c891-11eb-985d-09b8c0d18337.png)

   Image input:
   
   ![image](https://user-images.githubusercontent.com/80331973/121186281-20bcf680-c891-11eb-87d1-b4c38edfef4a.png)

   Image prediction:
   
   ![image](https://user-images.githubusercontent.com/80331973/121186342-32060300-c891-11eb-8735-47f9d6310f73.png)

# Summary:
In general, the model that we have created can predict the added image well and is quite accurate. The results of the test accuracy and the test loss on the OCR reached 0.059100549668073654 and 0.9830999970436096, respectively, which were good. On the other hand, the fruit detector, the results of the test accuracy and the test loss were able to reach 0.33575233817100525 and 0.9466678500175476, respectively.

 - Testing numbers in this experiment using images of random numbers from 1 to 9 taken from google in jpg/jpeg/png format and with the criteria of number images without background can give quite satisfactory prediction results.
 - Testing fruit images in this experiment using fruit images taken at random on a dataset that has been sorted (test dataset) or taken from google in jpg/jpeg/png format and with the criteria of fruit images without background can give quite satisfactory prediction results.
