# TeamHore
Josef Eric A0101106 (ANDROID)
Rasyid Hafiz A3192866 (ANDROID)
Riefky Arif Ibrahim M0101084 (MACHINE LEARNING)
Suci Ananda Sholihat M1721795 (MACHINE LEARNING)
Arifatul Khasanah C2832609 (CLOUD COMPUTING)
Fikih Firmansyah C3192855 (CLOUD COMPUTING)

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
the goal of our model is how to match the input image with the machine learning model that has been created using the dataset. if the image matches, it will display the name/label of the image (string), in which case our label uses the name of the fruit and for the digit dataset, our model focuses on creating OCR.

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


# Machine Learning Path: Fikih Firmansyah C3192855 and Arifatul Khasanah C2832609
# Agenda:
 -  Make Restful API with App Engine to connect ML and Android.
![console cloud google com_appengine_authuser=2 organizationId=112315058424 project=western-will-312804 serviceId=default](https://user-images.githubusercontent.com/54542591/121374228-eecb9300-c969-11eb-9646-f8e6010cd57f.png)

 -  Build database server with Cloud SQL to store data object.
![console cloud google com_sql_instances_db-check_overview_authuser=2 organizationId=112315058424 project=western-will-312804 cloudshell=true](https://user-images.githubusercontent.com/54542591/121374304-fe4adc00-c969-11eb-9949-9851d464929f.png)

 -  Create Cloud Stroage Bucket as media storage or content of our data.
![console cloud google com_storage_browser_authuser=2 organizationId=112315058424 project=western-will-312804 prefix=](https://user-images.githubusercontent.com/54542591/121374362-09057100-c96a-11eb-92b3-b9b3ed50411f.png)

 -  Helping ML Team for training data using jupyter notebook on AI Platform.
![console cloud google com_ai-platform_notebooks_details_locations_us-central1-a_instances_tensorflow-2-5-20210531-230118_authuser=2 organizationId=112315058424 project=western-will-312804](https://user-images.githubusercontent.com/54542591/121374433-16226000-c96a-11eb-9651-0e40b4bf16c0.png)

 -  Create Log metrics and uptime checks to monitor logs from microservices.
![console cloud google com_monitoring_metrics-explorer_pageState=%7B%22xyChart%22_%7B%22dataSets%22_%5B%7B%22timeSeriesFilter%22_%7B%22filter%22_%22metric type%3D%5C%22logging googleapis com%2Fuser%2Fhoreee-api-m (1)](https://user-images.githubusercontent.com/54542591/121374509-25a1a900-c96a-11eb-8781-eee5136b4bf1.png)


# Summary:
In general, we are ready to serve the needs of other teams such as those who want tensorflow with kers for training data, resfull api, web servers, and other services that are ready to support projects.
