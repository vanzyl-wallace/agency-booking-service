# Springboot REST Service
# agency-booking-service



    SpringBoot Application
    Postman local-spring-boot collection
    Postman local-docker environments
    H2 Embedded Database
    Docker

# Running the application from IntelliJ IDEA:
    Active Profile local    
    Run/Debug Configuration
        - Specify "local" in the Active profiles: configuration settings for the AgencyBookingApplication.
        
    Activating h2 dependency 
        - h2 dependency is set to test scope, so it will only be used for tests, 
        - this is to prevent it from running in a production env.
        - however u can enable it for runtime use in local development by following the next step.
            - Step: check the checkbox of h2 under Profiles in the Maven side panel.
                - Note: You might have to refresh/reload maven dependencies a couple of times depending on IDE.
                        - If you are still struggling to get the h2 running, then change the scope in the POM to runtime.

# Running the application inside a docker container:
    Execute the following commands in the terminal:
        - mvn clean package -P local -P h2
        - docker build --tag=agency-booking-service-image:latest . 
        - docker run -p8080:9090 agency-booking-service-image:latest
            - you can now access the application through port 8080 of the docker container which will map to the application port 9090

# Security
    There are two users allowed to access the api:
    
    Admin user: Can access all api calls.
      Username: admin
      Password: p@55w0rd
      
      Guest user: Can only access get all reservations api call.
      Username: guest
      Password: p@55w0rd1

# Running the Postman Collection
    Import the DigiCert Agency Booking postman collection and environment variables, from the postman directory in the root project folder, into your postman collection.
    You need to specify the environment variable collection to switch between local spring boot running from ide or local docker container.
    The postman collection can be executed independently or by running the Test Case collection in automated sequence using the collection runner.

    On initial startup there will be one reservation loaded in the database.
        - You can execute Get All Reservations to view the reservations in the database.
        - Then you can execute Make Reservation to make a booking, if you try to execute the same payload again, you will trigger a data constraint violation on the room number.
        - When you execute the Get All Reservations, you will note the addititional reservation made.
        - You can update a reservation by executing Update Booking, you need to provide the id of the booking(which you can find when executing the Get All reservations, in this case we are using the id from the one we created: 2)
            - After executing the Update Booking, you will notice the name changed when executing Get All Reservations.
            - If you execute the Update Booking again, but this time giving it a room number already used i.e 204 used by the initial reservation, you will trigger a data constraint violation on the room number.
        - You can delete a booking by executing Cancel Booking you need to provide the id of the booking(which you can find when executing the Get All reservations, in this case we are using the id from the one we created: 2)
        - When executing the Get All Reservations you will note just the initial reservation still active.
        - If you execute Cancel Booking usingthe same id, you will trigger an entity not found error.
