cd /opt/kafka_2.13-3.2.0

echo "Starting Zookeeper"
bin/zookeeper-server-start.sh config/zookeeper.properties &
sleep 10

echo "Starting Kafka"
bin/kafka-server-start.sh config/server.properties &
sleep 10

echo "Listening topic eventos..."
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic eventos --from-beginning