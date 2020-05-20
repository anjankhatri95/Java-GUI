import javax.swing.JFrame; //framing
import javax.swing.JLabel;//sticking the label
import javax.swing.ImageIcon;//placing the image in label
import javax.swing.JTextField;//text field
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.File;//allow to creat file
import java.io.FileWriter;//writing content
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GasStation5 extends JFrame implements ActionListener{
  private JLabel costlabel,dollarlabel,washamountlabel,carwashlabel;
  private ImageIcon pumpIcon, carwashIcon;
  private JTextField dollarfield;
  private JPanel northpanel,northpanel1,centerpanel,southpanel;
  private JCheckBox carWashCheck;
  private JButton paybutton,printbutton,clearbutton;
  
  
  public GasStation5(){
    super("Gas Station Cashier");
    //label of pump and text field for amount to enter
         costlabel= new JLabel("");
         pumpIcon= new ImageIcon("gas.gif");
         costlabel= new JLabel(pumpIcon);
         dollarlabel=new JLabel("$");
         dollarfield = new JTextField(5);
        northpanel= new JPanel();
        northpanel1=new JPanel();
        northpanel.add(costlabel);
        northpanel.add(dollarlabel);
        northpanel.add(dollarfield);
        northpanel1.add(northpanel);
        add(northpanel1,BorderLayout.NORTH);
        
   //check box on car wash and carwash icon 
    carWashCheck = new JCheckBox();
    washamountlabel= new JLabel("$10.00");
    carwashlabel= new JLabel("");
    carwashIcon= new ImageIcon("carwash.gif");
    carwashlabel= new JLabel(carwashIcon);
    centerpanel= new JPanel();
    centerpanel.add(carWashCheck);
    centerpanel.add(washamountlabel);
    centerpanel.add(carwashlabel);
    add(centerpanel,BorderLayout.CENTER);
    
    //buttons for pay print and clear
              paybutton= new JButton("Pay");
              paybutton.addActionListener(this);
              
              printbutton= new JButton("Print Receipt");
              printbutton.addActionListener(this);
              
              clearbutton= new JButton("Clear");
              clearbutton.addActionListener(this);
              
              southpanel=new JPanel();
              southpanel.add(paybutton);
              southpanel.add(printbutton);
              southpanel.add(clearbutton);
              add(southpanel,BorderLayout.SOUTH);
    
   setSize(400,200); 
   setVisible(true);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent event){
    double amount=0.00;
    double carwashamount=0.00;
    double total=0.00;
 
if (event.getSource()==paybutton){
     // System.out.println("Pay button is clicked");
               String Amountstr= dollarfield.getText();
               String carwash="No";
       if(carWashCheck.isSelected()){
         carwash="Yes"; 
         carwashamount=10.00;
           }
     try{//try exception and condition for field to be empty
         if (dollarfield.getText().equals("")){
                 throw new AmountException();
           }//all these result are depends on the above exception
          amount= Double.parseDouble(Amountstr);
         if(amount<=0){
          throw new AmountException("Error: Your dollar amount is either a zero or negative number!!!");
         }
            amount= Double.parseDouble(Amountstr);
        total= amount+carwashamount;
            String output="Receipt \nGas Amount: $"+amount+"\nCar Wash: "+carwash+"\nTotal Fee:$"+total;
           File Gasfile= new File("Receipt.txt");
           FileWriter writer= new FileWriter(Gasfile);
              writer.write(output);
              writer.close();
            JOptionPane.showMessageDialog(null,output);   
          }//catching the display
      catch(AmountException e){
           JOptionPane.showMessageDialog(null,e.getMessage());
          }
      catch(IOException ioe){
       JOptionPane.showMessageDialog(null, ioe.getMessage()); 
      }
    
}else if(event.getSource()==printbutton){
     // System.out.println("print button is clicked"); 
    File gasfile= new File("Receipt.txt");
    try{
     Scanner read= new Scanner(gasfile);
     while(read.hasNext()){
      String line= read.nextLine();
      System.out.println(line);
     }
       read.close();
    }
    catch(FileNotFoundException fnfe){
      fnfe.printStackTrace();
    }
      
}else if(event.getSource()==clearbutton){
      //System.out.println("clear button is clicked"); 
       dollarfield.setText("");
       carWashCheck.setSelected(false);
    }
}
  public static void main(String [] args){
   GasStation5 app= new GasStation5(); 
  }
}