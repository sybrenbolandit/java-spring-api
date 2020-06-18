# Java Spring API
This is an example api used to display Kubernetes and Helm functionality.

## Deploy

To deploy the kubernetes resources run the following command:

```cd deployment/helm/java-spring-api | helm -n <namespace> install --values environments/test/values.yaml java-spring-api .```
