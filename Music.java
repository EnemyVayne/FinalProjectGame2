/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**

 @author wills
 */

public class Music
{
  void playMusic (String musicPath)
  {
     try
     {
        File mFile = new File(musicPath);
        if(mFile.exists())
        {
           AudioInputStream audioInput = AudioSystem.getAudioInputStream( mFile );
           Clip clip = AudioSystem.getClip();
           clip.open(audioInput);
           clip.start();   
           clip.loop(Clip.LOOP_CONTINUOUSLY);
           
           JOptionPane.showMessageDialog(null, "Press ok to stop" );
        } else
        {
           System.out.println("File not found");
        }
     }
     catch(Exception ex)
     {
        ex.printStackTrace();
     }
  }
}
