# Spring Boot Kafka Starter With Kubernetes and Helm
## Demo-Service

[![akuzni2](https://circleci.com/gh/akuzni2/spring-boot-kubernetes-helm-starter.svg?style=svg)](https://app.circleci.com/pipelines/github/akuzni2/spring-boot-kubernetes-helm-starter)

This is starter project for an application `demo-service` - a Java application using Spring Boot Kafka.
The project configuration is setup to deploy this service to a Kubernetes environment using Helm.

## Testing the service locally
### Requirements
- [Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/)
- [Helm](https://helm.sh/docs/intro/install/)
- [Docker](https://docs.docker.com/get-docker/)

#### 1) Build the jar file (Either locally or from CICD pipeline)

#### 2) Start minikube
Ideally minikube should be already setup. Follow the guides linked in the **Requirements** section
##### Mac-OSX
Choose one of the available hypervisors
```shell
minikube start --driver=hyperkit
```
This only needs to be ran the first time. 
only needs to be ran the first time
```shell
minikube start
```
Above command can be ran after you've set the driver the first time running
##### On Windows
```
minikube start --vm-driver hyperv
```

#### 3) Point the docker daemon running inside Minikube 
This command will show the environment variables that minikube uses for it's docker daemon. In the next steps I show
how to pipe those variables into the local shell environment variables. This will mean that when you run docker commands
it will use the docker daemon that minikube started up.

```shell
minikube docker-env
```

##### Mac-OSX

```
eval $(minikube docker-env)
```


##### Windows
```shell
minikube docker-env
minikube docker-env | Invoke-Expression
```


#### 4) Build the docker container
```shell
docker build . demo-service:latest
```

#### 5) Apply the configmap (application configurations discovered from environment variables)
```shell
kubectl apply -f demo-service-configmap.yaml
```
This file probably shouldn't be checked into your git repo but it's shown here how you'd set up your application configuration

#### 6) run the helm install

Run helm install with `--debug --dry-run` to check to make sure the deployment looks good 

```shell
helm upgrade --install --debug --dry-run demo-service demo-service
```

Or skip straight to installing

```shell
helm upgrade --install demo-service demo-service
```

#### 7) Check that the service is running
```shell
kubectl get pods
```
If you're running the service without any kafka/zookeeper running then you see some warning logs that you can't connect to kafka
```shell
kubectl logs -f <pod name>
```
Check some other info on the pod if you need to debug
```shell
kubectl describe pod <pod name> 
```

## Additional Steps
#### Uninstall the service 
```shell
helm uninstall demo-service
```

#### Stop minikube
```shell
minkube stop
```

# Things you should do after cloning/forking
- Change the names everywhere from `demo-service` to your own application name
- change the java package name from `com.alex.demoservice` to your own package name
- Set this project up in your CICD pipeline to build the jar and deploy it to your Kubernetes environment via Helm



### TODO 
1) Set up secrets example
2) Set up a integration tests using Test Containers
3) Check template files for naming convention on 'demo-service' can it be injected in?
4) Add embedded kafka tests
5) Add some fun Emojis to this README :) 
6) Figure out running the docker/zookeepr and how we can connect them to this deployment?
7) Update the dev-tools kafka/zookeeper to latest version
8) Add circleci build process   
