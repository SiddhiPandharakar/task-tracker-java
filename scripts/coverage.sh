#!/bin/bash

echo "Generating coverage"

mvn clean verify

echo "Report available at:"
echo "target/site/jacoco/index.html"