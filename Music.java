/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 This class reads in the music file provided by the main and checks if it exists
 in the file. If it does then it plays the song forever until the program is
 closed

 @author William Sarabia
 */
public class Music
{

   void playMusic( String musicPath )
   {
      try
      {
         File mFile = new File( musicPath );
         if ( mFile.exists() )
         {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream( mFile );
            Clip clip = AudioSystem.getClip();
            clip.open( audioInput );
            FloatControl gainControl = ( FloatControl ) clip.getControl( FloatControl.Type.MASTER_GAIN );
            gainControl.setValue( -10.0f ); //reduces the volume by 10 decibels
            clip.start();
            clip.loop( Clip.LOOP_CONTINUOUSLY );

         } else
         {
            System.out.println( "Music File not found" ); // will be thrown if music file is not in the file
         }
      } catch ( Exception ex )
      {
         ex.printStackTrace();
      }
   }
}
