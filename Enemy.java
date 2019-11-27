package Game;

import javafx.animation.KeyFrame;
import javafx.scene.layout.Pane;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author kylef
 */
public class Enemy extends Pane
{

   private double xPos = Math.random() * 1262.62, yPos = Math.random() * 737.37;
   private double xWidth = 50, yHeight = 50 ;
   private Rectangle rectangle = new Rectangle(xPos, yPos, xWidth, yHeight);
   private double speedX = 7;
   private double speedY = 7;
   private Timeline animation;

   public Enemy()
   {
      rectangle.setFill(Color.RED);
      rectangle.setStroke(Color.BLACK);
      getChildren().add(rectangle);

      animation = new Timeline(
              new KeyFrame(Duration.millis(.1), e -> moveEnemy()));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play();
   }

   /**
    * The method is used to resume play when the game is paused.
    */
   public void play()
   {
      animation.play();
   }

   /**
    * The method is used to pause the game, and can be started again by using
    * start method.
    */
   public void pause()
   {
      animation.pause();
   }
   
      /**
    * Used to bind the rate of application.
    * 
    * @return Binding property value for rate.
    */
   public DoubleProperty rateProperty()
   {
      return animation.rateProperty();
   }
   
      /**
    * The method is used to move the player north. The method works by taking a 
    * specified speed and adding it to the coordinate of the player so it moves
    * by the speed every tick of the animation.
    */
   public void moveUp()
   {
      yPos -= speedY;
   }
   
   /**
    * The method is used to move the player south. The method works by taking a 
    * specified speed and adding it to the coordinate of the player so it moves
    * by the speed every tick of the animation.
    */
   public void moveDown()
   {
      yPos += speedY;
   }

    /**
    * The method is used to move the player west. The method works by taking a 
    * specified speed and adding it to the coordinate of the player so it moves
    * by the speed every tick of the animation.
    */
   public void moveLeft()
   {
      xPos -= speedX;
   }

    /**
    * The method is used to move the player east. The method works by taking a 
    * specified speed and adding it to the coordinate of the player so it moves
    * by the speed every tick of the animation.
    */
   public void moveRight()
   {
      xPos += speedX;
   }
   
      /**
    * The method grabs the private variable x to be able to use within different
    * classes.
    * @return Returns the variable found in Player class.
    */
   public double getX()
   {
      return xPos;
   }

   /**
    * The method grabs the private variable y to be able to use within different
    * classes.
    * @return Returns the variable found in Player class.
    */
   public double getY()
   {
      return yPos;
   }
   

   public double getRightBoundryX()
   {
      return xPos + (xWidth/2);
   }
   
   public double getLeftBoundryX()
   {
      return xPos - (xWidth/2);
   }

   public double getTopBoundryY()
   {
      return yPos + (yHeight/2);
   }
   
   public double getBottomBoundryY()
   {
      return yPos - (yHeight/2);
   }

   
   
      protected void moveEnemy()
   {
      rectangle.setX(xPos);
      rectangle.setY(yPos);
   }
}

