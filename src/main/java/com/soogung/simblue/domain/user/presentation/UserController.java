package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdatePasswordRequest;
import com.soogung.simblue.domain.user.presentation.dto.response.SearchUserResponse;
import com.soogung.simblue.domain.user.presentation.dto.response.UserResponse;
import com.soogung.simblue.domain.user.service.QueryCurrentUserService;
import com.soogung.simblue.domain.user.service.SearchUserService;
import com.soogung.simblue.domain.user.service.UpdatePasswordService;
import com.soogung.simblue.global.auth.annotation.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final QueryCurrentUserService queryCurrentUserService;
    private final SearchUserService searchUserService;
    private final UpdatePasswordService updatePasswordService;

    @GetMapping
    public UserResponse getUser(
            @AuthenticationPrincipal User user
    ) {
        return queryCurrentUserService.execute(user);
    }

    // TODO :: 로그인한 유저만 검색 가능하게 만든 후 handling
    @GetMapping("/search")
    public List<SearchUserResponse> searchUser(
            @RequestParam(name = "q") String q,
            @RequestParam(name = "authority", required = false) Authority authority
    ) {
        return searchUserService.execute(q, authority);
    }

    @PatchMapping("/password")
    public void updatePassword(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdatePasswordRequest request
    ) {
        updatePasswordService.execute(user, request);
    }
}
