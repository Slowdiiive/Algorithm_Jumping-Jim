**Jumping Jim**

# Description
This project solves the Jumping Jim maze problem using BFS algorithm.  
Jim starts from the top-left cell and jump according to the number in each cell to reach the bottom-right cell using the shortest possible path.

# Requirements
- Java JDK 8 or later
- IDE (IntelliJ)

# File List
- `JumpingJim.java`
- `jim-input.txt`
- `jim-output.txt`

# How to Compile and Run
**1 Compile the Java code**
javac JumpingJim.java

**2 Prepare the input**
Make sure `jim-input.txt` is present in the same directory.  
Format of `jim-input.txt`:

7 7
3 6 4 3 2 4 3
2 1 2 3 2 5 2
2 3 4 3 4 2 3
2 4 4 3 4 2 2
4 5 1 3 2 5 4
4 3 2 2 4 5 6
2 5 2 5 6 1 0

- First line: number of rows and columns
- Next lines: values in the maze

**3 Run the program**
java JumpingJim

The program will:
- Read the maze from `jim-input.txt`
- Build the graph
- Find the shortest jump path using BFS
- Write the path in directions to `jim-output.txt`

# Output
E S S N S W E N W E E W S E N W S E 

# Author
Lijing Li
May 6, 2025