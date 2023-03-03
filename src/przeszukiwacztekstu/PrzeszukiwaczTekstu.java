/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package przeszukiwacztekstu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrzeszukiwaczTekstu extends JFrame{

    public PrzeszukiwaczTekstu()
    {
        initComponents();
    }
    public void initComponents()
    {
        this.setTitle("Przeszukiwacz Tekstu");
        this.setBounds(300, 320, 400, 200);
        
        panelSzukania.add(znajdz);
        panelSzukania.add(szukany);
        panelSzukania.add(etykietaI);
        panelSzukania.add(zamien);
        panelSzukania.add(etykietaNa);
        panelSzukania.add(nowyTekst);
        
        //obszarTekstowy.selectAll(); - zaznacza caly tekst
        //obszarTekstowy.select(0, 4) - zaznacza wybrane od do 
        //obszarTekstowy.replaceSelection("lala"); - zamieni zaznaczony aktualnie obszar
        //obszarTekstowy.replaceRange("lala", 0, 6); - 2 poprzednie linijki w 1
        //obszarTekstowy.insert("jakis tam", 2); - dodaj w wybrane miejsce
        //obszarTekstowy.append(" iks de"); - dodaj na koniec
        //obszarTekstowy.select(obszarTekstowy.getText().indexOf("Test"), obszarTekstowy.getText().indexOf("Test")+"test".length());
        
        znajdz.addActionListener(new znajdowanieHandler());
        zamien.addActionListener(new zamienianieHandler());
        
        this.getContentPane().add(obszarPrzewijania, BorderLayout.NORTH);
        this.getContentPane().add(panelSzukania, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(3);
    }
    
    private class znajdowanieHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            poczatekSzukanego = obszarTekstowy.getText().indexOf(szukany.getText(), poczatekSzukanego+szukany.getText().length());
            
            System.out.println(poczatekSzukanego);
            
            if(poczatekSzukanego == -1)
            {
                poczatekSzukanego = obszarTekstowy.getText().indexOf(szukany.getText());
            }
            
            if(poczatekSzukanego >= 0 && szukany.getText().length()>0)
            {
                obszarTekstowy.requestFocus();
                obszarTekstowy.select(poczatekSzukanego, poczatekSzukanego+szukany.getText().length());
            }
        }
        private int poczatekSzukanego = 0;
    }
    
    private class zamienianieHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd())
            {
                zamienTekst();
            }
            else
            {
                znajdz.doClick();
                if(obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd())
                    zamienTekst();
            }
            
        }
        private void zamienTekst()
        {
            obszarTekstowy.requestFocus();
            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy chcesz zamienić \'"+szukany.getText()+"\' na \'"+nowyTekst.getText()+"\'", "Uwaga nastąpi zmiana!", JOptionPane.YES_NO_OPTION);
            if(opcja == 0)
                obszarTekstowy.replaceRange(nowyTekst.getText(), obszarTekstowy.getSelectionStart(), obszarTekstowy.getSelectionEnd());
        }
    }
        
    private JTextArea obszarTekstowy = new JTextArea("Tekst testowy do sprawdzenia dzialania wyszukiwań i testowania umiejętności",7, 10);
    private JScrollPane obszarPrzewijania = new JScrollPane(obszarTekstowy);
    
    private JPanel panelSzukania = new JPanel();
    private JButton znajdz = new JButton("Znajdź");
    private JTextField szukany = new JTextField(6);
    private JLabel etykietaI = new JLabel("i");
    private JButton zamien = new JButton("Zamień");
    private JLabel etykietaNa = new JLabel("na");
    private JTextField nowyTekst = new JTextField(6);
    
    public static void main(String[] args) {
        new PrzeszukiwaczTekstu().setVisible(true);
    }
    
}
