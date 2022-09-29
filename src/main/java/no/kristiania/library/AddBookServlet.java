package no.kristiania.library;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.servlet.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AddBookServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddBookServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        book.setTitle(req.getParameter("bookTitle"));
        book.setAuthor(req.getParameter("bookAuthor"));

        logger.info("Adding book {}", book);



    }
}
