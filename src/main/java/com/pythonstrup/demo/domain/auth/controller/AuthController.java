package com.pythonstrup.demo.domain.auth.controller;

import com.pythonstrup.demo.domain.auth.dto.request.SignupRequest;
import com.pythonstrup.demo.domain.auth.dto.response.SignupResponse;
import com.pythonstrup.demo.domain.auth.dto.response.result.ResultSignupResponse;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "User API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", description = "유저 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ResultSignupResponse.class))}),
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
