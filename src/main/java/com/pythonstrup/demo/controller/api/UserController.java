package com.pythonstrup.demo.controller.api;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.user.FindUserResponseDTO;
import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<ResultDTO<FindUserResponseDTO>> findUser(@RequestParam String username) {
        User user = userService.findByUsername(username);
        FindUserResponseDTO data = FindUserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
        ResultDTO<FindUserResponseDTO> response = ResultDTO.of("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
