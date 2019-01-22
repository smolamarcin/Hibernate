package com.smola.hiber.repositories;

import com.smola.hiber.model.UserSQL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserSQL,Long> {

}
