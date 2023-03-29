package ui;

import model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationPanels extends JPanel implements ActionListener {
    private Library library;
    private JPanel add = new JPanel();
    private JPanel remove = new JPanel();
    private JPanel rate = new JPanel();
    private JPanel daily = new JPanel();
    private JComboBox operationsBox;

    public OperationPanels(Library library) {
        this.library = library;
        setPreferredSize(new Dimension(200, 500));
        setLayout(new CardLayout());
        String[] operations = {"Add", "Remove", "Rate", "Daily"};
        operationsBox = new JComboBox(operations);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operation = (String) operationsBox.getSelectedItem();
        if (operation.equals("Add")) {
            add = new AddPanel(library);
            add(add);
        } else if (operation.equals("Remove")) {
            remove = new RemovePanel(library);
            add(remove);
        } else if (operation.equals("Rate")) {
            rate = new RatePanel(library);
            add(rate);
        } else if (operation.equals("Daily")) {
            daily = new DailyPanel(library);
            add(daily);
        }
    }
}
