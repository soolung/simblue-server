package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.repository.TeacherRepository;
import com.soogung.simblue.domain.user.presentation.dto.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchTeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherResponse> execute(String q) {
        return teacherRepository.searchTeacherByName(q)
                .stream().map(TeacherResponse::of)
                .collect(Collectors.toList());
    }
}
