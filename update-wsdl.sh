#!/bin/bash
parent_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$parent_path"

echo "# Updating all WSDL"
echo ""
echo "make sure the tomee server is on"
echo ""
cd ./src/main/resources/wsdl
echo "removing old WSDL"
rm -f *
echo "download DroneMaintenanceWS"
curl http://localhost:8080/drone-delivery-backend/webservices/DroneMaintenanceWS?wsdl > DroneMaintenanceWS.wsdl
echo "download DeliveryWS"
curl http://localhost:8080/drone-delivery-backend/webservices/DeliveryWS?wsdl > DeliveryWS.wsdl
echo "download DeliveryScheduleWS"
curl http://localhost:8080/drone-delivery-backend/webservices/DeliveryScheduleWS?wsdl > DeliveryScheduleWS.wsdl
echo "download InvoiceWS"
curl http://localhost:8080/drone-delivery-backend/webservices/InvoiceWS?wsdl > InvoiceWS.wsdl
echo ""
echo "Done"
