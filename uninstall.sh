#!/usr/bin/env bash

helm delete --purge istio-tracing-java-a
helm delete --purge istio-tracing-java-b
helm delete --purge istio-tracing-java-c
helm delete --purge istio-tracing-java-d
helm delete --purge istio-tracing-java-e
