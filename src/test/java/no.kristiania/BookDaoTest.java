package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class BookDaoTest {

    private BookDao dao;

    @BeforeEach
    void setUp() throws SQLException {
        var dataSource = new JdbcDataSource();
        //How we connect to the database:
        //1. Define jdbc
        //2. type: h2
        //3. how the data is stored: memory/disk
        //4. Name of database: testdatabase
        //5. Wait til the program is done to close the DB: DB_CLOSE_DELAY=-1
        dataSource.setURL("jdbc:h2:mem:testdatabase;DB_CLOSE_DELAY=-1");
        try (var connection = dataSource.getConnection()) {
            var statement = connection.createStatement();
            var sql = "create table books(id serial primary key, title varchar(100), author varchar(100))";
            statement.executeUpdate(sql);

        }

        dao = new BookDao(dataSource);
    }


    @Test
    void shouldRetriveSavedBook() throws SQLException {

        var sampleBook = new SampleBook();
        sampleBook.pickOne("title","Why we sleep", "Why we eat", "why we walk");
        sampleBook.pickOne("author", "matthew walker", "matthew eater", "matthew sleeper");
        var book = sampleBook.getBook();

        System.out.println("Book before saving in DB:   "+book);

        dao.save(book);
        assertThat(dao.retrieve(book.getId()))
                //Compare books and properies of book
                .usingRecursiveComparison()
                .isEqualTo(book);

        System.out.println("Book after retrieved from DB: "+book);

    }


}
