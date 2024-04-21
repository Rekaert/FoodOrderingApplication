# FoodOrderingApplication
Fullstack Angular and SpringBoot project

Repos: https://github.com/orgs/udemy-dev-withK8s-AWS-codedecode/repositories

PPT: https://docs.google.com/presentation/d/10yM2esLSyXUrY0qOuR9-JfoXwm9UEMRbVYDnAC9-fVM/edit#slide=id.g25f43485316_0_0

Documentation (Jenkins doc): https://docs.google.com/document/d/1yNbHndHMdeOUBryxLpwh6k9HpUVm_NYw-MebrhD2QGg/edit#heading=h.wq12uxm22fxt


## Chocolatey Installation and choco commands
Chocolatey Installation
To install Chocolatey, please follow the steps below:

Open your web browser and go to the Chocolatey website: https://chocolatey.org/install.

Follow the instructions provided on the Chocolatey website to install Chocolatey on your system.



After successfully installing Chocolatey, you can use the following commands to install different CLI tools:

To install AWS CLI, open a command prompt or terminal and type: choco install awscli.

To install EKSctl, open a command prompt or terminal and type: choco install eksctl.


## Deployment Steps
According to the AWS documentation, follow the steps below:

Run the following command to download the required IAM policy file:

curl -O https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/v2.4.7/docs/install/iam_policy.json        

Create an IAM policy named "AWSLoadBalancerControllerIAMPolicy" using the downloaded policy document in your AWS region (e.g., eu-west-3):

aws iam create-policy --policy-name AWSLoadBalancerControllerIAMPolicy --policy-document file://iam_policy.json --region eu-west-3        

Associate the IAM OIDC provider with your EKS cluster in your AWS region (e.g., eu-west-3):

eksctl utils associate-iam-oidc-provider --region=eu-west-3 --cluster=aws-eks-cluster1 --approve        

Create an IAM service account for the "aws-load-balancer-controller" in the "kube-system" namespace of your EKS cluster (e.g., aws-eks-cluster1) in your AWS region (e.g., eu-west-3). Use the appropriate AWS account ID and IAM policy ARN:

eksctl create iamserviceaccount --cluster=aws-eks-cluster1 --namespace=kube-system --region eu-west-3 --name=aws-load-balancer-controller --role-name AmazonEKSLoadBalancerControllerRole --attach-policy-arn=arn:aws:iam::YOUR_AWS_ACCOUNT_ID:policy/AWSLoadBalancerControllerIAMPolicy --approve
Important: Ensure that you modify "782482296161" with your AWS account ID, replace "eu-west-3" with the appropriate AWS region, and substitute "aws-eks-cluster1" with the respective name of your AWS cluster.

       

Apply the Cert Manager YAML file for installation:

kubectl apply --validate=false -f https://github.com/jetstack/cert-manager/releases/download/v1.5.4/cert-manager.yaml        

Download the YAML file for the AWS Load Balancer Controller from the following URL:



Download YAML File        

Remove the specified code block from the downloaded YAML file:



apiVersion: v1
kind: ServiceAccount
metadata:
  labels:
    app.kubernetes.io/component: controller
    app.kubernetes.io/name: aws-load-balancer-controller
  name: aws-load-balancer-controller
  namespace: kube-system
        
Edit the "Deployment" section in the YAML file by changing the following parameters:



spec:
      containers:
        - args:
            - --cluster-name=your-cluster-name
            - --ingress-class=alb
            - --aws-vpc-id=vpc-xxxxxxxx
            - --aws-region=region-code
        
Replace the image name with:

codedecode25/aws-load-balancer:v2.4.7        

Apply the edited YAML file:

kubectl apply -f v2_4_7_full.yaml


## Installation command

Maven:
        sudo yum update
        sudo yum install -y maven
        mvn -version
    
Install Amazon Corretto JDK 11:
sudo yum install java-11-amazon-corretto-headless
Update the system, install Docker, and configure it to start on system boot:
        sudo yum update -y
        sudo yum install -y docker
        sudo service docker start
        sudo chkconfig docker on 
    
Download Jenkins repository configuration, install Jenkins, and start it:
        sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
        sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
        sudo yum install jenkins -y
        sudo systemctl enable jenkins
        sudo systemctl start jenkins
    
Install NVM (Node Version Manager) and Node.js version 16:
        curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash
        . ~/.nvm/nvm.sh
        nvm install 16
    
Install Git:
sudo yum install -y git
Check the status of Jenkins service:
sudo systemctl status jenkins
Retrieve the initial admin password for Jenkins:
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
Add Jenkins user to the docker group to grant access:
sudo usermod -aG docker jenkins
Run SonarQube in Docker container:
docker run -d -p 9000:9000 --name sonarqube sonarqube
Check the logs of the SonarQube container:
docker logs -f sonarqube


## Jenkins Sonar And Argo CD Doc

Create an ec2 instance t2.xlarge

Connect ec2 with ssh from local

Installations
Maven

sudo yum update
sudo yum install -y maven
mvn -version


Now Install open jdk 11 

sudo yum install java-11-amazon-corretto-headless


Now Install Docker

sudo yum update -y
sudo yum install -y docker
sudo service docker start 
sudo chkconfig docker on - This command configures the Docker service to start automatically on system boot

Overall, these commands update the system, install Docker, start the Docker service, and set it to automatically start on system boot, allowing you to work with Docker and run containerized applications on your Linux system.

Now install Jenkins

sudo wget -O /etc/yum.repos.d/jenkins.repo \
    https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
sudo yum install jenkins -y
sudo systemctl enable jenkins
sudo systemctl start jenkins

yum update -y yum.noarch


Install Node 

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash

. ~/.nvm/nvm.sh

nvm install 16

Verification: 
node -e "console.log('Running Node.js ' + process.version)"


Install Git
sudo yum install -y git
















Add following things in security grp




To check the status of jenkins

sudo systemctl status jenkins


After this get password from below command in jenkins 

sudo cat /var/lib/jenkins/secrets/initialAdminPassword


After Jenkins installation add ssh plugin
Manage jenkins - plugin
Available plugin - SSH Agent - install without restart
Manage jenkins - tools- Add Maven
























To integrate webhook in jenkins






Create ssh key in jenkins server using command
ssh-keygen -t rsa -b 4096

Command to fetch public key - 
cat ~/.ssh/id_rsa.pub

Command to fetch private key - 
cat ~/.ssh/id_rsa



Add this public key in git hub . go to setting go to ssh key and add new key

Add private key in jenkins now
Go to jenkins - manage jenkins - credentials - Add ssh username with private key


Add docker creds - username with password
Codedecode25
DOCKER_HUB_CREDENTIAL - id


Also add this no verification which will be used during git push




Go to jenkins home. Click new item. Click on pipeline. Copy Restaurant Service Http clone url











sav e n add dummy commit build automatically triggers
Nowcheck manifest file







To provide access to docker
sudo usermod -aG docker jenkins

Restart jenkins after that
sudo service jenkins restart







Sonar

Run Sonar in docker using below command
docker run -d -p 9000:9000 --name sonarqube sonarqube

To check the logs
 docker logs -f sonarqube

Hit 
15.188.80.32:9000 for sonar dashboard and username password will be admin

To generate token go to sonar dashboard
Click on user icon - my account - security and create new token


Quality GAtes



To run sonar in local n push report in sonar dashboard
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://15.188.80.32:9000/ -Dsonar.login=squ_32789bcdadb6e4337e432d6cbc100c2a1a14fde5


Now put following dependencies in pom

<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-compiler-plugin</artifactId>
   <configuration>
      <source>1.8</source>
      <target>1.8</target>
      <annotationProcessorPaths>
         <path>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.26</version>
         </path>
         <path>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>1.4.2.Final</version>
         </path>
      </annotationProcessorPaths>
   </configuration>
</plugin>
<plugin>
   <groupId>org.sonarsource.scanner.maven</groupId>
   <artifactId>sonar-maven-plugin</artifactId>
   <version>3.8.0.2131</version>
</plugin>
<plugin>
   <groupId>org.jacoco</groupId>
   <artifactId>jacoco-maven-plugin</artifactId>
   <version>0.8.8</version>
   <executions>
      <execution>
         <id>prepare-agent</id>
         <goals>
            <goal>prepare-agent</goal>
         </goals>
      </execution>
      <execution>
         <id>report</id>
         <goals>
            <goal>report</goal>
         </goals>
      </execution>
   </executions>
</plugin>


Add this command in properties
 <sonar.exclusions>**/com/codeddecode/restaurantlisting/dto/** , **/*/com/codeddecode/restaurantlisting/entity/**/*</sonar.exclusions>




To generate token go to sonar dashboard
Click on user icon - my account - security and create new token


Where to get component key
Click on project – project setting – update key
Here you will find component key
<groupId>:<artifactId>






Run sonar using below command
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://localhost:9000/ -Dsonar.login=squ_a44ef243148d9f75cb3248851df2d555d5342ee2


After this delryr ptpject




















Argo CD

Command to install argo cd in cluster

kubectl create namespace argocd

kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

kubectl port-forward svc/argocd-server -n argocd 8080:80

To Get the password
Initial username 
argocd admin initial-password -n argocd

To install argocd cli
choco install argocd-cli 

Create ssh key in jenkins server using command
ssh-keygen -t rsa -b 4096

Command to fetch public key - 
cat ~/.ssh/id_rsa.pub

Command to fetch private key - 
cat ~/.ssh/id_rsa


To delete argocd completely
kubectl delete -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

If pods are in terminating state 

kubectl delete pods --all -n argocd --grace-period=0 --force


Go to setting - repository - connect repo

Overall, the provided YAML describes an Argo CD "Application" resource that deploys application resources from a Git repository, with specific configuration options for the Argo CD Image Updater and synchronization behavior.

