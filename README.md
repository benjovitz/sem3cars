[![Build and deploy JAR app to Azure Web App - danielcars](https://github.com/benjovitz/sem3cars/actions/workflows/master_danielcars.yml/badge.svg)](https://github.com/benjovitz/sem3cars/actions/workflows/master_danielcars.yml)

https://danielcars.azurewebsites.net/api/cars

# sem3cars

# First hand-in
4.2: 
If we dont change the column name, it will take the attribute name from camelcase and seperate it with underscore, lastName, last_name

5-a.2a: a new table was created with username as foreign key and their favourite colors

5-b.3a: again a new table was created with username as foreign key and the phone number and description as additional columns


# Second hand-in
I have created CRUD endpoints for both the car and member entity, i have created test for some of the functions in the service class.
i have created a github action to build my code everytime i push it to github.

# Fourth hand-in
Created a linux ubuntu server to host my database, that works via my local project.

Hosting my spring app via Azure app service is met with some issues, Lars talked about issues earlier with spring 3.0 and Azure app service.
Regardless, i have setup environment variables in azure, but i still get met with an application error or a 404 if i try to connect to an endpoint via danielcars.azurewebsites.net

Disabling application insights did the trick (for some reason) 

