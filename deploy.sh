#!/usr/bin/env bash

helm install istio-tracing-java-a istio-tracing-java-a || helm upgrade istio-tracing-java-a istio-tracing-java-a
helm install istio-tracing-java-b istio-tracing-java-b || helm upgrade istio-tracing-java-b istio-tracing-java-b
helm install istio-tracing-java-c istio-tracing-java-c || helm upgrade istio-tracing-java-c istio-tracing-java-c
helm install istio-tracing-java-d istio-tracing-java-d || helm upgrade istio-tracing-java-d istio-tracing-java-d
helm install istio-tracing-java-e istio-tracing-java-e || helm upgrade istio-tracing-java-e istio-tracing-java-e
