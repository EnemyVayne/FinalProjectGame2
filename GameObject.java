/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author kylef
 */
public abstract class GameObject
{
   protected int x,y;
   protected ID id;
   protected int speedX,speedY;

   
   //When spawning a game object, you want the location it spawns in, and the 
   //id of the object, to know if enemy,friend,coin, etc.
   public GameObject(int x,int y,ID id)
   {
      this.x = x;
      this.y = y;
      this.id = id;
   }
           
      public void setX(int x)
   {
      this.x = x;
   }
   public void setY(int y)
   {
      this.y = y;
   }
      public void setID(ID id)
   {
      this.id = id;
   }
      public void setSpeedX(int speedX)
   {
      this.speedX = speedX;
   }
      public void setSpeedY(int speedY)
   {
      this.speedY = speedY;
   }
       
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public ID getID()
   {
      return id;
   }
   public int getSpeedX()
   {
      return speedX;
   }
   public int getSpeedY()
   {
      return speedY;
   }

   
   
   
}