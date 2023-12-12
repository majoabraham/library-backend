# Library management application.
- backend

The task of the application is to register books, readers and borrowing / returning books.

The application contains 3 agendas:
1. A list of all books with information about their title, author and an indication of whether the book is borrowed.
2. List of all readers with information on name, surname, ID number and date of birth.
3. List of loans and returns of individual books to readers, while the flag is updated as to whether the book is borrowed.

The library has three data tables:
1. List of books (columns: book ID, Title, Author, flag whether it is borrowed)
2. List of readers (columns: ID card number, Name, Surname, Date of birth)
3. List of books borrowed and returned - which reader borrowed which book and when did he return it
