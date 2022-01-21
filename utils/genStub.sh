# wget https://repo1.maven.org/maven2/io/swagger/codegen/v3/swagger-codegen-cli/3.0.20/swagger-codegen-cli-3.0.20.jar
TARGET_DIR=/tmp/genStub
java -jar swagger-codegen-cli-3.0.20.jar generate --api-package health.hbp.api.stub.iface --model-package health.hbp.api.stub.model -c config.json -i ../src/main/resources/hbp-api.yaml -l spring -o $TARGET_DIR
cp -r $TARGET_DIR/src/main/java/health/hbp/* ../src/main/java/health/hbp