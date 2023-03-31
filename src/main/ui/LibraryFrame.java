package ui;

import model.Book;
import model.Library;
import observer.OperationsObserver;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame implements OperationsObserver {
    private Library library = new Library();
    private OperationPanels operationPanels;
    private ViewPanel viewPanel;
    private InfoPanel infoPanel;

    // EFFECTS: creates a new LibraryFrame
    public LibraryFrame() {
        super("Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(false);
        setPreferredSize(new Dimension(1000, 650));
        viewPanel = new ViewPanel(library);
        operationPanels = new OperationPanels(library);
        infoPanel = new InfoPanel(library);
        operationPanels.addObserver(this);
        add(operationPanels, BorderLayout.EAST);
        add(viewPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.WEST);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: updates the view panel
    @Override
    public void updateView(Library library) {
        this.library = library;
        viewPanel.refreshView(library);
    }

    // MODIFIES: this
    // EFFECTS: updates the info panel
    @Override
    public void updateInfo(Library library) {
        this.library = library;
        infoPanel.refreshInfo(library);
    }
}
