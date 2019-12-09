package Game;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*This class holds all of the freamwork to set up the coin counter that is 
   displayed in the game and will update the counter when the eney dies
   and drops more coins
*/

/**
 @author William Sarabia
 */
public class Coins extends Pane
{
   private static int coins = 0;
   private Label coinLabel;
   
   public Label displayCoins(int coins)// Creates the label for the coin counter
   {
      coinLabel = new Label ("Coins: " + coins);
      coinLabel.setFont(Font.font("", FontWeight.BOLD, 25));
      coinLabel.setTextFill(Color.WHITE);
      
      return coinLabel;
   }
  
   public int getCoins()
   {
      return Coins.coins;
   }
   
   
   public static void setCoins(int coins)
   {
      Coins.coins += coins;
   }
   
   public void Calc() // generates a random amount of coins the enemy will drop
   {
       Coins.setCoins(( int ) (Math.random() * ((10 - 1) + 1)) + 1);
   }
   
   
}