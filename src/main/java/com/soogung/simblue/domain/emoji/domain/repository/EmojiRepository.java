package com.soogung.simblue.domain.emoji.domain.repository;

import com.soogung.simblue.domain.emoji.domain.Emoji;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmojiRepository extends CrudRepository<Emoji, Long> {
    List<Emoji> findAll();
}
