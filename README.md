To run the server part, you need to have a development environment Intellij IDEA or Eclipse installed.
You also need to install JDK, I used Oracle OpenJDK version 19.0.2
Also, application uses Docker, so you have to install Docker if you don't want to use DBMS which was used by me.
You will need to free such ports 5432 and 8080 or replace them with new ones in application.yaml, docker-compose.yml and Dockerfile.

For running app you will need to run maven install command from Intellij IDEA ui if you are using Ultimate edition

if not, then run it in terminal, but you will need to install maven.
After that run command docker-compose up --build

If you still decide to run the program without docker, then you will need to configure application.yaml, docker-compose.yml and Dockerfile.


.............................................................................

To run frontend app, you need to have on your pc something like  VS Code. 

Also, I recommend to open app when it is running in chrome and make port 4200 free

You will need to install:

Node: 20.13.1

Angular: 18.0.0 npm install -g @angular/cli

npm: 10.5.2 

In an open project you must do next actions:

You need to install NgRx:

npm install @ngrx/store@17.2.0 --legacy-peer-deps

npm install @ngrx/effects --legacy-peer-deps

npm install @ngrx/store-devtools --legacy-peer-deps

.............................................................................

I used cypress for tests, so you have to install:

npm install cypress --save-dev --legacy-peer-deps

npm install --save-dev @types/cypress --legacy-peer-deps


And this is a command to run it

npx cypress open

.............................................................................

For localization, you will need to run next commands:

ng add @angular/localize

npm install @angular/localize --legacy-peer-deps

npm install @ngx-translate/core --legacy-peer-deps

npm install @ngx-translate/http-loader --legacy-peer-deps

.............................................................................

In addition, you have to install:

npm install date-fns --legacy-peer-deps

.............................................................................

P.S. For running a test in cypress you will need to delete the note with title Test Note.
