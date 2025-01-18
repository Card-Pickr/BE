package com.BE.Repository;

import com.BE.Common.LoginMethod;
import com.BE.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    //외부 로그인 (지금은 x)
    //Optional<UserEntity> findByLoginMethodAndExternalId(LoginMethod loginMethod, String externalId);
}
