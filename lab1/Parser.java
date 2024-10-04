import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private Library library = new Library();
    private Book currentBook = null;
    private Review currentReview = null;
    private Publisher currentPublisher = null;
    private Address currentAddress = null;


    public Library parse(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("<?")) continue;

                if (line.startsWith("<library>") || line.startsWith("</library>")) continue;

                if (line.startsWith("<book")) {
                    currentBook = new Book();
                    int idStart = line.indexOf("id=\"") + 4;
                    int idEnd = line.indexOf("\"", idStart);
                    String idStr = line.substring(idStart, idEnd);
                    currentBook.setId(Integer.parseInt(idStr));
                }

                if (line.startsWith("</book>")) {
                    library.addBook(currentBook);
                    currentBook = null;
                    continue;
                }

                if (currentBook != null) {
                    if (line.startsWith("<title>")) {
                        String title = getTagValue(line, "title");
                        currentBook.setTitle(title);
                        continue;
                    }

                    if (line.startsWith("<author>")) {
                        String author = getTagValue(line, "author");
                        currentBook.setAuthor(author);
                        continue;
                    }

                    if (line.startsWith("<year>")) {
                        int yearInt = Integer.parseInt(getTagValue(line, "year"));
                        currentBook.setYear(yearInt);
                        continue;
                    }

                    if (line.startsWith("<genre>")) {
                        String genre = getTagValue(line, "genre");
                        currentBook.setGenre(genre);
                        continue;
                    }

                    if (line.startsWith("<price")) {
                        int currencyStart = line.indexOf("currency=\"") + 10;
                        int currencyEnd = line.indexOf("\"", currencyStart);
                        String currency = line.substring(currencyStart, currencyEnd);
                        double price = Double.parseDouble(getTagValue(line, "price"));
                        currentBook.setPrice(new Price(price, currency));
                        continue;
                    }

                    if (line.startsWith("<format>")) {
                        String format = getTagValue(line, "format");
                        currentBook.setFormat(format);
                        continue;
                    }

                    if (line.startsWith("<isbn>")) {
                        String isbn = getTagValue(line, "isbn");
                        currentBook.setIsbn(isbn);
                        continue;
                    }

                    if (line.startsWith("<language>")) {
                        String format = getTagValue(line, "language");
                        currentBook.setLanguage(format);
                        continue;
                    }

                    if (line.startsWith("<translator>")) {
                        String format = getTagValue(line, "translator");
                        currentBook.setTranslator(format);
                        continue;
                    }

                    if (line.startsWith("<review>")) {
                        currentReview = new Review();
                        continue;
                    }

                    if (line.startsWith("</review")) {
                        currentBook.addReview(currentReview);
                        currentReview = null;
                        continue;
                    }

                    if (currentReview != null) {
                        if (line.startsWith("<user>")) {
                            String user = getTagValue(line, "user");
                            currentReview.setUser(user);
                            continue;
                        }

                        if (line.startsWith("<rating>")) {
                            int rating = Integer.parseInt(getTagValue(line, "rating"));
                            currentReview.setRating(rating);
                            continue;
                        }

                        if (line.startsWith("<comment>")) {
                            String comment = getTagValue(line, "comment");
                            currentReview.setComment(comment);
                            continue;
                        }
                    }

                    if (line.startsWith("<award>")) {
                        String award = getTagValue(line, "award");
                        currentBook.addAward(award);
                        continue;
                    }

                    if (line.startsWith("<publisher>")) {
                        currentPublisher = new Publisher();
                        continue;
                    }

                    if (line.startsWith("</publisher>")) {
                        currentBook.setPublisher(currentPublisher);
                        currentPublisher = null;
                        continue;
                    }

                    if (currentPublisher != null) {
                        if (line.startsWith("<name>")) {
                            String name = getTagValue(line, "name");
                            currentPublisher.setName(name);
                            continue;
                        }

                        if (line.startsWith("<address>")) {
                            currentAddress = new Address();
                            continue;
                        }

                        if (line.startsWith("</address>")) {
                            currentPublisher.setAddress(currentAddress);
                            currentAddress = null;
                            continue;
                        }

                        if (currentAddress != null) {
                            if (line.startsWith("<city>")) {
                                String city = getTagValue(line, "city");
                                currentAddress.setCity(city);
                                continue;
                            }

                            if (line.startsWith("<country>")) {
                                String country = getTagValue(line, "country");
                                currentAddress.setCountry(country);
                                continue;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return library;
    }

    private String getTagValue(String line, String tag) {
        if (line.startsWith("<price")) {
            line = line.replaceAll("<price\\s+currency=\"[^\"]+\">", "<price>");
        }
        return line.replace("<" + tag + ">", "")
                .replace("</" + tag + ">", "").trim();
    }
}
