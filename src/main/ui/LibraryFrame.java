package ui;

import javax.swing.*;

public class LibraryFrame extends JFrame {
    private OperationPanels operationPanels;
    private ViewPanel viewPanel;

    public LibraryFrame() {
        super("Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
