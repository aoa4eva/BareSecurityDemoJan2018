package me.afua.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserClassRepo extends CrudRepository<UserClass,Long>{
    UserClass findUserClassByUsername(String username);
}

