package no.kristiania;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookDaoTest {

    private BookDao dao = new BookDao();


    @Test
    void shouldRetriveSavedBook() {
        var book = sampleBook();
        dao.save(book);
        assertThat(dao.retrive(book.getId()))
                //Compare books and properies of book
                .usingRecursiveComparison()
                .isEqualTo(book);
    }

    private Book sampleBook(){
        return new Book();
    }
}
