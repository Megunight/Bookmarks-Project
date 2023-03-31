package ui;

import exceptions.BookNotFoundException;
import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InfoPanel extends JPanel implements ActionListener {
    private Library library;
    private JTextField searchField;
    private JButton searchButton;
    private String bookTitle;
    private JPanel infoGroup;

    // EFFECTS: creates a new InfoPanel
    public InfoPanel(Library library) {
        this.library = library;
        setPreferredSize(new Dimension(200, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.WHITE);
        bookSearch();
    }

    // EFFECTS: adds panel including search button and textfield to the info panel
    private void bookSearch() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        searchPanel.setPreferredSize(new Dimension(200, 120));
        searchField = new JTextField("Title");
        searchField.setPreferredSize(new Dimension(200, 40));
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel);
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: adds all the necessary info from the book in the form of labels to the info panel
    private void addInfo() {
        try {
            try {
                remove(infoGroup);
            } catch (NullPointerException e) {
                // do nothing
            }
            int indexOfBook = library.getIndexOfBook(bookTitle);
            List<Book> libraryList = library.getLibrary();
            Book b = libraryList.get(indexOfBook);
            JLabel title = new JLabel("Title: " + b.getTitle());
            JLabel author = new JLabel("Author: " + b.getAuthor());
            JLabel genre = new JLabel("Genre: " + b.getGenre());
            JLabel pages = new JLabel("Pages: " + b.getPageNum());
            JLabel pagesRead = new JLabel("Pages Read: " + b.getPagesRead());
            JLabel rating = new JLabel("Rating: " + b.getRating());
            infoGroup = new JPanel();
            infoGroup.setLayout(new GridLayout(7, 1));
            infoGroup.setPreferredSize(new Dimension(200, 500));
            infoGroup.setBackground(Color.WHITE);
            infoGroup.add(title);
            infoGroup.add(author);
            infoGroup.add(genre);
            infoGroup.add(pages);
            infoGroup.add(pagesRead);
            infoGroup.add(rating);
            if (b.getPagesReadToday() != 0) {
                JLabel daysLeft = new JLabel("Days Left: " + b.getDaysLeft());
                infoGroup.add(daysLeft);
            }
            add(infoGroup);
            revalidate();
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes the info panel
    public void refreshInfo(Library library) {
        this.library = library;
        try {
            remove(infoGroup);
        } catch (NullPointerException e) {
            // do nothing
        }
        revalidate();
    }

    // EFFECTS: when search button is pressed, the book info is added to the info panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            bookTitle = searchField.getText();
            addInfo();
        }
    }
}
