package com.pythonstrup.demo.domain.auth.controller;

import com.pythonstrup.demo.domain.auth.dto.request.SignupRequest;
import com.pythonstrup.demo.domain.auth.dto.response.SignupResponse;
import com.pythonstrup.demo.domain.auth.dto.response.result.ResultSignupResponse;
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
        SignupResponse data = authService.signup(signupRequest.toServiceDto());
        ResultSignupResponse response = new ResultSignupResponse("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "로그인", description = "유저 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "NO_CONTENT"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PostMapping("/login")
    public void login() {}
}
