package Game;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * The Class NPC is used to create an NPC at a random position, or gives the 
 * option of creating the NPC at a specific position, depending on preference.
 * @author Kyle Faith
 */
public class NPC extends Pane
{

   private double xPos = Math.random() * 1262.62, yPos = Math.random() * 725;
   private double xWidth = 50, yHeight = 50;
   private Rectangle npc = new Rectangle(xPos, yPos, xWidth, yHeight);

 
   /**
    * The NPC default constructor sets the color of the NPC.
    * 
    * @author Kyle Faith
    */
   public NPC()
   {
      npc.setFill(Color.GRAY);
      npc.setStroke(Color.BLACK);
      getChildren().add(npc);
   }
   
   /**
    * The NPC parameterized constructor sets the color of the NPC, and allows 
    * for deciding the spawn location of the NPC.
    * @param spawnX The X coordinate of where the NPC should spawn.
    * @param spawnY The Y coordinate of where the NPC should spawn.
    * 
    * @author Kyle Faith
    */
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
    * 
    * @author Kyle Faith
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
    * 
    * @author Kyle Faith
    */
   public double getY()
   {
      return yPos;
   }
   
   /**
    * Creates the bounds for the NPC, so the Player can interact with the NPC
    * based on distance.
    * 
    * @return Creates an invisible rectangular boundary around the NPC.
    * 
    * @author Kyle Faith
    */
   public Rectangle bounds()
   {
      return (new Rectangle(xPos+xWidth/2, yPos+yHeight/2, xWidth, yHeight));
   }

   /**
    * Randomizes the spawn in location of the NPC as long as it is within the 
    * boundaries of the window
    * 
    * @author Kyle Faith
    */
   public void Spawn()
   {
       xPos = Math.random() * 1240;
       yPos = Math.random() * 710;
   }
   


}
