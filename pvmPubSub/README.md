# Google Pub/Sub Java Examples
These are examples for using Java on a local envirnoment

## Running locally
Some samples have specific instructions. If there is a README in the sample
folder, please refer to it for any additional steps required to run the sample.

In general, the samples typically require:

1. Install the [Google Cloud SDK](https://cloud.google.com/sdk/), including the
[gcloud tool](https://cloud.google.com/sdk/gcloud/), and
[gcloud app component](https://cloud.google.com/sdk/gcloud-app).
1. Setup the gcloud tool. This provides authentication to Google Cloud APIs and
services.

        gcloud init

1. Acquire local credentials for autheticating with Google Cloud Platform APIs:

        gcloud beta auth application-default login

1. Clone this repo:

        git clone https://github.com/joeint/gcp_examples.git
        
1. cd into the directory 
    
        cd pvmPubSub
        
1. Build the jar:
        
        mvn clean package
        
1. Run the jar

        java -jar target/pvmPubSub-0.0.1-SNAPSHOT-jar-with-dependencies.jar
