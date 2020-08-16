# Lab 01

## Intro

With this practical work, you will learn a few things:
* how to clone a project from Github
* how to import a Maven project in your favorite IDE
* develop an entire JEE webapp almost from scratch
* run a webapp in Tomcat

## A drugstore story

We have to develop a webapp in order to help pharmacists with their stock of drugs.
The needs are really easy to implement, actually, the pharmacist will be able to use a "secured" webapp. Once authenticated, the app displays a list of the drugs, and a form allows to add some new drugs.

## Starter kit

If you clone this repo, you will get:
* the Maven configuration & structure (`pom.xml` and the `src` folder)
* the unit tests in order to validate your implementation
  * they are in the `src/test/java` directory
  * they do not compile, don't worry
  * it is forbidden to modify them
* the HTML templates your implementation will have to deal with
  * they are in the `src/main/webapp` directory
* you may wonder what are those `.gitkeep` files? These are just markers in order to push the correct directory tree `src/main/java` inside the repo.
  
## Your mission, should you choose to accept it

* Create several classes
  * `DrugsServlet` which manage the listing and the storage of the drugs
  * `LoginServlet` which manage the login phase
  * `SessionFilter` which handles security
  * `Pharmacist` to represent the user of the application
  * `Drug` to represent the managed drug
* The provided tests are your guide! Read them to understand what is needed. The *structure tests try to check if your class is correctly built and the *behaviour tests check the qualitfy of your algorithms. Package names and fields are not detailed, the tips are in the tests ;)

## Once built...

... you should be able to deploy it on a Tomcat and play with your brand new webapp!

# _Good luck!_
 
 
