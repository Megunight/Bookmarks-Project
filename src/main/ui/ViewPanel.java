package ui;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private Library library;

    // EFFECTS: creates a new ViewPanel
    public ViewPanel(Library library) {
        this.library = library;
        setPreferredSize(new Dimension(600, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setBackground(Color.decode("#80765b"));
        libraryToLabels(library);
        add(new BackgroundPanel());
    }

    // MODIFIES: this
    // EFFECTS: adds a label for each book in the library
    private void libraryToLabels(Library l) {
        for (Book b: l.getLibrary()) {
            JLabel bookLabel = new JLabel(b.getTitle());
            bookLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bookLabel.setPreferredSize(new Dimension(100, 140));
            add(bookLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes the view panel
    public void refreshView(Library library) {
        this.library = library;
        removeAll();
        libraryToLabels(library);
        add(new BackgroundPanel());
        revalidate();
        repaint();
    }
}
