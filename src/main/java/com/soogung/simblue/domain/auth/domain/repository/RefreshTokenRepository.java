package com.soogung.simblue.domain.auth.domain.repository;

import com.soogung.simblue.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
