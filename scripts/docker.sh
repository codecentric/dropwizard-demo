#!/bin/bash

#sudo docker build -t adaman79/dropwizard-kundenverwaltung .
#sudo docker push adaman79/dropwizard-kundenverwaltung
#cat scripts/dropwizard-customeradmin.1.service > dropwizard-customeradmin.${BUILD_NUMBER}.service
#fleetctl --tunnel=10.0.3.121 submit dropwizard-customeradmin.${BUILD_NUMBER}.service
fleetctl --tunnel=10.0.3.121 stop dropwizard-customeradmin.*@8080.service
#fleetctl --tunnel=10.0.3.121 destroy dropwizard-customeradmin.${BUILD_NUMBER}-1.service
fleetctl --tunnel=10.0.3.121 start dropwizard-customeradmin.*@8080.service
