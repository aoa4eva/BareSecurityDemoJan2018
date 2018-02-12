package me.afua.demo;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<UserRole,Long> {
    UserRole findByRole(String rolename);
}
