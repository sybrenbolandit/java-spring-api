# Java Spring API
This is an example api used to display Kubernetes and Helm functionality.

## Deploy

To deploy the kubernetes resources run the following command:

```
cd deployment/helm/java-spring-api 
helm -n <namespace> install spring-api java-spring-api --values environments/test/values.yaml
```
