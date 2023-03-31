# Bookmarks

## bookmarking/bookshelf management system

I wanted to create a program that allows me to:
- track what books/novels I'm currently reading
- what page I'm on
- give me statistics on the book like how many pages left, how long left to read
- allows me to sort the books based on tags (genres) and ratings

I read a lot of webnovels, but I constantly pause in the middle of one to start reading a new one. I also always
between my phone and laptop to read, so it becomes annoying real quick to try and track all the novels, resulting
in lots of tabs open at once. I want a centralized tracking system that helps organize my progress in reading.
The target audience for this program is people who read lots of novels or wants to start reading novels.

## User Stories

- As a user, I want to be able to add a book I'm currently reading to the list
- As a user, I want to be able to track how many pages I've read and the page I'm on
- As a user, I want to be able to set a daily reading goal/target for myself and see if I've achieved it
- As a user, I want to be able to sort the books in various ways via tags and ratings etc.
- As a user, I want to see stats on the books ex. how long to read based on my reading speed
- As a user, I want to be able to save the current state of my library locally
- As a user, I want to be able to load a state that I saved before even after quitting

## Instructions for Grader

- To run the program, run the Main class
- To add a book, make sure combobox is set to "Add Book", Title field is a string, Author field is a string,
Number of Pages field is an integer, Number of Pages Read field is an integer, and Genre field is a string.
- To remove a book, make sure combobox is set to "Remove Book", and Title field is a string of a book available in
the view panel (center).
- Save and load buttons will always be on the bottom right, regardless of which option is chosen.
- Visual component is visible along the bottom of the view panel as a shelf.