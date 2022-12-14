package com.kirekov.spring.boot.test.example.repository;

import static com.kirekov.spring.boot.test.example.TestProfiles.TEST_CONTAINERS;
import static com.kirekov.spring.boot.test.example.TestTags.TEST_CONTAINERS_TAG;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kirekov.spring.boot.test.example.entity.Person;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@ActiveProfiles(TEST_CONTAINERS)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName("PersonRepository: findAllLastNames tests with PostgreSQL Testcontainers instance")
@Tag(TEST_CONTAINERS_TAG)
@Slf4j
class PersonRepositoryTestContainers {

  @Autowired
  private PersonRepository personRepository;

  @Test
  @DisplayName("Should return all last names")
  void shouldReturnAlLastNames() {
    log.info("\n\n\nNACHALLLLLLL\n\n\n");
    System.out.println("\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
    personRepository.saveAndFlush(new Person().setFirstName("John").setLastName("Brown"));
    personRepository.saveAndFlush(new Person().setFirstName("Kyle").setLastName("Green"));
    personRepository.saveAndFlush(new Person().setFirstName("Paul").setLastName("Brown"));

    log.info("\n\n\nENDDDDDDDDDDDDDDDDD\n\n\n");
    assertEquals(Set.of("Brown", "Green"), personRepository.findAllLastNames());
  }
}