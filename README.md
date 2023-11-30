# Springboot REST Service
# agency-booking-service



    #SpringBoot Microservice
    #Postman testing collection
    #Postman local environments
    #H2 Embedded Database
    #Swagger

# Running the application from IntelliJ IDEA:
    # Active Profile local    
    # Run/Debug Configuration
        - Specify "local" in the Active profiles: configuration settings for the AgencyBookingApplication.
        
    # Activating h2 dependency 
        - h2 dependency is set to test scope, so it will only be used for tests, 
        - this is to prevent it from running in a production env.
        - however u can enable it for runtime use in local development by following the next step.
            - Step: check the checkbox of h2 under Profiles in the Maven side panel.
                - Note: You might have to refresh/reload maven dependencies a couple of times depending on IDE.
                        - If you are still struggling to get the h2 running, then change the scope in the POM to runtime.

# Running the application inside a docker container:
    # Execute the following commands in the terminal:
        - mvn clean package -P local -P h2