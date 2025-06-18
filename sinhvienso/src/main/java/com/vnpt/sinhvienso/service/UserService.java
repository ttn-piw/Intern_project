package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.User;
import com.vnpt.sinhvienso.dto.request.LoginRequest;
import com.vnpt.sinhvienso.dto.response.UserResponse;
import com.vnpt.sinhvienso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    UserRepository userResponseRepository;

    public Page<UserResponse> getUsers(String key, Pageable pageable){
        // Mock user data
        List<User> mockUsers = List.of(
                new User("u01", "Trung", "trung@example.com", "pass1", "ADMIN", LocalDate.of(2000, 1, 1)),
                new User("u02", "Nguyen", "nguyen@example.com", "pass2", "USER", LocalDate.of(2001, 2, 2)),
                new User("u03", "Linh", "linh@example.com", "pass3", "USER", LocalDate.of(2002, 3, 3)),
                new User("u04", "Huy", "huy@example.com", "pass4", "MOD", LocalDate.of(1999, 4, 4)),
                new User("u05", "Minh", "minh@example.com", "pass5", "USER", LocalDate.of(1998, 5, 5)),
                new User("u06", "Tran", "trung@example.com", "pass1", "ADMIN", LocalDate.of(2000, 1, 1))
        );

        //DTO (User -> UserResponse)
        List<UserResponse> dtoUserToUserResponse = mockUsers.stream()
                .map(dto ->  new UserResponse(dto.getId(), dto.getName(), dto.getEmail()))
                .collect(toList());

        // Filter by key
        List<UserResponse> filterByKey = new ArrayList<>();
        if ((key == null) || (key.isBlank())){
            filterByKey = dtoUserToUserResponse;
        } else
            filterByKey = dtoUserToUserResponse
                    .stream()
                    .filter(name -> name.getName().toLowerCase().contains(key.toLowerCase()))
                    .collect(toList());

        //Take data with page size
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filterByKey.size());
        List<UserResponse> paged = (start > end) ? List.of() : filterByKey.subList(start, end);

        return //new PageImpl<>(paged);
                new PageImpl<>(paged, pageable, filterByKey.size());
    }
}
