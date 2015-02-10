package pelituotanto;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author s1200481
 */
public class Interface extends JFrame {

    private void init(){
        setTitle("Dimensions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        
        //GamePanel takes the ammount of squares width, height and square size
        add(new GamePanel(11,11,50));
        
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Interface iface = new Interface();
        iface.init();
    }
}
