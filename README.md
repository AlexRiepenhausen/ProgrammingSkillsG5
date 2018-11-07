# Programming Skills - Coursework

## Team members
```
Hassan Al Marhoon
Alexey Riepenhausen
Almas Dossymbekov
Qirong Wu
```
## Brief Information

| Language  | Revision Control  | Debuggers  | Build Tool  | Test Tool  |
|:--:|:--:|:--:|:--:|:---:|
| Java  | github.com  | Eclipse IDE  | Ant  | JUnit  |

### Prerequisites

* Code runs on Cirrus
* It requires ANT to build and it is included in the repository.
* Also the junit library required for testing and it is included in the repository.
* [Source code repository](https://github.com/AlexRiepenhausen/ProgrammingSkillsG5) 

### Geting the source code and the dependencies
If you happen to have the ProgrammingSkillsG5.zip file you can skip the step below
and simply unzip the file provided in your home directory on Cirrus,
otherwise download the application source code from git:
```
cd ~
git clone https://github.com/AlexRiepenhausen/ProgrammingSkillsG5.git

```
Add execute permission for ant binaries and set the environment for the ANT 
```
chmod u+x ~/ProgrammingSkillsG5/ProgrammingSkillsG5/apache-ant-1.10.5/bin/*
export PATH=~/ProgrammingSkillsG5/ProgrammingSkillsG5/apache-ant-1.10.5/bin:$PATH 
```
Load the required java modules
```
module load spack
module load jdk-8u92-linux-x64-gcc-6.2.0-24xtmiy
```
### Build

Change directory to the source location and build the code

```
cd ~/ProgrammingSkillsG5/ProgrammingSkillsG5
ant build -f build.xml

```
If the build successful you should get 
```
build:

BUILD SUCCESSFUL
```
### Run

The application has three running options. 
The **First Option** is the default which will prompt the user to enter the parameters and you can run it with this command and follow the instructions.

```
cd ~/ProgrammingSkillsG5/ProgrammingSkillsG5
java -classpath ./bin main.java.application.Main
```
The **Second Option** is to read the parameters from a configuration file. The configuration (e.g. config.txt) file should be something like this:
```
input_file:src/main/resources/islands.dat
birth_rate_hares:0.08
predation_rate:0.04
birth_rate_pumas_per_hare_eaten:0.02
mortality_rate_pumas:0.06
diffusion_rate_hares:0.2
diffusion_rate_pumas:0.2
size_time_step:0.4
```
To run the application with this configuration
```
java -classpath ./bin main.java.application.Main -config config.txt
```
The **Third Option** is to specify only the input file name and the application will run with the default parameters by run this
```
java -classpath ./bin main.java.application.Main -file src/main/resources/islands.dat
```
The output files will be available for every 10 iterations in the `PPM_Files` folder.
### Test

The jUnit test cases are supplied and can be checked with this command and check the test summary.
```
cd ~/ProgrammingSkillsG5/ProgrammingSkillsG5
ant TestApplication -f build.xml
```
You should expect to see the result as this:
```
TestApplication:
    [mkdir] Created dir: /ProgrammingSkillsG5/ProgrammingSkillsG5/junit
    [junit] Running test.java.input.ParserTest
    [junit] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.037 sec
    [junit] Running test.java.world.GridSquareTest
    [junit] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 sec
    [junit] Running test.java.world.GridSquareTypeTest
    [junit] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec
    [junit] Running test.java.world.LandscapeTest
    [junit] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 sec
    [junit] Running test.java.outputs.OutTest
    [junit] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.01 sec


BUILD SUCCESSFUL
```

## Summary
![Alt text](C:\Users\wuqir\OneDrive\Desktopimg.png?raw=true "ProgrammingSkillsG5_UML")

### Population density lower boundary: 
To restrict the population densities from going below zero, it is designed to have statements to reset the population density values to 0.0 automatically before every single update is complete, which is the simplest method. More advanced solutions are available, however, could lead to further undesired problems, and therefore are not chosen for implementation.
### Provision of Landscape file: 
The landscape file is included and packed all together with others into the zip file which is to be downloaded by the user. It is designed in this way for reasons like better portability and easier for the user to handle.
### Robustness of the program:
It is designed to have reduced level of robustness for this program. Once the system receives some values passed through the command line, it checks whether they are valid, which is checking the input values are convertible to the type, double, or not. If is, the values are then translated into a double type, and then processed by the system. If not, it will prompt the user to type the value again. It is designed for circumstances in which the user is familiar with or at least know how to operate this system.
### Global variables: 
Yes, it is OK with using global variables. Otherwise, the system would be clunky. To prevent the system from dangerous behaviors like unwanted value updates, it is designed in a way, for example, in the Processor class would have two landscape instances which both are private: old_landscape and new_landscape. During an update to the fields of the landscape which could contain global variables that the Processor is depended on, the program makes two instances of landscape and put the new values of new_landscape. Eventually, the old_landscape gets replaced by the new_landscape. 
Visualization:




