package model;

// represents a singular book object that contains various info
public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int pageNum;
    private int pagesRead;
    private boolean completed;
    private int rating = 0;
    private String genre;

    public Book(String title, String author, int pageNum, int pagesRead, String genre) {
        this.title = title;
        this.author = author;
        this.pageNum = pageNum;
        this.pagesRead = pagesRead;
        if (pageNum == pagesRead) {
            this.completed = true;
        } else {
            this.completed = false;
        }
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    //REQUIRES: has to be an integer >= 0
    //MODIFIES: this
    //EFFECTS: updates pagesRead with given positive integer
    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getRating() {
        return rating;
    }

    //MODIFIES: this
    //EFFECTS: updates rating of object with given int between 0 and 5
    public void setRating(int rating) {
        assert (rating >= 0 && rating <= 5);
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public int compareTo(Book o) {
        return Integer.compare(getRating(), o.getRating());
    }
}
