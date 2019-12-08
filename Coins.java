package Game;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 @author William Sarabia
 */
public class Coins extends Pane
{
   private static int coins = 0;
   private Label coinLabel;
   
   public Label displayCoins(int coins)
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
   
   public void Calc()
   {
       Coins.setCoins(( int ) (Math.random() * ((10 - 1) + 1)) + 1);
   }
   
   
}