package com.moneyhop.bookmark.repository;

import com.moneyhop.bookmark.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    public Users findUserByEmail(String email);

    public Users findUserByUserName(String userName);
}
