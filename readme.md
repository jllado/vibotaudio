### Install
- gradlew build -x test
- docker-compose up --build -d

#### Use
- Build audio: 
curl --header "Content-Type: application/json" --request POST --data '{ "text": "Hello world" }' http://localhost:10000/buildAudio
- Download audio: 
wget http://localhost:10000/audio/FOOtfXXb
