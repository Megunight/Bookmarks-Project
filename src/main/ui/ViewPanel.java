package ui;

import model.Library;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private Library library;

    public ViewPanel(Library library) {
        this.library = library;
        setPreferredSize(new Dimension(200, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

}
