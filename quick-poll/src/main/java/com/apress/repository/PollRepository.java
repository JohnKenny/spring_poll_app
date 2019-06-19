package com.apress.repository;
import org.springframework.data.repository.CrudRepository;
import com.apress.domain.Poll;

import java.util.List;

import org.springframework.data.jpa.repository.*;

public interface PollRepository extends JpaRepository<Poll, Long>, CrudRepository<Poll, Long> {
    //List<Poll> findAllById(Long pollId);

}
