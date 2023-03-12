package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.TeacherRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateTeacherRequest;
import com.soogung.simblue.domain.user.presentation.dto.response.TeacherResponse;
import com.soogung.simblue.domain.user.service.JoinTeacherService;
import com.soogung.simblue.domain.user.service.SearchTeacherService;
import com.soogung.simblue.domain.user.service.UpdateTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final SearchTeacherService searchTeacherService;
    private final JoinTeacherService joinTeacherService;
    private final UpdateTeacherService updateTeacherService;

    @GetMapping("/search")
    public List<TeacherResponse> searchTeacher(@RequestParam(name = "q") String q) {
        return searchTeacherService.execute(q);
    }

    @PostMapping
    public void joinTeacher(@RequestBody @Valid TeacherRequest request) {
        joinTeacherService.execute(request);
    }

    @PutMapping
    public void updateTeacher(@RequestBody @Valid UpdateTeacherRequest request) {
        updateTeacherService.execute(request);
    }
}
