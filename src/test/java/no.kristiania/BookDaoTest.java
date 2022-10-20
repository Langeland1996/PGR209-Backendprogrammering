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
        sampleBook.pickOne("title", "why we sleep", "why we eat", "why we walk");
        sampleBook.pickOne("author", "Matthew sleeper", "Matthew eater", "Matthew walker");
        var book = sampleBook.getBook();

        dao.save(book);
        assertThat(dao.retrive(book.getId()))
                //Compare books and properies of book
                .usingRecursiveComparison()
                .isEqualTo(book);

    }
}