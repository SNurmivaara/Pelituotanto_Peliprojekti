/* 
 * Copyright (C) 2015 Sami Nurmivaara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package wildwestshootout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *asd
 * @author s1200478
 */

public class MainMenu extends JFrame {
    
    private MainMenu ikkuna;
    private JPanel Pohja = new JPanel(new GridLayout(4,6,5,5));
    
    private JButton btAloita = new JButton("Aloita peli");
    private JButton btAsetukset = new JButton("Asetukset");
    private JButton btTekijat = new JButton("Tekijät");
    private JButton btLopeta = new JButton("Lopeta");
    
    public MainMenu(){
        this.setTitle("Wild West Shootout!");
        this.setSize(260, 300);
        this.setLocation(550, 250);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asettelu();
        this.setVisible(true);
    }
    
    private void asettelu(){
        Pohja.add(btAloita);
        Pohja.add(btAsetukset);
        Pohja.add(btTekijat);
        Pohja.add(btLopeta);
        
        //Napin tekstin väri
        btAloita.setForeground(Color.BLACK);
        btAsetukset.setForeground(Color.BLACK);
        btTekijat.setForeground(Color.BLACK);
        btLopeta.setForeground(Color.BLACK);
        
        //Napin taustan väri
        btAloita.setBackground(Color.GRAY);
        btAsetukset.setBackground(Color.GRAY);
        btTekijat.setBackground(Color.GRAY);
        btLopeta.setBackground(Color.GRAY);
        
        Pohja.setBackground(Color.BLACK);
        this.add(Pohja);
        
        btAloita.addActionListener(new lAloita());
        btAsetukset.addActionListener(new lAsetukset());
        btTekijat.addActionListener(new lTekijat());
        btLopeta.addActionListener(new lLopeta());
    }
    //toinen muutos
    //muutos2
    class lAloita implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            String[] args = {};
            Game.main(args);
        }
    }
    
    class lAsetukset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            JOptionPane.showMessageDialog(rootPane, "Ei vielä käytössä", "Ilmoitus", WIDTH);
        }
    }
    
    class  lTekijat implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            JOptionPane.showMessageDialog(rootPane, "Sami Nurmivaara\nJesse Vuori\nToni Liivola\nVille Vihervaara\nVille Henriksson", "Tekijät", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    class lLopeta implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            System.exit(0);
        }
    }
    
    public static void main(String[] args){
        MainMenu ikkuna = new MainMenu();
    }
}
