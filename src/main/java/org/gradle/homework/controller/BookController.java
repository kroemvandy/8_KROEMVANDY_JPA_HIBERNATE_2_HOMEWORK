package org.gradle.homework.controller;

import lombok.AllArgsConstructor;
import org.gradle.homework.model.dto.request.BookRequest;
import org.gradle.homework.model.dto.response.BookApiResponse;
import org.gradle.homework.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping("/insert")
    public ResponseEntity<?> insertBook(@RequestBody BookRequest bookRequest) {
       BookApiResponse<Object> apiResponse = BookApiResponse
               .builder()
               .message("Book inserted successfully!")
               .payload(bookRepository.insertBook(bookRequest))
               .status(HttpStatus.CREATED)
               .code(201)
               .localDateTime(LocalDateTime.now())
               .build();
       return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/read-allBook")
    public ResponseEntity<List<?>> readAllBook() {
        BookApiResponse<Object> apiResponse = BookApiResponse
                .builder()
                .message("Get all books successfully!")
                .payload(bookRepository.readAllBook())
                .status(HttpStatus.OK)
                .code(200)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(Collections.singletonList(apiResponse));
    }

    @GetMapping("/readBook-byId/{bookId}")
    public ResponseEntity<?> readBookById(@PathVariable UUID bookId) {
        BookApiResponse<Object> apiResponse = BookApiResponse
                .builder()
                .message("Read Book with ID " + bookId + " successfully!")
                .payload(bookRepository.readBookById(bookId))
                .status(HttpStatus.OK)
                .code(200)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/readBook-byTitle/{bookTittle}")
    public ResponseEntity<?> readBookByTitle(@RequestParam("bookTitle") String bookTitle) {
        BookApiResponse<Object> apiResponse = BookApiResponse
                .builder()
                .message("Read Book By Title" + bookTitle + " Successfully!")
                .payload(bookRepository.readBookByTitle(bookTitle))
                .status(HttpStatus.OK)
                .code(200)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("update-book/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "bookId") UUID bookId, @RequestBody BookRequest updateBook) {
        BookApiResponse<Object> apiResponse = BookApiResponse
                .builder()
                .message("Book updated with ID "+ bookId+ " successfully!")
                .payload(bookRepository.updateBook(bookId, updateBook))
                .status(HttpStatus.OK)
                .code(200)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID bookId) {
        BookApiResponse<Object> apiResponse = BookApiResponse
                .builder()
                .message("Book deleted with ID " + bookId + " sucessfully!")
                .payload(bookRepository.deleteBook(bookId))
                .status(HttpStatus.OK)
                .code(200)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
