package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import org.springframework.data.repository.CrudRepository;

public interface IKingdomRepository extends CrudRepository<Kingdom, Long> {

  boolean existsByName(String name);

  Kingdom findKingdomById(long id);

}
