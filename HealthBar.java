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
public class HealthBar extends Pane
{

   private double xPos = Game.WIDTH/2-150, yPos = 10;
   private double xWidth = 300, yHeight = 25 ;
   private Rectangle rectangle = new Rectangle(xPos, yPos, xWidth, yHeight);
   private double speedX = 50;
   private Timeline animation1;

   public HealthBar()
   {
      rectangle.setFill(Color.GREEN);
      rectangle.setStroke(Color.BLACK);
      getChildren().add(rectangle);

      animation1 = new Timeline(
              new KeyFrame(Duration.millis(.1), e -> LowerHealth()));
      animation1.setCycleCount(Timeline.INDEFINITE);
      animation1.play();
   }

   /**
    * The method is used to resume play when the game is paused.
    */
   public void play()
   {
      animation1.play();
   }

   /**
    * The method is used to pause the game, and can be started again by using
    * start method.
    */
   public void pause()
   {
      animation1.pause();
   }

   
   /**
    * Used to bind the rate of application.
    * 
    * @return Binding property value for rate.
    */
   public DoubleProperty rateProperty()
   {
      return animation1.rateProperty();
   }
   
   
      protected void LowerHealth()
   {

       this.xWidth -= speedX;
   }
}
