package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdatePasswordRequest;
import com.soogung.simblue.domain.user.presentation.dto.response.SearchUserResponse;
import com.soogung.simblue.domain.user.presentation.dto.response.UserResponse;
import com.soogung.simblue.domain.user.service.DeleteUserService;
import com.soogung.simblue.domain.user.service.QueryCurrentUserService;
import com.soogung.simblue.domain.user.service.SearchUserService;
import com.soogung.simblue.domain.user.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final QueryCurrentUserService queryCurrentUserService;
    private final SearchUserService searchUserService;
    private final UpdatePasswordService updatePasswordService;
    private final DeleteUserService deleteUserService;

    @GetMapping
    public UserResponse getUser() {
        return queryCurrentUserService.execute();
    }

    @GetMapping("/search")
    public List<SearchUserResponse> searchTeacher(
            @RequestParam(name = "q") String q,
            @RequestParam(name = "authority", required = false) Authority authority
    ) {
        return searchUserService.execute(q, authority);
    }

    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @DeleteMapping
    public void deleteUser() {
        deleteUserService.execute();
    }
}
