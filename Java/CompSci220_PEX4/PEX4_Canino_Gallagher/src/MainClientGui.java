
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * Game_Gui.java
 *
 * Created on Apr 15, 2012, 11:48:11 AM
 */
/**
 *
 * @author C14Gallagher
 * @author C14Canino
 */
public class MainClientGui extends javax.swing.JFrame {

    int GameHeight = 0;
    int GameWidth = 0;
    Dimension d;
    DrawingPanel GamePanel;
    String shortPlayer = null;
    String cutPlayer = null;

    /**
     * Creates new form Game_Gui
     */
    public MainClientGui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sizeGroup = new javax.swing.ButtonGroup();
        cutGroup = new javax.swing.ButtonGroup();
        shortGroup = new javax.swing.ButtonGroup();
        MainPanel = new javax.swing.JPanel();
        SmallWindowSize = new javax.swing.JRadioButton();
        MediumWindowSize = new javax.swing.JRadioButton();
        LargeWindowSize = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        PlayerLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CutHuman = new javax.swing.JRadioButton();
        ShortHuman = new javax.swing.JRadioButton();
        CutComputer = new javax.swing.JRadioButton();
        ShortComputer = new javax.swing.JRadioButton();
        Starter = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitOption = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sizeGroup.add(SmallWindowSize);
        SmallWindowSize.setText("Small");
        SmallWindowSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmallWindowSizeActionPerformed(evt);
            }
        });

        sizeGroup.add(MediumWindowSize);
        MediumWindowSize.setText("Medium");
        MediumWindowSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MediumWindowSizeActionPerformed(evt);
            }
        });

        sizeGroup.add(LargeWindowSize);
        LargeWindowSize.setText("Large");
        LargeWindowSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LargeWindowSizeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel1.setText("Size of Window");

        PlayerLabel.setFont(new java.awt.Font("Times New Roman", 1, 14));
        PlayerLabel.setText("Player Types");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel2.setText("Cut");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel3.setText("Short");

        cutGroup.add(CutHuman);
        CutHuman.setText("Human");
        CutHuman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CutHumanActionPerformed(evt);
            }
        });

        shortGroup.add(ShortHuman);
        ShortHuman.setText("Human");
        ShortHuman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShortHumanActionPerformed(evt);
            }
        });

        cutGroup.add(CutComputer);
        CutComputer.setText("Computer");
        CutComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CutComputerActionPerformed(evt);
            }
        });

        shortGroup.add(ShortComputer);
        ShortComputer.setText("Computer");
        ShortComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShortComputerActionPerformed(evt);
            }
        });

        Starter.setText("Start Game");
        Starter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StarterActionPerformed(evt);
            }
        });

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(PlayerLabel)
                        .addContainerGap())
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                            .addComponent(LargeWindowSize)
                            .addContainerGap())
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(MediumWindowSize)
                                .addContainerGap())
                            .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(MainPanelLayout.createSequentialGroup()
                                    .addComponent(SmallWindowSize)
                                    .addContainerGap())
                                .addGroup(MainPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(168, 168, 168)))))))
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CutComputer)
                            .addComponent(CutHuman))
                        .addGap(67, 67, 67)))
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Starter)
                            .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ShortComputer)
                                .addComponent(ShortHuman)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Exit)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(SmallWindowSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MediumWindowSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LargeWindowSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PlayerLabel)
                .addGap(9, 9, 9)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CutHuman)
                    .addComponent(ShortHuman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CutComputer)
                    .addComponent(ShortComputer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Exit)
                    .addComponent(Starter)))
        );

        jMenu1.setText("File");

        exitOption.setText("Exit");
        exitOption.setToolTipText("");
        exitOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitOptionActionPerformed(evt);
            }
        });
        jMenu1.add(exitOption);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * CutHuman - receives an event and then sets the cut player to "human"
     *
     * @param evt
     */
private void CutHumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CutHumanActionPerformed
    cutPlayer = "human";

}//GEN-LAST:event_CutHumanActionPerformed
    /**
     * Starter - initializes the game
     *
     * @param evt
     */
private void StarterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StarterActionPerformed
    if (GameWidth == 0 && GameHeight == 0) {
        JOptionPane.showMessageDialog(null, "Error: The window size has not been specified.");
    }
    if (shortPlayer == null || cutPlayer == null) {
        JOptionPane.showMessageDialog(null, "Error: One or more players have not been specified \n please"
                + " specify the player type.");

    } else if (shortPlayer != null && cutPlayer != null && GameWidth != 0 && GameHeight != 0) {


        this.setVisible(false);

        Thread game = new gameThread();
        game.start();


    }
}//GEN-LAST:event_StarterActionPerformed
    /**
     * SmallWindowSize - sets the window width to 700 and height to 500
     *
     * @param evt
     */
private void SmallWindowSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmallWindowSizeActionPerformed
    GameWidth = 700;
    GameHeight = 500;

}//GEN-LAST:event_SmallWindowSizeActionPerformed
    /**
     * MediumWindowSize - sets the window width to 800 and height to 600
     *
     * @param evt
     */
private void MediumWindowSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediumWindowSizeActionPerformed
    GameWidth = 800;
    GameHeight = 600;


}//GEN-LAST:event_MediumWindowSizeActionPerformed
    /**
     * LargeWindowSize - sets the window width to 900 and height to 700
     *
     * @param evt
     */
private void LargeWindowSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LargeWindowSizeActionPerformed
    GameWidth = 900;
    GameHeight = 700;

}//GEN-LAST:event_LargeWindowSizeActionPerformed
    /**
     * Exit - allows the user to exit the main panel via an exit button on the
     * main panel.
     *
     * @param evt
     */
private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
    pullThePlug();
}//GEN-LAST:event_ExitActionPerformed
    /**
     * exitOption - allows the user to exit from the exit option in the file
     * menu
     *
     * @param evt
     */
private void exitOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitOptionActionPerformed
    pullThePlug();
}//GEN-LAST:event_exitOptionActionPerformed
    /**
     * CutComputer - receives an event and then sets the cut player to
     * "computer"
     *
     * @param evt
     */
private void CutComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CutComputerActionPerformed
    cutPlayer = "computer";
}//GEN-LAST:event_CutComputerActionPerformed
    /**
     * ShortHuman - receives an event and then sets the short player to "human"
     *
     * @param evt
     */
private void ShortHumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShortHumanActionPerformed
    shortPlayer = "human";

}//GEN-LAST:event_ShortHumanActionPerformed
    /**
     * ShortComputer - receives an event and then sets the short player to
     * "computer"
     *
     * @param evt
     */
private void ShortComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShortComputerActionPerformed
    shortPlayer = "computer";
}//GEN-LAST:event_ShortComputerActionPerformed
    /**
     * pullThePlug - allows the user to exit the main panel from the file menu
     */
    public void pullThePlug() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainClientGui().setVisible(true);
            }
        });
    }

    /**
     * gameThread - In order to facilitate accurate game movement, a seperate
     * thread is created for the game. Within this thread exists one method,
     * run, which runs the game.
     */
    private class gameThread extends Thread {

        public void run() {

            DrawingPanel panel = new DrawingPanel(GameWidth, GameHeight);
            Graphics g = panel.getGraphics();
            panel.setBackground(Color.LIGHT_GRAY);
            ShannonSwitchingGraph test = new ShannonSwitchingGraph(GameWidth, GameHeight);
            test.shortWins();
            test.draw(g);
            panel.setWindowTitle("GAME TIME");
            panel.copyGraphicsToScreen();
            g.setColor(Color.magenta);
            boolean shortTurn = true;
            for (; !test.shortWins() && !test.cutWins();) {
                panel.setBackground(Color.LIGHT_GRAY);

                g.fillOval(panel.getMouseX(), panel.getMouseY(), 5, 5);
                if (shortTurn) {
                    panel.setWindowTitle("Short's Turn");
                    if ("human".equals(shortPlayer)) {
                        while (!panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
                            panel.sleep(10);
                        }
                        test.lockEdgeAt(panel.getMouseX(), panel.getMouseY());
                    } else {
                        test.makeShortMove();
                    }
                } else {
                    panel.setWindowTitle("Cut's Turn");
                    if ("human".equals(cutPlayer)) {
                        while (!panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
                            panel.sleep(10);
                        }
                        test.cutEdgeAt(panel.getMouseX(), panel.getMouseY());
                    } else {
                        test.makeCutMove();
                    }
                }
                shortTurn = !shortTurn;

                test.draw(g);
                panel.copyGraphicsToScreen();
                panel.sleep(500);
            }
            if (test.cutWins()) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("SansSerif", Font.PLAIN, 90));
                g.drawString("CUT WINS", GameWidth / 4 - 14, GameHeight / 2);
                panel.copyGraphicsToScreen();
            } else {
                g.setColor(Color.BLACK);
                g.setFont(new Font("SansSerif", Font.PLAIN, 90));
                g.drawString("SHORT WINS", GameWidth / 4 - 14, GameHeight / 2);
                panel.copyGraphicsToScreen();
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CutComputer;
    private javax.swing.JRadioButton CutHuman;
    private javax.swing.JButton Exit;
    private javax.swing.JRadioButton LargeWindowSize;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JRadioButton MediumWindowSize;
    private javax.swing.JLabel PlayerLabel;
    private javax.swing.JRadioButton ShortComputer;
    private javax.swing.JRadioButton ShortHuman;
    private javax.swing.JRadioButton SmallWindowSize;
    private javax.swing.JButton Starter;
    private javax.swing.ButtonGroup cutGroup;
    private javax.swing.JMenuItem exitOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.ButtonGroup shortGroup;
    private javax.swing.ButtonGroup sizeGroup;
    // End of variables declaration//GEN-END:variables
}