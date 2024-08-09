package org.gradle.homework.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.gradle.homework.model.Book;
import org.gradle.homework.model.dto.request.BookRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Transactional
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public Book insertBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
        entityManager.persist(book);
        return book;
    }

    public List<Book> readAllBook() {
        return entityManager.createNativeQuery("SELECT * FROM books", Book.class).getResultList();
    }

    public Book readBookById(UUID bookUUID) {
       return entityManager.find(Book.class,bookUUID);
    }

    public List<Book> readBookByTitle(String bookTitle) {
        TypedQuery<Book> res = entityManager.createQuery("SELECT b FROM Book b WHERE lower(b.title) ilike  lower(:bookTitle)", Book.class);
        res.setParameter("bookTitle", "%" + bookTitle.toLowerCase() + "%");
        return res.getResultList();
    }

    public Book updateBook(UUID id, BookRequest updateBook) {
        Book book = entityManager.find(Book.class, id);
        entityManager.detach(book);
        book.setTitle(updateBook.getTitle());
        book.setDescription(updateBook.getDescription());
        book.setAuthor(updateBook.getAuthor());
        book.setPublicationYear(updateBook.getPublicationYear());
        entityManager.merge(book);
        return book;
    }
    

    public Book deleteBook(UUID bookId) {
        Book book = entityManager.find(Book.class, bookId);
        entityManager.remove(book);
        return book;
    }
}
