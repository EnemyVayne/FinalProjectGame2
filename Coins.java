/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
   private int coins = 0;
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
      return this.coins;
   }
   
   public void setCoins(int coins)
   {
      this.coins =+ coins;
   }
   
   
   
}
