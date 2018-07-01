

## stream-source

### Build

```
./mvnw clean package -DskipTests=true
```

### Run

- Run jar (when rabbitmq is running)

```
java -jar target/stream-source-0.0.1-SNAPSHOT.jat
```

- Send test message

```
curl -v localhost:8080 -d '{"text":"Hello"}' -H 'Content-Type: application/json'
```
