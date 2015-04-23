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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author s1200478
 */
public class MainMenu extends JFrame {

    private MainMenu ikkuna;
    private JPanel Napit = new JPanel(new GridLayout(4, 6, 500, 5));
    private JPanel Pohja = new MainMenuBG();
    private JPanel TLabelit = new JPanel(new GridLayout(5, 9, 5, 10));
    private JPanel TPohja = new JPanel(new BorderLayout());
    private JPanel Nappula = new JPanel(new FlowLayout());

    private JButton btAloita = new OmaNappi("Aloita peli");
    private JButton btAsetukset = new OmaNappi("Asetukset");
    private JButton btTekijat = new OmaNappi("Tekijät");
    private JButton btLopeta = new OmaNappi("Lopeta");
    private JButton btTakaisin = new OmaNappi("Takaisin");

    private JLabel lbTekijat = new JLabel("Tekijät:");
    private JLabel lbTekijatS = new JLabel("Sami Nurmivaara");
    private JLabel lbTekijatJ = new JLabel("Jesse Vuori");
    private JLabel lbTekijatT = new JLabel("Toni Liivola");
    //JLabel testi = new JLabel(new ImageIcon("Menu_background.png"));

    //Konstruktori
    public MainMenu() {
        this.setTitle("Wild West Shootout!");
        this.setSize(660, 500);
        this.setLocation(400, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TLabelit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setVisible(true);
        Pohja.setLayout(new FlowLayout(FlowLayout.CENTER, 600, 150));
        this.setResizable(false);
        asettelu();

    }

    private void asettelu() {

        Napit.add(btAloita);
        Napit.add(btAsetukset);
        Napit.add(btTekijat);
        Napit.add(btLopeta);

        //Nappien tekstin väri
        btAloita.setForeground(Color.BLACK);
        btAsetukset.setForeground(Color.BLACK);
        btTekijat.setForeground(Color.BLACK);
        btLopeta.setForeground(Color.BLACK);

        //Nappien taustaväri
        btAloita.setBackground(Color.LIGHT_GRAY);
        btAsetukset.setBackground(Color.LIGHT_GRAY);
        btTekijat.setBackground(Color.LIGHT_GRAY);
        btLopeta.setBackground(Color.LIGHT_GRAY);

        Font fontti = new Font("Liberation Mono", Font.BOLD, 20);
        btAloita.setFont(fontti);
        btAsetukset.setFont(fontti);
        btTekijat.setFont(fontti);
        btLopeta.setFont(fontti);

        //MenunTausta.setOpaque(false);
        //Pohja.add(testi);
        Pohja.add(Napit);
        Napit.setLocation(0, 0);
        Napit.setBackground(new Color(0, 0, 0, 0));
        Pohja.setBackground(Color.WHITE);
        this.add(Pohja);

        btAloita.addActionListener(new lAloita());
        btAsetukset.addActionListener(new lAsetukset());
        btTekijat.addActionListener(new lTekijat());
        btLopeta.addActionListener(new lLopeta());
    }

    class lAloita implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Game game = new Game();
            game.frame.setResizable(false);
            game.frame.setTitle(Game.title);
            game.frame.add(game);
            game.frame.pack();
            game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.frame.setLocationRelativeTo(null);
            game.frame.setVisible(true);

            game.start();
        }
    }

    class lAsetukset implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(rootPane, "Ei vielä käytössä", "Ilmoitus", WIDTH);
        }
    }

    class lTekijat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JFrame jframe = new JFrame("");
            jframe.setResizable(false);
            jframe.setLocation(645, 230);
            jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jframe.setVisible(true);
            TLabelit.add(lbTekijat);
            TLabelit.add(lbTekijatS);
            TLabelit.add(lbTekijatJ);
            TLabelit.add(lbTekijatT);

            lbTekijat.setFont(new Font("Liberation Mono", Font.BOLD, 16));
            lbTekijatS.setFont(new Font("Liberation Mono", Font.BOLD, 16));
            lbTekijatJ.setFont(new Font("Liberation Mono", Font.BOLD, 16));
            lbTekijatT.setFont(new Font("Liberation Mono", Font.BOLD, 16));

            Font fontti = new Font("Liberation Mono", Font.BOLD, 20);
            btTakaisin.setFont(fontti);
            btTakaisin.setBackground(Color.LIGHT_GRAY);

            Nappula.add(btTakaisin);
            TLabelit.setBackground(Color.LIGHT_GRAY);
            TPohja.add(TLabelit, BorderLayout.CENTER);
            TPohja.add(Nappula, BorderLayout.PAGE_END);

            Nappula.setBackground(Color.LIGHT_GRAY);
            TPohja.setBackground(Color.GRAY);
            jframe.add(TPohja);
            jframe.pack();
            btTakaisin.addActionListener(new lTakaisin(jframe));
        }

    }

    class lTakaisin implements ActionListener {

        private JFrame ikkuna;

        public lTakaisin(JFrame ikkuna) {
            this.ikkuna = ikkuna;
        }

        @Override
        public void actionPerformed(ActionEvent a) {
            ikkuna.dispose();
        }
    }

    class lLopeta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        MainMenu ikkuna = new MainMenu();
    }
}
