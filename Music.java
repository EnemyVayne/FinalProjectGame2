/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**

 @author wills
 */
import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class Music
{
   public static void main(String[] args)
{
    JFrame frame = new JFrame();
    frame.setSize(200,200);
    frame.setLocationRelativeTo(null);
    JButton button = new JButton("Click me");
    frame.add(button);
    button.addActionListener(new AL());
    frame.setVisible(true);
}
    public static class AL implements ActionListener{
        public final void actionPerformed(ActionEvent e){
            music();
    }
}

    public static void music() 
    {       
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("C:\\Music1.wmv");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
}
