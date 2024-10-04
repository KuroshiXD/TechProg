import java.util.List;
import java.util.ArrayList;

public class Book {

    private int id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private Price price;
    private String format;
    private List<Review> reviews = new ArrayList<Review>();
    private String language;
    private String translator;
    private List<String> awards = new ArrayList<>();
    private Publisher publisher;
    private String isbn;


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) { this.author = author; }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public void addAward(String award) {
        this.awards.add(award);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setPrice(Price price) { this.price = price; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book ID: ").append(id).append("\n")
                .append("Title: ").append(title).append("\n")
                .append("Author: ").append(author).append("\n")
                .append("Year: ").append(year).append("\n")
                .append("Genre: ").append(genre).append("\n")
                .append("Price: ").append(price).append("\n");
        if (format != null) sb.append("Format: ").append(format).append("\n");
        if(isbn != null) sb.append("ISBN: ").append(isbn).append("\n");
        if (!reviews.isEmpty()) {
            sb.append("Reviews:\n");
            for (Review review : reviews) {
                sb.append(review).append("\n");
            }
        }
        if (language != null) sb.append("Language: ").append(language).append("\n");
        if (translator != null) sb.append("Translator: ").append(translator).append("\n");
        if (!awards.isEmpty()) {
            sb.append("Awards: ").append(String.join(", ", awards)).append("\n");
        }
        if (publisher != null) sb.append("Publisher: ").append(publisher).append("\n");
        sb.append("\n").append("---------------------------------").append("\n");
        return sb.toString();
    }
}

class Price {
    private String currency;
    private double price;

    public Price(double price, String currency) {
        this.currency = currency;
        this.price = price;
    }

    @Override
    public String toString() {
        return price + " " + currency;
    }
}

class Review {
    private String user;
    private int rating;
    private String comment;

    public void setUser(String user) {
        this.user = user;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "\tUser: " + user + "\n\t\tRating: " + rating + "\n\t\tComment: " + comment;
    }
}

class Publisher {
    private String name;
    private Address address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + ", Address: " + address;
    }
}

class Address {
    private String city;
    private String country;

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
