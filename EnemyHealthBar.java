package Game;

import javafx.animation.KeyFrame;
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
public class EnemyHealthBar extends Pane
{
   
   private double xPos = Game.WIDTH / 2 - xWidth/2, yPos = 725;
   private static double xWidth = (int)(Math.random() * ((400 - 100) + 1)) + 100, yHeight = 25;
   private Rectangle rectangleRed = new Rectangle(xPos, yPos, xWidth, yHeight);
   private Rectangle rectangleBlack = new Rectangle(xPos, yPos, xWidth, yHeight);

   private double speedX = 1;
   private Timeline animation;
   
   Enemy enemy;

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

   public void LowerHealth()
   {
      if ( xWidth > 0 )
      {
         xWidth -= speedX;
      }
   }
   
   public double getHealth()
   {
       return xWidth;
   }
   
   public static void setHealth(double Health)
   {
       EnemyHealthBar.xWidth = Health;
   }

   public void RaiseHealth()
   {
      if ( xWidth < 300  && xWidth != 0)
      {
         xWidth += speedX;
      }
   }

   protected void HealthBarMovement()
   {

      rectangleRed.setWidth(xWidth);

   }
}
