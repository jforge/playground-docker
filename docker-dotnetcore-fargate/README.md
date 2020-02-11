# Docker'ed DotnetCore 2 App to K8s / AWS EKS / Fargate

![Components Dotnet Docker Fargate](https://yuml.me/diagram/plain/activity/(ASP.NET%20Core2)-%3E(Docker%20Container)-%3E(AWS%20Fargate),(Docker%20Container)-%3E(AWS%20EKS%20[K8s]))


## Step 1

- (MacOS) JetBrtains Rider mit .NET Core 2.1 (& Mono)
- ASP.NET Core 2.1 App
- Docker Image w/ Base Image "microsoft/dotnet:2.1-sdk"
- Push -> Docker Hub Repository

## Step 2

- Minikube (local K8s Cluster)
- AWS EKS Kubernetes Cluster
- AWS Fargate (ECS) Cluster)

Definitions:

ECS ~= Amazon "Docker as a Service"
EKS ~= Managed K8s (~ AKS(ACS), GKE)
Fargate ~= Amazon "Launchtype" ("more serverless") für ECS & EKS (2018)


Flow:

Builder a container image 
-> Choose orchestrator (Choose a container orchestration solution)
-> Define application (Define your containerized application requirements)
-> Launch containers (AWS Fargate launches your containers)
-> Run containers (AWS Fargate manages all the underlying container infrastructure)
