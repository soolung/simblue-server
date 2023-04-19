package com.soogung.simblue.domain.auth.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "refreshToken", timeToLive = 2592000L)
public class RefreshToken {
    @Id
    private String id;

    private String refreshToken;

    private String role;

    @TimeToLive
    private long ttl;

    private ZonedDateTime expiredAt;

    public RefreshToken update(final String refreshToken, final long ttl) {
        this.refreshToken = refreshToken;
        this.ttl = ttl;
        return this;
    }
}
