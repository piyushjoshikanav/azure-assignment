package com.nagarro.training.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.model.BookAuthor;

public interface AuthorRepo extends JpaRepository<BookAuthor, Integer> {

}
