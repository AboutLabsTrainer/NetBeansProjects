#!/bin/bash

cd "$(dirname "$0")" || exit 1

echo "🚀 Building all Microservices..."
echo ""

# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

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

# Build all modules
echo "📦 Building all modules..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}✅ All services built successfully!${NC}"
    echo ""
    echo "📁 JAR files created in:"
    echo "  - eureka-server/target/eureka-server-1.0.0.jar"
    echo "  - user-service/target/user-service-1.0.0.jar"
    echo "  - order-service/target/order-service-1.0.0.jar"
    echo "  - api-gateway/target/api-gateway-1.0.0.jar"
    echo ""
    echo "🚀 Start services with:"
    echo "  ./start-all.sh"
    echo ""
    echo "🐳 Or use Docker Compose:"
    echo "  docker-compose up -d"
else
    echo -e "${RED}✗ Build failed${NC}"
    exit 1
fi
