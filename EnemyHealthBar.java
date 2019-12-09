package Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The class creates a health bar for the enemy, and allows for changing the 
 * amount of health the enemy has, the speed at which the health decreases, 
 * and allows for specific methods to control all of this. 
 * 
 * @author Kyle Faith
 */
public class EnemyHealthBar extends Pane
{
   
   private double xPos = Game.WIDTH / 2 - xWidth/2, yPos = 725;
   private static double xWidth = (int)(Math.random() * ((400 - 100) + 1)) + 100, yHeight = 25;
   private Rectangle rectangleRed = new Rectangle(xPos, yPos, xWidth, yHeight);
   private Rectangle rectangleBlack = new Rectangle(xPos, yPos, xWidth, yHeight);

   private double speedX = 1;
   private Timeline animation;
   
   /**
    * The method creates a health bar for the enemy, allows for the enemy 
    * health bar to be filled, and creates a time line for the enemies health 
    * to tick down.
    * 
    * @author Kyle Faith
    */
   public EnemyHealthBar()
   {
      rectangleRed.setFill(Color.RED);
      rectangleRed.setStroke(Color.BLACK);
      rectangleBlack.setFill(Color.BLACK);
      rectangleBlack.setStroke(Color.BLACK);
      getChildren().addAll(rectangleBlack, rectangleRed);

      animation = new Timeline(
              new KeyFrame(Duration.millis(30), e -> HealthBarMovement()));
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
    * The method reduces the enemies health bar by a consistent rate, which is 
    * dictated by the variable speedX, as long as the health bar doesn't hit 
    * 0 or below.
    * 
    * @author Kyle Faith
    */
   public void LowerHealth()
   {
      if ( xWidth > 0 )
      {
         xWidth -= speedX;
      }
   }
   
   /**
    * The method allows the user to grab the xWidth variable from the class. 
    * The xWidth variable is used for the size of the health bar, so therefore 
    * is the amount of health the enemy has.
    * 
    * @return xWidth The size of the health bar.
    * 
    * @author Kyle Faith
    */
   public double getHealth()
   {
       return xWidth;
   }
   
   /**
    * The method allows the user to set the amount of health the enemy has.
    * 
    * @param Health The amount of health.
    * 
    * @author Kyle Faith
    */
   public static void setHealth(double Health)
   {
       xWidth = Health;
   }
   
   /**
    * The method allows the user to set the amount of health the enemy has.
    * 
    * @param Health The amount of health.
    * 
    * @author Kyle Faith
    */
   public void setSpeedX(double x)
   {
       this.speedX = x;
   }

   /**
    * The method creates the enemy health bar, with a random amount of health 
    * between 100 to 400 health, and sets the position of the health bar.
    * 
    * @author Kyle Faith
    */
   public void Spawn()
   {
       xWidth = (int)(Math.random() * ((400 - 100) + 1)) + 100;
       rectangleBlack.setWidth(xWidth);
rectangleRed.setX(Game.WIDTH / 2 - xWidth/2);
rectangleBlack.setX(Game.WIDTH / 2 - xWidth/2);
   }
   
   /**
    * The method is used by the animation to allow for the health bar to 
    * increase or decrease based on the variable xWidth.
    * 
    * @author Kyle Faith
    */
   protected void HealthBarMovement()
   {

      rectangleRed.setWidth(xWidth);

   }
}
