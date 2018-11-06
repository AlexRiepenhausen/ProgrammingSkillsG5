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
| Java  | github.com  | ----  | Ant  | JUnit  |

### Prerequisites

* Code runs on Cirrus
* It requires ANT to build and it is included in the repository.
* Also the junit library required for testing and it is included in the repository.
* [Source code repository](https://github.com/AlexRiepenhausen/ProgrammingSkillsG5) 

### Geting the source code and the dependencies

Login to Cirrus and download the Application source code
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
input_file:src/main/resources/sample.dat
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
java -classpath ./bin main.java.application.Main -file filename.dat
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

BUILD SUCCESSFUL
```

## Summary

Summary of key design decisions and reasons for these. 
