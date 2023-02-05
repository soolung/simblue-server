package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.StudentRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateStudentRequest;
import com.soogung.simblue.domain.user.service.JoinStudentService;
import com.soogung.simblue.domain.user.service.UpdateStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/student")
@RequiredArgsConstructor
public class StudentController {

    private final JoinStudentService joinStudentService;
    private final UpdateStudentService updateStudentService;

    @PostMapping
    public void joinStudent(@RequestBody @Valid StudentRequest request) {
        joinStudentService.execute(request);
    }

    @PutMapping
    public void updateStudent(@RequestBody @Valid UpdateStudentRequest request) {
        updateStudentService.execute(request);
    }
}
