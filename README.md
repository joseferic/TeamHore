# TeamHore
Josef Eric A0101106 (ANDROID)
Rasyid Hafiz A3192866 (ANDROID)
Riefky Arif Ibrahim M0101084 (MACHINE LEARNING)
Suci Ananda Sholihat M1721795 (MACHINE LEARNING)
Arifatul Khasanah C2832609 (CLOUD COMPUTING)
Fikih Firmansyah C3192855 (CLOUD COMPUTING)

# Dataset source (Fruit):
 - Fruit: https://www.kaggle.com/moltean/fruits
 - Digit: https://www.tensorflow.org/datasets/catalog/mnist

# Goals:
the goal of our model is how to match the input image with the machine learning model that has been created using the dataset. if the image matches, it will display the name/label of the image (string), in which case our label uses the name of the fruit and for the digit dataset, our model focuses on creating OCR.

# ML technique used:
 - ImageDataGenerator: For Data Augmentation. It is a technique of creating new data from existing data by applying some transformations such as flips, rotate at a various angle, shifts, zooms and many more. Training the neural network on more data leads to achieving higher accuracy. In real-world problem, we may have limited data. Therefore, data augmentation is often used to increase train dataset. An ImageDataGenerator class function provides a range of transformations such as: Translations, Rotations, Shearing, Changes in scale, Image fliping, and Zooming.
 - keras_preprocessing: Keras Preprocessing is the data preprocessing and data augmentation module of the Keras deep learning library. It provides utilities for working with image data, text data, and sequence data.
 - Sequential: Model is appropriate for a plain stack of layers where each layer has exactly one input tensor and one output tensor.

# Mobile Programming technique used:

# Cloud Computing technique used:

# Problems:
Our application has not been able to integrate the feature of counting fruit by writing numbers and there are still many features that have not been embedded in our application, such as games features, learning to write letters, allergy detection, and others. However, this problem seems to be easily solved because it is only constrained by a very short processing time.

# App Appearance:
 - Tampilan Login
    <img src="https://user-images.githubusercontent.com/80331973/121385638-7cf84700-c973-11eb-97de-90344630cde9.png" width="200">
    
    <img src="https://user-images.githubusercontent.com/80331973/121385668-841f5500-c973-11eb-8e3b-b7ff6b77c5e8.png" width="200">


# Summary:
In general, the model that we have created can predict the added image well and is quite accurate. The results of the test accuracy and the test loss on the OCR reached 0.059100549668073654 and 0.9830999970436096, respectively, which were good. On the other hand, the fruit detector, the results of the test accuracy and the test loss were able to reach 0.33575233817100525 and 0.9466678500175476, respectively.

 - Testing numbers in this experiment using images of random numbers from 1 to 9 taken from google in jpg/jpeg/png format and with the criteria of number images without background can give quite satisfactory prediction results.
 - Testing fruit images in this experiment using fruit images taken at random on a dataset that has been sorted (test dataset) or taken from google in jpg/jpeg/png format and with the criteria of fruit images without background can give quite satisfactory prediction results.

# Agenda:
 -  Make Restful API with App Engine to connect ML and Android.
<img src="https://user-images.githubusercontent.com/54542591/121374228-eecb9300-c969-11eb-9646-f8e6010cd57f.png" >
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
