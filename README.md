# SASOW - Agent Based Modeling System

#### SASOW is an Open Agent Based Simulation for online WOM

![image](https://user-images.githubusercontent.com/45185542/146555223-42e49c93-cc9e-4834-b86e-fc9335c5df5d.png)

## About

SASOW is a software to simulate social networks, its development is focused on an open implementation where the user/ developer will be able to generate a simple to build simulation by writing a few lines of code.

SASOW is mainly focused on WOM (Word of Mouth Marketing) but we hope to achieve a robust system capable of facilitating the modeling of any social network.

SASOW has an open architecture that will allow the programmer to interact with the different classes and elements of the system and, in the future, to access a metaprogramming abstraction layer. 

The Current SASOW Architecture is as follows

![image](https://user-images.githubusercontent.com/45185542/146765498-c130250d-68fb-40fd-b4bd-c5a51091f524.png)

A user who uses SASOW in a normal way, will focus his use mainly through the ```Experiment and Scenarios layers```. While a user who needs a more specialized or complex simulation will have access to all the ```abstraction layers```, where he will be able to create a new simulation capable of adapting to his needs by using the ```Essential layer```, which is nothing more than the core of SASOW.

- ```Experiment``` is the first abstraction layer which allows you to configure the simulation and helps you to set the simulation objective.
- ```Scenarios``` is a central layer between Experiment and Essential. Scenarios contains a collection of different social networks ready for use, this would include the specialized simulations created by the developer.

- ```Essentials``` is the heart of SASOW and provides all the logic support to create new scenarios that adapt to the different uses that can be given to SASOW.

- The last abstraction ```layer Meta``` refers to a metaprogramming layer that will allow the developer to use SASOW at all levels.


## Installation
if you want to use SASOW software, you need to install and following the documentation

clone or download repository

- ```git clone https://github.com/D1eku/SASOW.git```

![image](https://user-images.githubusercontent.com/45185542/146491346-7dd4ef8e-7c98-4f41-a5da-a98a75597b4a.png)

### Ubuntu 20.04 
You will need java for execute, install it using 
- ```sudo apt install openjdk-11-jre```
- ```cd SASOW```
- ```java -jar dist/sasow\ jar\ file/sasow.jar```
- enjoyed!

### Windows

donwload or clone, extract where you like and have fun!

## Usage 
### Normal Mode

For his use in normal mode, first go to the proyect folder and then go to "dist" folder

![image](https://user-images.githubusercontent.com/45185542/146491507-bbf2b10a-909b-4c3e-b7e6-71aca81ee22b.png)

![image](https://user-images.githubusercontent.com/45185542/146491547-7609e0ff-6f98-4cb9-a3f9-da397ee76599.png)

After go to "sasow jar file folder". In this folder you can find the sasow software jar file and some experiments examples

![image](https://user-images.githubusercontent.com/45185542/146491604-fdc46322-fb7a-499a-aabc-4dedce7e7eb1.png)

![image](https://user-images.githubusercontent.com/45185542/146491990-97635b4b-1873-4eff-9a54-a5dead2307ed.png)

if you open the examples folder you can find some examples, for experiment config and his respect result .csv files 

![image](https://user-images.githubusercontent.com/45185542/146492012-9d8b8ae4-cae1-4879-9bff-f75e48e8b020.png)

![image](https://user-images.githubusercontent.com/45185542/146492113-899832c6-3848-45fa-8768-7e907366cd48.png)

![image](https://user-images.githubusercontent.com/45185542/146492125-f18745a8-1eff-4e1d-8919-9bcb3c4c85a4.png)

if you want to use some .cfg example just open sasow.jar 

![image](https://user-images.githubusercontent.com/45185542/146492183-4133912e-1354-4b90-9c7e-fb188498051d.png)

and you can use sasow software

![image](https://user-images.githubusercontent.com/45185542/146492215-5245726b-1eea-44eb-a4e5-c465d01a63cf.png)

for load .cfg files go to "Load Experiment Config File" and just find some .cfg file for sasow software

![image](https://user-images.githubusercontent.com/45185542/146492251-9e8711f4-7e12-4809-98ce-06e2e725f69a.png)

![image](https://user-images.githubusercontent.com/45185542/146492309-6e42d901-600f-443e-96a7-062246389ba9.png)

![image](https://user-images.githubusercontent.com/45185542/146492369-de1699cd-d773-4676-8747-cc1f3c886529.png)

and then the experiment load from you interface and now you can start simulation for generate csv files and output data

![image](https://user-images.githubusercontent.com/45185542/146492468-2b89f516-b2f4-4ecd-ab73-e996f5870797.png)

![image](https://user-images.githubusercontent.com/45185542/146492817-3b547c6a-45b5-4e77-b4e6-fb40fd465364.png)

![image](https://user-images.githubusercontent.com/45185542/146492888-8b5be642-6d9f-48f7-8e46-2a2cdf12601f.png)

after you can use other tools for data tratament

![image](https://user-images.githubusercontent.com/45185542/146492956-cb436e74-4e0e-4596-9b87-27096dfd363d.png)

![image](https://user-images.githubusercontent.com/45185542/146493094-9c11b94c-94df-4274-998c-e516f4f2f44b.png)


### Expert Mode

If you need more functionality feel freedom to enter and edit the code supporting you with the essential model datahandler, and more classes !! 

//Todo add information for Expert mode.
