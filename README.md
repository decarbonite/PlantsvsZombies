# Plants vs Zombies
SYSC 3110 - Third Year Project

## Folders description
- Documentation - Java doc generated from classes
- Diagrams - UML diagrams (Class and Sequence)
- Source - The project source code (.java files) and JUnit testing files

## Workload

   * View           ->    [Ahmed (Ahmed Romih)](@decarbonite) & [Dima (Dmytro Sytnik)](@vanarman)
   * Controller     ->    [Ahmed (Ahmed Romih)](@decarbonite) & [Dima (Dmytro Sytnik)](@vanarman)
   * NodeButton     ->    [Dima (Dmytro Sytnik)](@vanarman)
   * Plant          ->    [Ahmed (Ahmed Romih)](@decarbonite) & [Dima (Dmytro Sytnik)](@vanarman) & [Mrunal (Mrunal Patel)](@mrunal-patel1997)
   * MoneyPlant     ->    [Ahmed (Ahmed Romih)](@decarbonite) & [Dima (Dmytro Sytnik)](@vanarman) & [Mrunal (Mrunal Patel)](@mrunal-patel1997)
   * NPC            ->    [Ahmed (Ahmed Romih)](@decarbonite)
   * Zombie         ->    [Ahmed (Ahmed Romih)](@decarbonite)
   * Board          ->    [Ahmed (Ahmed Romih)](@decarbonite) & [Dima (Dmytro Sytnik)](@vanarman)
   * InitialScreen       ->    [Dima (Dmytro Sytnik)](@vanarman)
   * InitialScreenController      ->    [Dima (Dmytro Sytnik)](@vanarman)
   * ReadLevel      ->    [Dima (Dmytro Sytnik)](@vanarman)
   * BoardRow       ->    [Dima (Dmytro Sytnik)](@vanarman)
   * BoardNode      ->    [Dima (Dmytro Sytnik)](@vanarman)
   * Game           ->    [Ahmed (Ahmed Romih)](@decarbonite)
   * Testing        ->    [Ahmed (Ahmed Romih)](@decarbonite) & [Dima (Dmytro Sytnik)](@vanarman)

## Game specification and Design decisions

The current game is a step-by-step strategy game (simulation of the existing **Plats vs Zombies** game). In the current version, 
the game is working in real-time, using delay function to delay zombie's movement and the plants' shooting which makes it dynamic.
Plants are placed by the Player at anytime of the game as long as the money is sufficient. In order to win the game, 
Plants should kill all Zombies on the board. 

Currently, the game has two modes, regular mode; which the player starts with choosing what type of zombie he wants to face and the amount to show on the board, and XML Game Builder Mode; in which the game automatically reads the amount of zombies that would show up and number of levels from a XML file. Zombies are generated randomly at the beginning (right side) of the board.

Unlimited undo and redo functionality was added in Milestone 3. Stacks were used to track the last plants that were added to the board and their coordinates. In case of undo, you pop from the stack and remove from board, if it's redo, you add back the plant and its coordinate into the stacks.
As the instructions of implementing the undo/redo were not very clear, the way I implemeted it in case of placing a plant at (x,y) and clicking undo, then placing a different plant at same position (x,y) and doing redo would not allow the redo because there is already another plant there, therefore this plant needs to be killed to be able to do redo again. Moreover, undo also gives back the money you spent on the plant that was undo-ed, and redo would decrease your money.

The player can add plants to fight against Zombies and able to choose out of two types of plants:   
* **Plant** - Attack first available Zombie across a row were Plant is placed.
* **MoneyPlant** - Cannot attack Zombies but generates in-game money which helps the player to buy more plants. In each step of the game, if a MoneyPlant is placed, it has 50% chance to increase money by 25 money points.

Each time Plant kills Zombie Player will be rewarded by score point (each Zombie worth 10 points).
Game continue until Plants kill all Zombies or at least one Zombie reaches the end of the board 
(left side) and kills the Plant in the last cell if exists.

##  User manual
1. When the game starts, the first look is like the picture below.

    ![Initial Game Look](Documentation/images/initialgame.png)
    
    The player gets to put his name and chooses the game mode.
    
2. At the beginning of the game, the player has a few seconds to place plants before zombies start to appear on the board.
By clicking once on the Plant you want to choose from the top panel, the cursor image changes to that Plant image, then the player can choose which cell on the board to place it by clicking on that cell as long as there is no other object already placed there. 

    ![Plants Placement](Documentation/images/Plantsplacing.png)
    
3. In the top menu you can choose undo to remove a plant that you added to the board or redo to bring it back.
4. The player can save the state of the game by choosing save from the top menu and upon his return to the game he can loads to start where he left.
    
5. After the time delay is over the zombies will start to appear from the right side of the board, automatically moving towards the left side of the board.
            
    ![Zombies Display](Documentation/images/zombies.png)

6. The game ends either by the player winning; killing all zombies on the board and no more zombies are left to spawn, or losing in case of a zombie reaching the last cell at the left side of the board.

   When the player wins, a message pops up indicating victory like the picture below.
   
   ![Victory](Documentation/images/victory.png)

   When the player loses, a message pops up indicating that the player has lost like the picture below.
   
   ![Lose](Documentation/images/lose.png)
   


## Changes Log

1. Save and Load functionality was added.
2. Regular game mode added.
3. Game Level Builder Mode added.
4. Unused classes were removed.
5. JavaDoc added to the entire code.
6. Overall refactoring.
7. Reflections of the project file has been added.
8. Changes to the UML diagrams according to the changes noted above.
9. Sequence diagram has been changed into a multiple pages PDF file for each event as we were advised to by the TA.


   
## Known Issues

1. Plants are shooting at the zombies across the row, but there is no graphical image of the actual shooting, like peas getting thrown at the zombie.

2. On MacOS platform there happens to be a problem when the JOptionPane that appears declaring winning or losing at the end of the game. If the MacOS user click on the "OK" button to close it, it SOMETIMES shows the problem below. However, this doesn't affect the game experience in any way.
This issue doesn't happen on other platforms and after some research it happens to be a known issue with MacOS.

![Issue](Documentation/images/issue.png)
