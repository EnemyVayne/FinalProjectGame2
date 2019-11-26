/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**

 @author wills
 */
public class MusicPlayer
{
   public static void main (String[] args )
   {
      String Filepath = "Test1.wav";
      
      Music musicObj = new Music();
      musicObj.playMusic( Filepath );
      
      //Music_2 musicObj2 = new Music_2();
      //musicObj2.playMusic( Filepath );
   }
}
