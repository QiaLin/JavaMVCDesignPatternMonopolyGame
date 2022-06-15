# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Qia Lin

**Email:** lin.qia1@northeastern.edu

**Preferred Name:** Qia Lin



### About/Overview
The milestone4 provide a nice UI interface to play the lucky game

![demo](https://user-images.githubusercontent.com/47118475/165420983-ca016d17-ef34-4004-8685-8a33e561f4bf.png)
The milestone3 project update the game to be able to play with winning or losing when target character attacked by player is dead below 0 health, enable players to move a pet to make influence of visibility of the room space. 

The milestone2 project provide a Command design pattern controller to the project and run the games with multiple players with limited turns.

The milestone1 project provide a game model implementation for a lucky world including drawing map, map graphical and text representation details and game progress console log. 


### List of Features Milestone 4
A nice UI interface for play MyLuckyGame

### How to Run miltstone 4
  1. Launch CMD in depository res/ of this project
  2. Enter the following command in CMD
  3. java -jar milestone4.jar mansion.txt [turns]
  For example,
  java -jar milestone4.jar mansion.txt 8  
  means that will be 8 runs in this game.
  4. Then enter the following exactly command  to run the program
            Game Rules
  a) Click the menu begin game to begin game
  b) Click the board to move<br>
  c) Press "l" to lookaround current space<br>
  d) Press "k" to attack the Target Character<br>
  e)Press  "p" to pick item to current space<br>
  f) Kill the TargetCharacter with devil pic to win






### List of Features Milestone 3
Allow player to play game with winning and losing
The game rule is win when target character dead or maximum turns of games reached

Allow player to set limit turn through command line eg. run the command:  java -jar milestone3.jar mansion.txt 8
The last number 8 means that will be 8 runs in this game.


### How to Run miltstone 3
  1. Launch CMD in depository res/ of this project
  2. Enter the following command in CMD
  3. java -jar milestone3.jar mansion.txt [turns]
  For example,
  java -jar milestone3.jar mansion.txt 8  
  means that will be 8 runs in this game.
  4. Then enter the following exactly command such as go , add , addc, save and exit to run the program
  5. -------- Start Menu -------
     1. Enter go to startgame
     2. Enter add to add a player
     3. Enter addc to add a Computer player
     4. Enter save to save World to a PNG file
    5. Enter exit to exit
  5. Then enter the following to playGame with turns Reminder!!! enter finish after each turn of each play to continue playing the game
  6. -------- Turn Menu -------
     1. Enter move to next space
     2. Enter pick to items
     3. Enter attack to attack Target Character
     4. Enter mp to move pet
     5. Enter myinfo to show your info
     6. Enter lookaround to show all players
     7. Enter finish to end turn
      


### How to Run miltstone 2
  1. Launch CMD in depository res/ of this project
  2. Enter the following command in CMD
  3. java -jar milestone2.jar mansion.txt [turns]
  For example,
  java -jar milestone2.jar mansion.txt 8  
  means that will be 8 runs in this game.
  4. Then enter the following exactly command such as go , add , addc, save and exit to run the program
  5. -------- Start Menu -------
      Enter go to startgame
      Enter add to add a player
      Enter addc to add a Computer player
      Enter save to save World to a PNG file
      Enter exit to exit
  5. Then enter the following to playGame with turns Reminder!!! enter finish after each turn of each play to continue playing the game
  6. -------- Turn Menu -------
      Enter move to next space\n");
      Enter dsp to display current space with items\n");
     Enter pick to items\n");
      Enter myinfo to show your info\n");
      Enter lookaround to show all players\n");
      Enter finish to end turn\n
      


### How to Run miltstone 1

Describe how to run your program from the JAR file. Describe what arguments are needed (if any) and what they mean.

  1. Launch CMD in depository res/ of this project
  2. Enter the following java -jar milestone1.jar mansion.txt
  3. mansion.txt is just a sample file to put into jar file to read the game info. It can be passed through other test file However, it should followed the format of the following:
    World description consists of two lines:
        the first line contains the number of rows, the number columns, and the name of the world
        the second line contains the health and name of the target character
    Spaces or rooms are specified in an ordered, 0-indexed list. The first line of this section contains a single number that represents the number of spaces or rooms that make up the world. Then each space or room is specified on its own line containing:
        (row, col) of the upper left corner
        (row, col) of the lower right corner
        the name of the space or room
    Items are also specified in an ordered list. The first line of this section contains a single number that represents the number of items. Then each item is specified on its own line containing:
        the index of the room in which the item can be found
        the amount of damage the item could do if it was used to attack the target character
        the name of the item




### List of Features Milestone 1
Determine the neighbors of any space. Spaces that share a "wall" are neighbors. Spaces can see into their neighboring spaces.

Display information about a specified space in the world. In addition to the name of the space, this information should include what items are in the space and what spaces can be seen from the specified space.

Move the target character around the world. The target character starts in space 0 and moves from space to space in order using the ordered, 0-indexed list of spaces found in the world specification. Thus if they are in space 5, they would move to space 6 even if these spaces are not neighbors.

 Create a graphical representation of the world map (like the image above) in the form of a BufferedImage (Links to an external site.). Learn more about this in the Technical Tidbits later in this description.




### How to Run miltstone 1

Describe how to run your program from the JAR file. Describe what arguments are needed (if any) and what they mean.

  1. Launch CMD in depository res/ of this project
  2. Enter the following java -jar milestone1.jar mansion.txt
  3. mansion.txt is just a sample file to put into jar file to read the game info. It can be passed through other test file However, it should followed the format of the following:
    World description consists of two lines:
        the first line contains the number of rows, the number columns, and the name of the world
        the second line contains the health and name of the target character
    Spaces or rooms are specified in an ordered, 0-indexed list. The first line of this section contains a single number that represents the number of spaces or rooms that make up the world. Then each space or room is specified on its own line containing:
        (row, col) of the upper left corner
        (row, col) of the lower right corner
        the name of the space or room
    Items are also specified in an ordered list. The first line of this section contains a single number that represents the number of items. Then each item is specified on its own line containing:
        the index of the room in which the item can be found
        the amount of damage the item could do if it was used to attack the target character
        the name of the item



### How to Use the Program

Provide instructions on how to use the functionality in your program. If it is interactive, describe how to interact with your program. Pay particular attention to the parts that are not part of the example runs that you provide.

You can edit the text file to create your own world. However, be careful that two spaces cannot be overlapped it will throw exception. Be sure to check the error throw in jar.



### Example Runs

SampleRun.txt                Milestone 1 Sample Run

M2SampleRun.txt              Milestone 2 Sample Run

M3SampleRun.txt              Milestone 3 Sample Run: All the functionalities demonstrate including moving the pet, adding player, attack the target character and identify                                                        if game end, etc. User should use commands as following:
                                                       java -jar milestone3.jar mansionm3sr.txt
                                                            
  res/demo ![image](https://user-images.githubusercontent.com/47118475/165421070-1b188f80-ffbd-4158-bdce-01c535a9a131.png)



### Design/Model Changes

Document what changes you have made from earlier designs. Why did you make those changes? Keep an on-going list using some form of versioning so it is clear when these changes occurred.




### Assumptions

The text file should be followed the above mention game commands otherwise, it won't work!


### Limitations

It have to clicked and type the keyboard to play the game and have to know the game rules.  



### Citations

Determination of overlapped spaces：https://www.cxyzjd.com/article/zouxin_88/100831313 


