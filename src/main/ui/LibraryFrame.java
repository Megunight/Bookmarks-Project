package ui;

import model.Book;
import model.Library;
import observer.OperationsObserver;
import model.EventLog;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LibraryFrame extends JFrame implements OperationsObserver, WindowListener {
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
        addWindowListener(this);
    }

    // EFFECTS: prints the event log to the console
    public void printLog(EventLog el) {
        for (Event e : el) {
            System.out.println(e.toString());
        }
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
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
