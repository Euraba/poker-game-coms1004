import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import java.awt.*;
class Main {

    public static ArrayList<JButton> change = new ArrayList<>() ;
    public static JFrame frame = new JFrame("mor aci");
    public static void main(String args[]) {

        //Creating the Frame
        /*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(550, 300));
        frame.getContentPane().setBackground( Color.blue );
        frame.pack();

        /*JButton b1 = new JButton();
        b1.setBounds(100, 100, 60, 100);
        b1.setForeground(Color.red);
        b1.setBackground(Color.white);
        b1.setText("<html><aling = left>"+"K♥    "+"<br>"+"<br>"+"<br>"+"<br>" + "<right>"+"    K♥"+"</right></html>");
        b1.setFont(new Font("Arial", Font.PLAIN, 10));
        frame.add(b1) ;*/

        ArrayList<JButton> butoane = new ArrayList<>() ;
        Deck deck = new Deck() ;
        int leftPosition = 80 ;
        int i = 0 ;
        deck.shuffle();
        while (deck.howManyCardLeft() > 0 && i ++ < 5) {
            Card card = deck.deal() ;
            int finalLeftPosition = leftPosition;
            JButton digitalCard = new JButton() {{
                setBounds(finalLeftPosition, 80, 60, 100);
                setBackground(Color.white);
                setText("<html><aling = left>" + card.getName() +"    "+"<br>"+"<br>"+"<br>"+"<br>" + "<right>"+"    " + card.getName() +"</right></html>");
                setFont(new Font("Arial", Font.PLAIN, 15));
                if (card.getName().charAt(1) == '♥' || card.getName().charAt(1) == '♦') {
                    setForeground(Color.red);
                } else {
                    setForeground(Color.black);
                }
            }};

            digitalCard.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    change.add(digitalCard) ;
                }

            });

            frame.add(digitalCard) ;
            butoane.add(digitalCard);
            leftPosition += 70 ;
        }


        /*JButton b1 = new JButton();
        JButton b2 = new JButton("middle button");
        JButton b3 = new JButton();

        b1.setBounds(100, 100, 50, 50);
        b2.setBounds(200, 200, 50, 50);
        b1.setBackground(Color.red);

        frame.getContentPane().add(b1) ;
        frame.getContentPane().add(b2) ;
        frame.getContentPane().add(b3) ;

        frame.setVisible(true);*/

        System.out.println(Integer.compare(4, 1 ));
    }
}