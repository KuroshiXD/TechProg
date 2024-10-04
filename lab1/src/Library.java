import java.util.ArrayList;
import java.util.List;
public class Library {
    private List<Book> books = new ArrayList<Book>();
    public void addBook(Book book) { books.add(book); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Book book : books){
            sb.append(book).append("\n");
        }
        return sb.toString();
    }
}

