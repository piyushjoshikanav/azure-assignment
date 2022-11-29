package com.nagarro.training.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.model.BooksModel;

public interface BookRepo extends JpaRepository<BooksModel, Integer> {

}
