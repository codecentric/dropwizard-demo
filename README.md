dropwizard-demo
===============

Dropwizard-demo

1. Clone it
2. Build it -> mvn package
3. Migrate DB -> java -jar target/dropwizard-customeradmin-demo-1.0-SNAPSHOT.jar db migrate target/classes/customer-admin.yml
4. Run it -> java -jar target/dropwizard-customeradmin-demo-1.0-SNAPSHOT.jar server target/classes/customer-admin.yml
5. check out @ -> http://localhost:8080
