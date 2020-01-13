To start the application import it to an IDE with JVM support and run it using the class GatewayServiceApplication. Application uses an in-memory database engine h2. Does not need to do a extra steps to configure the database.
Postman or any tool that could send a rest requests can be used:
The endpoints in the application are as below:
  - To create a new gateway
    endpoint: 127.0.0.1:8080/create
    request type; POST
    sample request payload: 
                                      {
                                            "serialNumber": "1D5JYT7",
                                            "name": "Main gateway",
                                            "ip": "127.0.0.1",
                                            "peripheralDevices" : [
                                                {
                                                  "uid" : "1D5JYT723",
                                                  "vendor" : "seller",
                                                  "creationDate": 1578898541000,
                                                  "status": "ONLINE"
                                                },
                                                {
                                                  "uid" : "1D5JYT724",
                                                  "vendor" : "seller",
                                                  "creationDate": 1578898541000,
                                                  "status": "ONLINE"
                                                }
                                              ]
                                      }
                                 
  - To get all gateways
    endpoint: 127.0.0.1:8080/getAll
    request type: GET
    
  - To get single gateway
    endpont: 127.0.0.1:8080/get/{{serialNumber}} - where serial number is a path verable
    request type: GET
    
  - To delete a gateway
    endpoint: 127.0.0.1:8080/delete/{{serialNumber}} - where serial number is a path verable
    request type: DELETE
