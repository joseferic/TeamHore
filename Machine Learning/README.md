# Machine Learning Div:
Riefky Arif Ibrahim M0101084 and Suci Ananda Sholihat M1721795

# Dataset source: 
https://www.kaggle.com/moltean/fruits

properties:
 - Total number of images: 90483.
 - Training set size: 67692 images (one fruit or vegetable per image).
 - Test set size: 22688 images (one fruit or vegetable per image).
 - Number of classes: 131 (fruits and vegetables).
 - Image size: 100x100 pixels.
Filename format: imageindex100.jpg (e.g. 32100.jpg) or rimageindex100.jpg (e.g. r32100.jpg) or r2imageindex100.jpg or r3imageindex100.jpg. "r" stands for rotated fruit. "r2" means that the fruit was rotated around the 3rd axis. "100" comes from image size (100x100 pixels). Different varieties of the same fruit (apple for instance) are stored as belonging to different classes.

# Topic:

# technique used:
 - ImageDataGenerator: For Data Augmentation. It is a technique of creating new data from existing data by applying some transformations such as flips, rotate at a various angle, shifts, zooms and many more. Training the neural network on more data leads to achieving higher accuracy. In real-world problem, we may have limited data. Therefore, data augmentation is often used to increase train dataset. An ImageDataGenerator class function provides a range of transformations such as: Translations, Rotations, Shearing, Changes in scale, Image fliping, and Zooming.
 - keras_preprocessing: Keras Preprocessing is the data preprocessing and data augmentation module of the Keras deep learning library. It provides utilities for working with image data, text data, and sequence data.
 - Sequential: Model is appropriate for a plain stack of layers where each layer has exactly one input tensor and one output tensor.

# Problems:
 - This machine learning model has Test loss: 0.34004560112953186 and Test accuracy: 0.9353843331336975. Our problem is that the test loss is still large, which is 0.3 and there is still overfitting in our model.

# Results:

# Summary:
