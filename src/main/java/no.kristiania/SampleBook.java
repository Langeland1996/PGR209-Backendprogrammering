package no.kristiania;

public class SampleBook {

    private final Book book;

    public SampleBook() {
        book = new Book();

    }

    public Book getBook(){
        return book;
    }

    public void pickOne(String property, String... alternatives){
        if (property.equals("title")){
            book.setTitle(alternatives[(int) Math.floor(Math.random() + alternatives.length - 1)]);
        } else if (property.equals("author")){
            book.setAuthor(alternatives[(int) Math.floor(Math.random() + alternatives.length - 1)]);
        } else {
            System.out.println("Input invalid, try \"title\" | \"author\".");
        }
    }
}
