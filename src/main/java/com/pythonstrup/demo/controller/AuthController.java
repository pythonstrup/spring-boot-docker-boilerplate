package com.pythonstrup.demo.controller;

import com.pythonstrup.demo.controller.dto.auth.request.SignupRequest;
import com.pythonstrup.demo.controller.dto.auth.response.SignupResponse;
import com.pythonstrup.demo.controller.dto.auth.response.result.ResultLoginResponse;
import com.pythonstrup.demo.controller.dto.auth.response.result.ResultSignupResponse;
import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "유저 정보를 받아 로그인을 진행")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ResultLoginResponse.class))}),
    })

    @PostMapping("signup")
    public ResponseEntity<ResultSignupResponse> signup(@Parameter @Valid @RequestBody SignupRequest signupRequest) {
        User user = authService.signup(signupRequest.toServiceDto());
        SignupResponse data = SignupResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
        ResultSignupResponse response = new ResultSignupResponse("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("login/success")
    public void loginSuccess() {}

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @PostMapping("login/fail")
    public void loginFailure() {}
}
