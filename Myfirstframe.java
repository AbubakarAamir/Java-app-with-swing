
import java.awt.*;

import javax.swing.*;// here we import the all swing classes


class Myfirstframe{
    public static void main(String[] args) {
        // here we created thye jframe object
        JFrame frame = new JFrame(); 
        frame.setVisible(true);// .setvisible aik method hota ha jframe ka use karte huye
    //1: apke liye aik app banai ga lekin iski default value false hoti ha lekin humko is function ki help se true krna hota ha
        
        
    //2: agar apko size zada krna ha toh uske liye yeh method hota ha     
       frame.setSize(700,500);// phele width set hogi then height
    
   //3: location set krne ka bhhi aik method hota ha
     frame.setLocation(100,50); 

 
  //4: bound ka bhi aik method ha jo setlocation aursetsize ka kaam aik sath he krdeta ha lekin ismein phele location set ho gi phir width and height
   frame.setBounds(100,100,1000,500);


 //5: if you want to set the title there is also a method 
   frame.setTitle("Hello abubakar");

//6: if you want to set the image first you create the image object and there is also a image method
  ImageIcon icon = new ImageIcon("Devoop3.jflf");
  frame.setIconImage(icon.getImage());

//7: jframe aik container ki tarah hota ha(yeh swing ka package nhi ha awt ka ha )

Container c = frame.getContentPane();
c.setBackground(Color.ORANGE);

//8: aik setresize ka bhi method hota ha yeh user ko window ka size kaam karne nhi dega uske liye false pass krna hoga 
frame.setResizable(false);
    }
}