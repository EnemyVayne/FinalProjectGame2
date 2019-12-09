package Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The class is used to create an enemy, which is spawned in randomly, and can
 * also move around randomly throughout the window, until it hits a boundary.
 *
 * @author Kyle Faith
 */
public class Enemy extends Pane
{

    private static int randomInt;
    private double xPos = Math.random() * 1262.62, yPos = Math.random() * 725;
    private double xWidth = 50, yHeight = 50;
    private Rectangle rectangle = new Rectangle(xPos, yPos, xWidth, yHeight);
    private double speedX = 1;
    private double speedY = 1;
    private Timeline animation;
    private Timeline random;

    /**
     * The default constructor creates the enemy, makes an animation for the
     * movements of the enemies, and creates a new random integer every 2
     * seconds to determine the direction the enemy will move in.
     * 
     * @author Kyle Faith
     */
    public Enemy()
    {
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);

        animation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> moveEnemy()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        EventHandler<ActionEvent> eventHandler = e
                -> 
                {
                    randomInt = ( int ) (Math.random() * ((4 - 1) + 1)) + 1;

        };

        random = new Timeline(
                new KeyFrame(Duration.millis(2000), eventHandler));
        random.setCycleCount(Timeline.INDEFINITE);
        random.play();
    }

    /**
     * The parameterized constructor does the same as the default constructor,
     * except it allows for the user to chose the location that the enemy spawns
     * in.
     *
     * @param spawnX x-coordinate for spawning enemy
     * @param spawnY y-coordinate for spawning enemy
     * 
     * @author Kyle Faith
     */
    public Enemy( int spawnX, int spawnY )  //to choose where the enemy spawns
    {
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);

        xPos = spawnX;
        yPos = spawnY;

        animation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> moveEnemy()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        EventHandler<ActionEvent> eventHandler = e
                -> 
                {
                    randomInt = ( int ) (Math.random() * ((4 - 1) + 1)) + 1;

        };

        random = new Timeline(
                new KeyFrame(Duration.millis(2000), eventHandler));
        random.setCycleCount(Timeline.INDEFINITE);
        random.play();
    }

    /**
     * The method is used to pause the enemies movement.
     * 
     * @author Kyle Faith
     */
    public void pauseEnemy()
    {
        animation.pause();
    }

    /**
     * The method is used to resume the enemies movement.
     * 
     * @author Kyle Faith
     */
    public void playEnemy()
    {
        animation.play();
    }

    /**
     * Used to bind the rate of application.
     *
     * @return Binding property value for rate.
     * 
     * @author Kyle Faith
     */
    public DoubleProperty rateProperty()
    {
        return animation.rateProperty();
    }

    /**
     * The method is used to move the player north. The method works by taking a
     * specified speed and adding it to the coordinate of the player so it moves
     * by the speed every tick of the animation.
     * 
     * @author Kyle Faith
     */
    public void moveUp()
    {
        yPos -= speedY;
    }

    /**
     * The method is used to move the player south. The method works by taking a
     * specified speed and adding it to the coordinate of the player so it moves
     * by the speed every tick of the animation.
     * 
     * @author Kyle Faith
     */
    public void moveDown()
    {
        yPos += speedY;
    }

    /**
     * The method is used to move the player west. The method works by taking a
     * specified speed and adding it to the coordinate of the player so it moves
     * by the speed every tick of the animation.
     * 
     * @author Kyle Faith
     */
    public void moveLeft()
    {
        xPos -= speedX;
    }

    /**
     * The method is used to move the player east. The method works by taking a
     * specified speed and adding it to the coordinate of the player so it moves
     * by the speed every tick of the animation.
     * 
     * @author Kyle Faith
     */
    public void moveRight()
    {
        xPos += speedX;
    }

    /**
     * The method grabs the private variable x to be able to use within
     * different classes.
     *
     * @return Returns the variable found in Player class.
     * 
     * @author Kyle Faith
     */
    public double getX()
    {
        return xPos;
    }

    /**
     * The method grabs the private variable y to be able to use within
     * different classes.
     *
     * @return Returns the variable found in Player class.
     * 
     * @author Kyle Faith
     */
    public double getY()
    {
        return yPos;
    }

    /**
     * Creates a rectangular boundary for the enemy to interact with other
     * objects based on distance.
     *
     * @return Creates an invisible rectangular boundary around the NPC.
     * 
     * @author Kyle Faith
     */
    public Rectangle bounds()
    {
        return (new Rectangle(xPos + xWidth / 2, yPos + yHeight / 2, xWidth,
                yHeight));
    }

    /**
     * This method sets the players coins between 1-10 once the enemy dies.
     * 
     * @author Kyle Faith
     */
    public void Die()
    {
        Coins.setCoins(( int ) ((Math.random() * ((10 - 1) + 1)) + 1));
    }

    /**
     * Creates a random spawn location for the enemy within the window.
     * 
     * @author Kyle Faith
     */
    public void Spawn()
    {
        xPos = Math.random() * 1240;
        yPos = Math.random() * 710;
    }

    /**
     * This method is used as an animation and based on what random integer is
     * used, the enemy will move in a specific direction.
     * 
     * @author Kyle Faith
     */
    protected void moveEnemy()
    {
        switch ( randomInt )
        {

            case 1:
                if ( yPos <= 0 )
                {
                    moveDown();
                }
                moveUp();
                break;
            case 2:
                if ( yPos >= 710 )
                {
                    moveUp();
                }
                moveDown();
                break;
            case 3:
                if ( xPos <= 0 )
                {
                    moveRight();
                }
                moveLeft();
                break;
            case 4:
                if ( xPos >= 1240 )
                {
                    moveLeft();
                }

                moveRight();

                break;
        }
        rectangle.setX(xPos);
        rectangle.setY(yPos);
    }
}
