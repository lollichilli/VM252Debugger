# VM252 Debugger

## Overview
The VM252 Debugger is a comprehensive debugging tool for the VM252 assembly language, developed using Java and Java Swing. This application features a responsive graphical user interface that allows users to run, pause, resume, and set breakpoints in their assembly language programs. It also provides a detailed view of the machine's state, including memory bytes, accumulator, and program counter values.

## Highlight Features
- **Run and Pause Programs:** Easily control the execution of your assembly programs with intuitive buttons.
- **Breakpoints:** Set breakpoints to stop the program at specific addresses for detailed inspection.
- **Memory Display:** View the memory bytes of an object file and get notifications when a memory byte changes.
- **Real-time Updates:** The ACC and PC fields update dynamically as the machine runs.
- **Input Functionality:** Enter numbers into the input field and run the program to see immediate results.
- **ContentsView:** Display the status of the machine while it's running, including messages when setting breakpoints or running inputs.

## Running the Application
To compile and run the VM252 Debugger, follow these steps:

1. Navigate to the project directory:
    ```
    cd VM252Debugger
    ```

2. Compile the main Java class:
    ```
    javac Main.java
    ```

3. Run the application:
    ```
    java Main
    ```

## App Demo
For a detailed demonstration of the VM252 Debugger, check out the demo video below:

[App Demo Video](#)  <!-- Replace # with your video link -->

## Family Tree Diagram
The following is an example family tree diagram of the components used in this project. Please replace the link with your own diagram image.

![Family Tree Diagram](<img src="https://imgur.com/s5yU26S" alt="Family Tree Diagram" width="600">)

## Components
Here are some key components of the VM252 Debugger:

- **MachineViewContainer:** Manages the main view of the virtual machine.
- **ContentsView:** Displays the status and outputs of the machine.
- **ButtonsContainer:** Contains all control buttons for interacting with the debugger.
- **ObservableVM252:** The core model that represents the state of the virtual machine and notifies the view of any changes.
- **Main:** The entry point of the application that sets up the initial UI and connects components.

## Technologies Used
- **Java**
- **Java Swing**
- **GitHub for version control**

## Contributors
- [Tung Nguyen] - Team Lead and Developer
- [Janik Niggli, CleanWell Mwalwanda]

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

