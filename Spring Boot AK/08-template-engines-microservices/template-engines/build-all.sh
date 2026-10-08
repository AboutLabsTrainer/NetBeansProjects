#!/bin/bash

echo "🚀 Building all Template Engine Demos..."
echo ""

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# Build parent first
echo "📦 Building parent POM..."
mvn clean install -N
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Parent POM built successfully${NC}"
else
    echo -e "${RED}✗ Parent POM build failed${NC}"
    exit 1
fi

echo ""
echo "📦 Building all modules..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}✅ All template engine demos built successfully!${NC}"
    echo ""
    echo "📁 JAR files created in:"
    echo "  - thymeleaf-demo/target/thymeleaf-demo-1.0.0.jar"
    echo "  - freemarker-demo/target/freemarker-demo-1.0.0.jar"
    echo "  - jte-demo/target/jte-demo-1.0.0.jar"
    echo "  - groovy-demo/target/groovy-demo-1.0.0.jar"
    echo "  - pebble-demo/target/pebble-demo-1.0.0.jar"
    echo "  - mustache-demo/target/mustache-demo-1.0.0.jar"
    echo "  - jsp-demo/target/jsp-demo-1.0.0.jar"
    echo ""
    echo "🚀 Start individual demos:"
    echo "  cd thymeleaf-demo && mvn spring-boot:run"
    echo "  cd freemarker-demo && mvn spring-boot:run"
    echo "  # etc..."
else
    echo -e "${RED}✗ Build failed${NC}"
    exit 1
fi
