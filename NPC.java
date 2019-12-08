package Game;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * maybe wont have time for insertion sort on final, study 5.
 * @author kylef
 */
public class NPC extends Pane
{

   private double xPos = Math.random() * 1262.62, yPos = Math.random() * 725;
   private double xWidth = 50, yHeight = 50;
   private Rectangle npc = new Rectangle(xPos, yPos, xWidth, yHeight);
   private double speedX = 1;
   private double speedY = 1;

 

   public NPC()
   {
      npc.setFill(Color.GRAY);
      npc.setStroke(Color.BLACK);
      getChildren().add(npc);
      


   }
   public NPC(int spawnX, int spawnY)  //to choose where the enemy spawns
   {
      npc.setFill(Color.RED);
      npc.setStroke(Color.BLACK);
      getChildren().add(npc);
      
      xPos = spawnX;
      yPos = spawnY;

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
   

   
   public void Spawn()
   {
       xPos = Math.random() * 1240;
       yPos = Math.random() * 710;
   }
   


}
