# Simple project to echo back few vcap_services env variables

This is a simple scratch-pad project to quickly check for credhub service broker integration.

1. Assuming you have a credhub service broker installed, create a new service instance passing credentials to connect to whatever backing services as desired.

```shell script
cf create-service credhub default credhub-demo -c '{                                                                                                                                                                              ⏎ ✹
     "hostname": "q-n4s3y1.q-g70801.bosh",                                                                                                                                                                                                         "jdbcUrl": "jdbc:mysql://q-n4s3y1.q-g70801.bosh:3306/service_instance_db?user=aa0386c7b3c943e6b5dd8b55a261e394\u0026password=82fyqv8qntqozjpc\u0026useSSL=false",
     "name": "service_instance_db",
     "password": "XXXX",
     "port": 3306,
     "uri": "mysql://aa0386c7b3c943e6b5dd8b55a261e394:XXXX@q-n4s3y1.q-g70801.bosh:3306/service_instance_db?reconnect=true",
     "username": "aa0386c7b3c943e6b5dd8b55a261e394"
    }'
```

2. Push this java app with `--no-start` parameter

```shell script
git clone https://github.com/sharadg/vcap_demo
cd vcap_demo
./mvnw package -DskipTests=true 
cf push vcap_demo -p target/vcap_services-0.0.1-SNAPSHOT.jar --no-start
```

3. Bind the credhub service instance to the app
```shell script
cf bind-service vcap_demo credhub-demo
```

4. Start/Restage the app

```shell script
cf start vcap_demo
```

5. Hit the `/` endpoint to confirm that VCAP_SERVICES variable is correctly dereferenced.
