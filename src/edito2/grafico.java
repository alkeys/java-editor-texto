package edito2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class grafico extends JFrame {

    private String ruta_aux, texto_carga, texto_carga_activo;
    private JTextArea panel_tex = new JTextArea(25, 70);
    private JScrollPane scrol = new JScrollPane(panel_tex);
    private int tipo_letras, tamaño=12;

    public grafico() {
        setVisible(true);
        setAutoRequestFocus(false);
        Dimension pant = getToolkit().getScreenSize();
        setLocation(pant.width / 4, pant.height / 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 500);
        panel_principal xd = new panel_principal();
        add(xd);
      //  setResizable(false);
        //eventos de mouse
         mouse even=new mouse();
         addMouseListener(even);
        ///
        //eventos de ventana
        ventana eventos = new ventana();
        addWindowListener(eventos);
        ///

    }

    private class panel_principal extends JPanel {

        Image picture;

        public panel_principal() {

            panel_botones pant1 = new panel_botones();
            panel_tex.addCaretListener(new teclado());
            getContentPane().add(pant1, BorderLayout.NORTH);

            add(scrol, BorderLayout.CENTER);
            panel_tex.selectAll();
            panel_tex.setSelectedTextColor(Color.RED);
            try {
                picture = ImageIO.read(new File("src//img//fondo.jpg"));
            } catch (IOException e) {
            }

        }

        @Override
        public void paintComponent​(Graphics g) {
            super.paintComponent(g);
            g.drawImage(picture, 1, 1, 1600, 1600, this);
        }

    }

    private class panel_botones extends JPanel {

        public panel_botones() {
            JMenuBar menu = new JMenuBar();
            ////////////////////////////////////////////////////////////
            JMenu ob = new JMenu("File");
            JMenu tipo_letra = new JMenu("tipo de letra");
            JMenu tama = new JMenu("tamaño");
            JMenu color = new JMenu("color");
            menu.add(ob);
            menu.add(color);
            menu.add(tama);
            menu.add(tipo_letra);
            //////////////////////////////////////////////////////////////
            ob.add(new JMenuItem(new event("abrir", 0)));
            ob.add(new JMenuItem(new event("guardar", 1)));
            ob.add(new JMenuItem(new event("guardar como", 2)));
            ob.add(new JMenuItem(new event("cerrar", 3)));
            ////////////////////////////////////////////////////////////
            color.add(new JMenuItem(new event("verde", 4)));
            color.add(new JMenuItem(new event("rosa", 5)));
            color.add(new JMenuItem(new event("azul", 6)));
            color.add(new JMenuItem(new event("morado", 7)));
            color.add(new JMenuItem(new event("celeste", 8)));
            color.add(new JMenuItem(new event("rojo", 9)));
            color.add(new JMenuItem(new event("negro", 10)));
            ////////////////////////////////////////////////////////////tamaño de letra
            tama.add(new JMenuItem(new event("13", 11)));
            tama.add(new JMenuItem(new event("15", 12)));
            tama.add(new JMenuItem(new event("16", 13)));
            tama.add(new JMenuItem(new event("18", 14)));
            tama.add(new JMenuItem(new event("20", 15)));
            tama.add(new JMenuItem(new event("25", 16)));
            tama.add(new JMenuItem(new event("27", 17)));
            tama.add(new JMenuItem(new event("30", 18)));
            ////////////////////////////////////////////////////////////
            add(menu);
        }

        private class event extends AbstractAction {

            public event(String nombre, int tipo) {
                putValue(javax.swing.Action.NAME, nombre);
                putValue("tipo", tipo);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int tipo = Integer.parseInt(String.valueOf(getValue("tipo")));
                carga_datos c = new carga_datos();
                switch (tipo) {
                    ////////////////////////////////////////////////////////////////////////////
                    case 0 -> {
                        panel_tex.setText(c.abrir());
                        texto_carga = panel_tex.getText();
                        ruta_aux = c.getAux_ruta();
                    }
                    case 1 -> {
                        c.guardar(texto_carga_activo, ruta_aux);
                    }
                    case 2 -> {
                        c.guardad_como(texto_carga_activo);
                        ruta_aux = c.getAux_ruta();
                    }
                    case 3 -> {
                        ruta_aux = "";
                        panel_tex.setText("");
                        texto_carga_activo = "";
                    }
                    ///////////////////////////////////////////////////////////////////////////////
                    case 4 -> {
                        color(Color.GREEN);
                    }
                    case 5 -> {
                        color(Color.PINK);
                    }
                    case 6 -> {
                        color(Color.BLUE);
                    }
                    case 7 -> {
                        color(Color.MAGENTA);
                    }
                    case 8 -> {
                        color(Color.CYAN);
                    }
                    case 9 -> {
                        color(Color.red);
                    }
                    case 10 -> {
                        color(Color.DARK_GRAY);
                    }
                    //////////////////////////////////////////////////////////////////////////////////////
                    case 11 -> {
                        grande(new Font("", tipo_letras, 13));
                        tamaño = 13;
                    }
                    case 12 -> {
                        grande(new Font("", tipo_letras, 15));
                        tamaño = 15;
                    }
                    case 13 -> {
                        grande(new Font("", tipo_letras, 16));
                        tamaño = 16;
                    }
                    case 14 -> {
                        grande(new Font("", tipo_letras, 18));
                        tamaño = 18;
                    }
                    case 15 -> {
                        grande(new Font("", tipo_letras, 20));
                        tamaño = 20;
                    }
                    case 16 -> {
                        grande(new Font("", tipo_letras, 25));
                        tamaño = 25;
                    }
                    case 17 -> {
                        grande(new Font("", tipo_letras, 27));
                        tamaño = 27;
                    }
                    case 18 -> {
                        grande(new Font("", tipo_letras, 30));
                        tamaño = 30;
                    }
                    /////////////////////////////////////////////////////////////////////////////////////
                }
            }

        }

        public void color(Color g) {
            panel_tex.setForeground(g);
        }

        public void grande(Font tipo_letra) {
            panel_tex.setFont(tipo_letra);
        }

    }

    private class ventana implements WindowListener {
/*    */
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            carga_datos f = new carga_datos();
            int i = JOptionPane.showInternalConfirmDialog(null, "desea guardad");
            System.out.println(i);
            if (i == 0) {
                f.guardad_como(texto_carga_activo);
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {
         
        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

    }

    private class mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        System.out.println("1");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("2");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("3");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        //eventos de mouse se ocupo por el error de agrandamiento;
        panel_tex.setText(texto_carga);
        panel_tex.setFont(new Font("",tipo_letras, tamaño));
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private class teclado implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent e) {
            texto_carga_activo = panel_tex.getText();
        }

    }

}
