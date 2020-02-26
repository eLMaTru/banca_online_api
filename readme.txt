---This api is running at aws as a service, to run or handle the app state please use the below steps---

1. logging into ec2 instance using correct .pem

to start the service: sudo systemctl start bancaonlineapi
to stop the service: sudo systemctl stop bancaonlineapi
to see service status: sudo systemctl status bancaonlineapi

to view live logs: sudo journalctl -f -u bancaonlineapi

to view first 1000 lines in logs: sudo journalctl -n 1000 -u bancaonlineapi

you can see the below link for more information about how Run Your Java App as a Service

https://dzone.com/articles/run-your-java-application-as-a-service-on-ubuntu