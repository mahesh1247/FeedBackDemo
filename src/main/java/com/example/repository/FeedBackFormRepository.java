package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.FeedBackForm;
public interface FeedBackFormRepository extends CrudRepository<FeedBackForm, Integer> {

}
