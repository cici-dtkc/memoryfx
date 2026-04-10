#!/bin/bash
set -e

# Start Xvfb virtual display
Xvfb :99 -screen 0 1280x720x24 &
export DISPLAY=:99

# Start VNC server
vncserver :1 -geometry 1280x720 -depth 24 -SecurityTypes None &
sleep 2

# Start noVNC web server
websockify --web=/usr/share/novnc 6080 localhost:5901 &

# Run JavaFX app
java --module-path /usr/share/openjfx/lib \
     --add-modules javafx.controls,javafx.fxml \
     -Xms256m -Xmx512m \
     -jar app.jar
