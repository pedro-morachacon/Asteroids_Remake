![Asteroids Arcade Banner image: Asteroids JavaFX Remake](https://github.com/pedro-morachacon/Asteroids_Remake/blob/main/AsteroidsArcadeBanner.jpg?raw=true)
# Asteroids JavaFX Remake

**Project Team:**
Xiuping Xue,
Dean O'Brien,
Joseph Tummon,
Pedro Antonio Mora Chacón

**Supervisor:**
Dr. Fatemeh Golpayegani

**Brief Description**

Recreate in Java the 1979 Atari game 'Asteroids'.  See https://freeasteroids.org/ for further game details and or game play. Our recreation project(of the original 1979 game) has one required difference and the remaining feature requirements and their implementation may not be the same.

#Features not required for this Assignment#

The following are 1979 game features that were not implementation requirements:

 - Each new player ship life, to start, may either be placed in a currently safe location (or be invincible for a brief period).
 
  - The Alien ship behavior or methods do not need to be the same (get smaller and or faster) as the 1979 game with each higher difficulty level. 

   - The point system is not required to be the same as the 1979 game. 


#Differences#

The following are 1979 game features that had different implementation requirements:

 - The Original Game thrust feature only provided movement and died out shortly after the release of the thrust feature button. Our recreation requires an equal application of the thrust feature button in the direct opposite direction to bring it to a stop.

 - This game like the original 1979 game has the player enter their initials if the score is in the top ten scores, but the implementation of the user input is a little different. 

In the original game "At the beginning of the high score initial mode, the player instructions appear at the top of the screen, and A _____ appears at the lower center of the display. Players enter initials one character at a 
time. By pressing the LEFT ROTATE pushbutton, the displayed character steps through the alphabet from A to Z. By pressing the RIGHT ROTATE pushbutton, the character steps backwards through the alphabet from A to a blank, then from Z to A. Once the game displays the desired letter, players should press the HYPERSPACE pushbutton to record the letter; then an A appears in the next space. 
If players need only two letters for their initials, they should use the blank between Z and A in one of the three locations. Pressing the HYPERSPACE pushbutton a third time will cause the initials and game score to be transferred to the "10 highest scores" listing that appears during the attract mode." (ATARI INC) 

In our version of the game, the initials "AAA" appear on the screen. 

By pressing the Right Arrow key the cursor moves to the next initial on the right. Pressing the Left Arrow key the cursor moves back to the previous initial to the left. 

Pressing the Down Arrow key the initial at the cursor location moves down to the next alphabet letter, then to underscore ("_"), then to letter "A" again in a circle. 

Pressing the Up Arrow key the initial at the cursor location moves up to the previous alphabet letter, then to underscore ("_"), then to letter "Z", again in a circle.

Pressing the enter/return key will save the three initials displayed in the top ten scores, and display the updated top ten scores with the user's initials included.


**Acknowledgments:**
"Asteroids Operation, Maintenance and Service Manual Complete with Illustrated Parts Lists" ATARI INC (PDF Attached)
