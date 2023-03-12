package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.*;
import com.soogung.simblue.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UpdatePasswordService updatePasswordService;
    private final DeleteUserService deleteUserService;

    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @DeleteMapping
    public void deleteUser() {
        deleteUserService.execute();
    }
}
