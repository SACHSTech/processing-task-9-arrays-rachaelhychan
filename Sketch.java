import processing.core.PApplet;

/**
 * Avoid the Falling Snowballs Game Controlled with aswd keys and up, down, left, right arrows
 * @author Rachael Chan
 */
public class Sketch extends PApplet {
    // declaring variables
    int intSpeed = 1;
    int intBlueX = 200;
    int intBlueY = 350;
    int intLife = 3;

    boolean keyAPressed = false;
    boolean keySPressed = false;
    boolean keyDPressed = false;
    boolean keyWPressed = false;
    
    public void settings() {
        // setting the size of the screen
        size(400, 400);
    }
    // create related array of circles and ball hide statuses
    float[] circleY = {random(400), random(400), 
                      random(400), random(400), 
                      random(400), random(400), 
                      random(400), random(400), 
                      random(400), random(400)}; 
    boolean[] ballHideStatus = {false, false, false, false, false,
                                false, false, false, false, false};

    public void setup() {
        // setting background colour to grey
        background(210, 255, 173);
    }

    public void draw() {
        background(50);
        
        // restricts the speed to always be at least 1
        if (intSpeed < 1){
            intSpeed = 1;
        }

        // if keyAPressed is true, the blue circle goes left
        if (keyAPressed) {
            intBlueX--;
        }

        // if keyDPressed is true, the blue circle goes right
        if (keyDPressed) {
            intBlueX++;
        }

        // if keyWPressed is true, the blue circle goes up
        if (keyWPressed) {
            intBlueY--;
        }

        // if keySPressed is true, the blue circle goes down
        if (keySPressed) {
            intBlueY++;
        }

        // creating the blue circle
        fill(0, 0, 255);
        ellipse(intBlueX, intBlueY, 25, 25);

        for (int i = 0; i < 10; i++) {
            // set the circleX position
            float circleX = 50 * (i + 1);

            fill(255, 255, 255);

            // if ball is pressed on, hide the ball
            if (mousePressed) {
                if(dist(mouseX, mouseY, circleX, circleY[i]) < 25){
                    ballHideStatus[i] = true;    
              }
            }

            // if ball is visible and touches the blue circle, hide the ball and take away a life
            if(ballHideStatus[i] == false && dist(intBlueX, intBlueY, circleX, circleY[i]) < 25){
                ballHideStatus[i] = true;
                intLife--;    
            }

            // if ball is visible, create a 25x25 circle
            if (ballHideStatus[i] == false) {
                ellipse(circleX, circleY[i], 25, 25);
            }

            circleY[i] += intSpeed;

            // if circleY goes past the screen, set it back to zero to put it at the top of the screen
            if (circleY[i] > height){
                circleY[i] = 0;
                // make the ball visible
                ballHideStatus[i] = false;  
            }
            
            // create the green rectangles, and set them to appear according to how many lives are left
            fill(0, 255, 0);
            if (intLife > 2) {
                rect(310, 10, 20, 20);
            }
            if (intLife > 1) {
                rect(340, 10, 20, 20);
            }
            if (intLife > 0) {
                rect(370, 10, 20, 20);
            }
        }

        // if there are no more lives, the background turns white
        if (intLife == 0) {
            background(255, 255, 255);
        }

      }

      /**
       * Detects when the key is pressed in order to control the booleans that control the direction of the blue ball
       * @param none
       * @return none
       */
      public void keyPressed() {
          // if a is pressed, keyAPressed is set to true
          if (key == 'a') {
              keyAPressed = true;
          }
          // if d is pressed, keyDPressed is set to true
          else if (key == 'd') {
              keyDPressed = true;
          }
          // if s is pressed, keySPressed is set to true
          else if (key == 's') {
              keySPressed = true;
          }
          // if W is pressed, keyWPressed is set to true
          else if (key == 'w') {
              keyWPressed = true;
          }
    }
  
    /**
     * Detects when the key is released in order to control booleans that control direction of the blue ball and the speed of snowflakes
     * @param none
     * @return none
     */
    public void keyReleased() {
        // decreases speed if up is released, so that every click the speed goes down
        if (keyCode == UP) {
            intSpeed--;
        }
        // increases speed if down is released, so that every click the speed goes up
        else if (keyCode == DOWN) {
            intSpeed++;
        }
        // sets boolean keyAPressed to false if a is released
        else if (key == 'a') {
            keyAPressed = false;
        }
        // sets boolean keyDPressed to false if d is released
        else if (key == 'd') {
            keyDPressed = false;
        }
        // sets boolean keySPressed to false if s if released
        else if (key == 's') {
            keySPressed = false;
        }
        // sets boolean keyWPressed to false if w if released
        else if (key == 'w') {
            keyWPressed = false;
        }
    }
} 