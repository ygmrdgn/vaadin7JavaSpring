package com.example.ilk;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepostory extends JpaRepository<Todo, Long> {
	
@Transactional
void deleteByDone(boolean done);

}
