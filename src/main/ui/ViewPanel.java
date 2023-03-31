package ui;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private Library library;

    public ViewPanel(Library library) {
        this.library = library;
        setPreferredSize(new Dimension(600, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setBackground(Color.decode("#80765b"));
        libraryToLabels(library);
        add(new BackgroundPanel());
    }

    private void libraryToLabels(Library l) {
        for (Book b: l.getLibrary()) {
            JLabel bookLabel = new JLabel(b.getTitle());
            bookLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bookLabel.setPreferredSize(new Dimension(100, 140));
            add(bookLabel);
        }
    }

    public void refreshView(Library library) {
        this.library = library;
        removeAll();
        libraryToLabels(library);
        add(new BackgroundPanel());
        revalidate();
        repaint();
    }
}
