package com.springboot.jpa.persistence;

import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	
}
