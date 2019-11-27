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
 * maybe wont have time for insertion sort on final, study 5.
 * @author kylef
 */
public class Enemy extends Pane
{

   private static int randomInt;
   private double xPos = Math.random() * 1262.62, yPos = Math.random() * 737.37;
   private double xWidth = 50, yHeight = 50;
   private Rectangle rectangle = new Rectangle(xPos, yPos, xWidth, yHeight);
   private double speedX = 1;
   private double speedY = 1;
   private Timeline animation;
   private Timeline random;

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
    * The method is used to resume play when the game is paused.
    */
   public void playAnimation()
   {
      animation.play();
   }

   /**
    * The method is used to pause the game, and can be started again by using
    * start method.
    */
   public void pauseAnimation()
   {
      animation.pause();
   }
   
   public void pauseEnemy()
   {
      animation.pause();
   }
   
   public void playEnemy()
   {
      animation.play();
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
    *
    * @return Returns the variable found in Player class.
    */
   public double getX()
   {
      return xPos;
   }

   /**
    * The method grabs the private variable y to be able to use within different
    * classes.
    *
    * @return Returns the variable found in Player class.
    */
   public double getY()
   {
      return yPos;
   }

   public Rectangle bounds()
   {
      return (new Rectangle(xPos+xWidth/2, yPos+yHeight/2, xWidth, yHeight));
   }

   protected void moveEnemy()
   {
      switch ( randomInt )
      {

         case 1:
            if(yPos <= 0)
            {
               moveDown();
            }
//            else
//            {
            moveUp();
//            }
            break;
         case 2:
            if(yPos >= 710)
            {
               moveUp();
            }
//            else
//            {
               moveDown();
//            }
            break;
         case 3:
            if(xPos <= 0)
            {
               moveRight();
            }
//            else
//            {
               moveLeft();
//            }
            break;
         case 4:
            if(xPos >= 1240)
            {
               moveLeft();
            }
//            else
//            {
               moveRight();  
//            }
            break;
      }

      rectangle.setX(xPos);
      rectangle.setY(yPos);
   }
}
