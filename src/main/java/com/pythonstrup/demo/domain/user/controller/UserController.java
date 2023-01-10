package com.pythonstrup.demo.domain.user.controller;

import com.pythonstrup.demo.domain.user.dto.response.FindUserResponse;
import com.pythonstrup.demo.domain.user.dto.response.ResultFindUserReponse;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.service.UserService;
import com.pythonstrup.demo.domain.user.swagger.UsernameNotFoundErrorSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User API")
@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "find user by username", description = "유저이름으로 유저정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ResultFindUserReponse.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 유저 정보 입력",
                    content = {@Content(schema = @Schema(implementation = UsernameNotFoundErrorSwagger.class))})
    })
    @GetMapping("/user")
    public ResponseEntity<ResultFindUserReponse> findUserById(@Parameter @RequestParam Long userId) {
        User user = userService.findByUserId(userId);
        FindUserResponse data = FindUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
        ResultFindUserReponse response = new ResultFindUserReponse("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
