package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.presentation.dto.response.SearchUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchUserService {

    private final UserRepository userRepository;

    public List<SearchUserResponse> execute(String q, Authority authority) {
        return userRepository.searchUser(q, authority)
                .stream().map(SearchUserResponse::of)
                .collect(Collectors.toList());
    }
}
