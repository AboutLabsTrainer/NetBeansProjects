#!/bin/bash

cd "$(dirname "$0")" || exit 1

echo "🛑 Stopping all Microservices..."
echo ""

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

if [ -d ".pids" ]; then
    for pidfile in .pids/*.pid; do
        if [ -f "$pidfile" ]; then
            PID=$(cat "$pidfile")
            SERVICE=$(basename "$pidfile" .pid)
            
            if ps -p $PID > /dev/null 2>&1; then
                echo "Stopping $SERVICE (PID: $PID)..."
                kill $PID
                echo -e "${GREEN}✓ $SERVICE stopped${NC}"
            else
                echo -e "${RED}✗ $SERVICE not running${NC}"
            fi
            rm "$pidfile"
        fi
    done
    rmdir .pids 2>/dev/null
else
    echo "No PIDs found. Trying to kill by port..."
    
    # Kill by port. lsof fehlt in manchen WSL-Distributionen, dann hilft fuser aus psmisc.
    kill_port() {
        if command -v lsof > /dev/null; then
            lsof -ti:"$1" | xargs -r kill -9 2>/dev/null
        else
            fuser -k "$1"/tcp > /dev/null 2>&1
        fi
    }
    kill_port 8761 && echo "✓ Stopped Eureka (8761)"
    kill_port 8081 && echo "✓ Stopped User Service (8081)"
    kill_port 8082 && echo "✓ Stopped Order Service (8082)"
    kill_port 8080 && echo "✓ Stopped API Gateway (8080)"
fi

echo ""
echo -e "${GREEN}✅ All services stopped!${NC}"
