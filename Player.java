package Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The class is used to create a player, and set the animation/move operations
 * for the player. The animation is set to refresh every .1 milliseconds, and
 * checks the method "movePlayer" every time it ticks. The ticks cause the
 * player to move based on the location of the center of the circle.
 *
 * @author KyleFaith
 */
public class Player extends Pane
{

    private final double radius = 30;
    private double x = Game.WIDTH / 2, y = Game.HEIGHT / 2 - radius;
    private double speedX = 10, speedY = 10;
    private Circle circle = new Circle(x, y, radius);
    private Timeline animation;

    /**
     * The method is used to create a player in the shape of a circle, add the
     * player to a pane, and create an animation for said player.
     *
     * @author Kyle Faith
     */
    public Player()
    {
        circle.setFill(Color.GREEN);
        circle.setStroke(Color.BLACK);
        getChildren().add(circle);

        animation = new Timeline(
                new KeyFrame(Duration.millis(.1), e -> movePlayer()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    /**
     * The method is used to resume play when the game is paused.
     *
     * @author Kyle Faith
     */
    public void play()
    {
        animation.play();
    }

    /**
     * The method is used to pause the game, and can be started again by using
     * start method.
     *
     * @author Kyle Faith
     */
    public void pause()
    {
        animation.pause();
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
        y -= speedY;
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
        y += speedY;
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
        x -= speedX;
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
        x += speedX;
    }

    /**
     * The method is used to set the private variable x to a different value.
     *
     * @param x is a coordinate of player
     *
     * @author Kyle Faith
     */
    public void setX( double x )
    {
        this.x = x;
    }

    /**
     * The method is used to set the private variable y to a different value.
     *
     * @param y is a coordinate of player
     *
     * @author Kyle Faith
     */
    public void setY( double y )
    {
        this.y = y;
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
        return x;
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
        return y;
    }

    /**
     * The method sets the speed of the player based on the variable x.
     *
     * @param x user inputted variable which determines the x-coordinate speed.
     *
     * @author Kyle Faith
     */
    public void setSpeedX( int x )
    {
        this.speedX = x;
    }

    /**
     * The method sets the speed of the player based on the variable y.
     *
     * @param y user inputted variable which determines the y-coordinate speed.
     *
     * @author Kyle Faith
     */
    public void setSpeedY( int y )
    {
        this.speedY = y;
    }

    /**
     * The method creates a rectangular boundary around the player so that it
     * can interact with other objects based distance.
     *
     * @return Creates an invisible rectangular boundary around the Player.
     *
     * @author Kyle Faith
     */
    public Rectangle bounds()
    {
        return (new Rectangle(x, y, 59, 59));
    }

    /**
     * Sets the players position to the center of the screen and full heals the
     * player.
     *
     * @author Kyle Faith
     */
    public void Die()
    {
        setX(Game.WIDTH / 2 - radius);
        setY(Game.HEIGHT / 2 - radius);
        PlayerHealthBar.setHealth(300);
    }

    /**
     * Spawns the player in the center of the screen and sets the health to
     * full.
     *
     * @author Kyle Faith
     */
    public void Spawn()
    {
        setX(Game.WIDTH / 2 - radius);
        setY(Game.HEIGHT / 2 - radius);
        PlayerHealthBar.setHealth(300);
    }

    /**
     * The method is used to move the players coordinates, and is the main
     * movement command. The movePlayer method is looped through every time the
     * animation takes place(every .1 milliseconds), to see if there is a change
     * in position.
     *
     * @author Kyle Faith
     */
    protected void movePlayer()
    {
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
}
