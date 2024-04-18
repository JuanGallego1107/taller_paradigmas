import java.util.ArrayList;
import java.util.List;

// Clase que representa un libro
class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Clase que representa un usuario de la biblioteca
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Clase que representa el sistema de gestión de la biblioteca
class LibrarySystem {
    private List<Book> books;
    private List<User> users;

    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void borrowBook(User user, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            System.out.println(user.getName() + " has borrowed the book: " + book.getTitle());
        } else {
            System.out.println("The book " + book.getTitle() + " is not available.");
        }
    }

    public void returnBook(User user, Book book) {
        book.setAvailable(true);
        System.out.println(user.getName() + " has returned the book: " + book.getTitle());
    }
}

// Clase principal que contiene el programa principal
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Crear el sistema de gestión de la biblioteca
        LibrarySystem librarySystem = new LibrarySystem();

        // Agregar algunos libros a la biblioteca
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        librarySystem.addBook(book1);
        librarySystem.addBook(book2);

        // Agregar algunos usuarios
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        librarySystem.addUser(user1);
        librarySystem.addUser(user2);

        // Realizar algunas operaciones en la biblioteca
        librarySystem.borrowBook(user1, book1);
        librarySystem.borrowBook(user2, book2);
        librarySystem.returnBook(user1, book1);
    }
}
