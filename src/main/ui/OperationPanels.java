package ui;

import exceptions.BookNotFoundException;
import exceptions.SameTitleException;
import model.Book;
import model.Library;
import observer.OperationsObserver;
import observer.Subject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OperationPanels extends JPanel implements ItemListener, ActionListener, Subject {
    private static final String JSON_STORE = "./data/libraries.json";
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel cards = new JPanel();
    private JPanel addPanel = new JPanel();
    private JPanel removePanel = new JPanel();
    private JPanel ratePanel = new JPanel();
    private JPanel dailyUpdPanel = new JPanel();
    private JPanel dailySetPanel = new JPanel();

    private JTextField titleAdd;
    private JTextField author;
    private JTextField pageNum;
    private JTextField pagesRead;
    private JTextField genre;
    private JTextField titleRemove;
    private JTextField titleRate;
    private JTextField rate;
    private JTextField titleDailyUpd;
    private JTextField pagesReadDaily;
    private JTextField dailyGoal;

    private Button addBookButton;
    private Button removeBookButton;
    private Button rateBookButton;
    private Button dailyUpdButton;
    private Button dailyGoalButton;
    private Button saveButton;
    private Button loadButton;

    private JComboBox operationsBox;
    private JLabel goalAchieved;

    // EFFECTS: creates a new OperationPanels
    public OperationPanels(Library library) {
        this.library = library;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setPreferredSize(new Dimension(200, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        addComboCard();
        styleSaveButtons();
    }

    // EFFECTS: adds combo box and card panels to the panel
    private void addComboCard() {
        JPanel comboBoxPanel = new JPanel();
        String[] operations = {"Add", "Remove", "Rate", "Daily", "Daily Set"};
        operationsBox = new JComboBox(operations);
        operationsBox.addItemListener(this);
        comboBoxPanel.add(operationsBox);
        add(comboBoxPanel);
        addCards();
    }

    // EFFECTS: adds card panels to the panel and styles them
    private void addCards() {
        cards.setLayout(new CardLayout());
        setPreferredSize(new Dimension(200, 400));
        cards.add(addPanel, "Add");
        cards.add(removePanel, "Remove");
        cards.add(ratePanel, "Rate");
        cards.add(dailyUpdPanel, "Daily");
        cards.add(dailySetPanel, "Daily Set");
        styleAddPanel();
        styleRemovePanel();
        styleRatePanel();
        styleDailyUpdPanel();
        styleDailySetPanel();
        add(cards);
    }

    // EFFECTS: adds the buttons and textfields to the add panel and styles them
    private void styleAddPanel() {
        addPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        addPanel.setPreferredSize(new Dimension(200, 400));
        addPanel.setBackground(Color.WHITE);
        addTextFieldsAdd();
        addBookButton = new Button("Add Book");
        addBookButton.addActionListener(this);
        addPanel.add(addBookButton);
    }

    // EFFECTS: adds the buttons and textfields to the remove panel and styles them
    private void styleRemovePanel() {
        removePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        removePanel.setPreferredSize(new Dimension(200, 400));
        removePanel.setBackground(Color.WHITE);
        titleRemove = new JTextField("Title");
        titleRemove.setPreferredSize(new Dimension(200, 40));
        removeBookButton = new Button("Remove Book");
        removeBookButton.addActionListener(this);
        removePanel.add(titleRemove);
        removePanel.add(removeBookButton);
    }

    // EFFECTS: adds the buttons and textfields to the rate panel and styles them
    private void styleRatePanel() {
        ratePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        ratePanel.setPreferredSize(new Dimension(200, 400));
        ratePanel.setBackground(Color.WHITE);
        JPanel textFields = new JPanel();
        textFields.setLayout(new GridLayout(2, 1));
        textFields.setPreferredSize(new Dimension(200, 80));
        titleRate = new JTextField("Title");
        rate = new JTextField("Rating");
        rateBookButton = new Button("Rate Book");
        rateBookButton.addActionListener(this);
        textFields.add(titleRate);
        textFields.add(rate);
        ratePanel.add(textFields);
        ratePanel.add(rateBookButton);
    }

    // EFFECTS: adds the buttons and textfields to the daily update panel and styles them
    private void styleDailyUpdPanel() {
        dailyUpdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        dailyUpdPanel.setPreferredSize(new Dimension(200, 400));
        dailyUpdPanel.setBackground(Color.WHITE);
        JPanel textFields = new JPanel();
        textFields.setLayout(new GridLayout(2, 1));
        textFields.setPreferredSize(new Dimension(200, 80));
        titleDailyUpd = new JTextField("Title");
        pagesReadDaily = new JTextField("Pages Read");
        textFields.add(titleDailyUpd);
        textFields.add(pagesReadDaily);
        dailyUpdButton = new Button("Update Book");
        dailyUpdButton.addActionListener(this);
        dailyUpdPanel.add(textFields);
        dailyUpdPanel.add(dailyUpdButton);
    }

    // EFFECTS: adds the buttons and textfields to the daily set panel and styles them
    private void styleDailySetPanel() {
        dailySetPanel.setLayout(new BorderLayout());
        dailySetPanel.setPreferredSize(new Dimension(200, 400));
        dailySetPanel.setBackground(Color.WHITE);
        JPanel group = new JPanel();
        group.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        group.setPreferredSize(new Dimension(200, 80));
        group.setBackground(Color.WHITE);
        dailyGoal = new JTextField("Daily Goal");
        dailyGoal.setPreferredSize(new Dimension(200, 40));
        dailyGoalButton = new Button("Set Goal");
        dailyGoalButton.addActionListener(this);
        group.add(dailyGoal);
        group.add(dailyGoalButton);
        dailySetPanel.add(group, BorderLayout.CENTER);
        goalAchieved = new JLabel(library.getDailyReadingAccum() + " pages out of "
                + library.getDailyReadingGoal() + " pages read today");
        dailySetPanel.add(goalAchieved, BorderLayout.SOUTH);
    }

    // EFFECTS: adds the textfields to the add panel and styles them
    private void addTextFieldsAdd() {
        JPanel textFields = new JPanel();
        textFields.setLayout(new GridLayout(5, 1));
        titleAdd = new JTextField("Title");
        author = new JTextField("Author");
        pageNum = new JTextField("Number of Pages");
        pagesRead = new JTextField("Number of Pages Read");
        genre = new JTextField("Genre");
        textFields.setPreferredSize(new Dimension(200, 200));
        textFields.add(titleAdd);
        textFields.add(author);
        textFields.add(pageNum);
        textFields.add(pagesRead);
        textFields.add(genre);
        addPanel.add(textFields);
    }

    // EFFECTS: adds the buttons save and load to the panel and styles them
    private void styleSaveButtons() {
        saveButton = new Button("Save");
        saveButton.addActionListener(this);
        loadButton = new Button("Load");
        loadButton.addActionListener(this);
        JPanel saveLoad = new JPanel();
        saveLoad.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        saveLoad.setPreferredSize(new Dimension(400, 40));
        saveLoad.add(saveButton);
        saveLoad.add(loadButton);
        add(saveLoad);
    }

    // EFFECTS: looks at combo box and changes cards when selection is changed
    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) e.getItem());
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: looks at which button is pressed and performs the appropriate action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBookButton) {
            try {
                library.addBook(new Book(titleAdd.getText(), author.getText(), Integer.parseInt(pageNum.getText()),
                        Integer.parseInt(pagesRead.getText()), genre.getText()));
            } catch (SameTitleException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == removeBookButton) {
            try {
                library.removeBook(titleRemove.getText());
            } catch (BookNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == rateBookButton) {
            try {
                int index = library.getIndexOfBook(titleRate.getText());
                Book book = library.getLibrary().get(index);
                book.setRating(Integer.parseInt(rate.getText()));
                library.sortLibrary();
            } catch (BookNotFoundException | AssertionError ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == dailyUpdButton) {
            try {
                Book book = library.getLibrary().get(library.getIndexOfBook(titleDailyUpd.getText()));
                int amount = Integer.parseInt(pagesReadDaily.getText());
                book.setPagesReadToday(amount);
                book.setPagesRead(book.getPagesRead() + amount);
                library.setDailyReadingAccum(library.getDailyReadingAccum() + amount);
                dailySetPanel.remove(goalAchieved);
                goalAchieved = new JLabel(library.getDailyReadingAccum() + " pages out of "
                        + library.getDailyReadingGoal() + " pages read today");
                dailySetPanel.add(goalAchieved, BorderLayout.SOUTH);
            } catch (BookNotFoundException | AssertionError ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == dailyGoalButton) {
            try {
                library.setDailyReadingGoal(Integer.parseInt(dailyGoal.getText()));
                dailySetPanel.remove(goalAchieved);
                goalAchieved = new JLabel(library.getDailyReadingAccum() + " pages out of "
                        + library.getDailyReadingGoal() + " pages read today");
                dailySetPanel.add(goalAchieved, BorderLayout.SOUTH);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == saveButton) {
            try {
                jsonWriter.open();
                jsonWriter.write(library);
                jsonWriter.close();
                System.out.println("Saved to " + JSON_STORE);
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to save to " + JSON_STORE);
            }
        } else if (e.getSource() == loadButton) {
            try {
                library = jsonReader.read();
                System.out.println("Successfully loaded library");
            } catch (IOException | SameTitleException ex) {
                System.out.println("Unable to read from " + JSON_STORE);
            }
        }
        notifyObservers(library);
    }

    // EFFECTS: adds an observer to the list of observers
    @Override
    public void addObserver(OperationsObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    // EFFECTS: removes an observer from the list of observers
    @Override
    public void removeObserver(OperationsObserver o) {
        observers.remove(o);
    }

    // EFFECTS: notifies all observers of the observer list
    @Override
    public void notifyObservers(Library library) {
        for (OperationsObserver o : observers) {
            o.updateView(library);
            o.updateInfo(library);
        }
    }
}