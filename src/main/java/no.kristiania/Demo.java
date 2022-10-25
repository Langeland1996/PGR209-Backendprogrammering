package no.kristiania;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Demo {
    private BookDao bookDao;

    public Demo (DataSource dataSource){
        bookDao = new BookDao(dataSource);
    }

    public void run() throws SQLException {
        var sampleBook = new SampleBook();
        sampleBook.pickOne("title", "why we sleep", "why we eat", "why we walk");
        sampleBook.pickOne("author", "Matthew sleeper", "Matthew eater", "Matthew walker");
        var book = sampleBook.getBook();

        bookDao.save(book);


    }

    public static void main(String[] args) throws SQLException {
        var dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/testdatabase");
        dataSource.setUser("testuser");
        dataSource.setPassword("drossap321");
        //To implements migration of database:
        // 1. Download flyway dep
        // 2. Create folder db.migration in /resources
        // 3. Drop the table/tables you want to create and make them in a V001__"NameOfFile".sql
        // Tips: Using .location()  to change directory, default is .location("db/migration")
        Flyway.configure().dataSource(dataSource).load().migrate();
        var demo = new Demo(dataSource);
        demo.run();
    }
}
