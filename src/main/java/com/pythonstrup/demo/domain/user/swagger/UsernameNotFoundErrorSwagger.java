package com.pythonstrup.demo.domain.user.swagger;

import com.pythonstrup.demo.common.utils.message.ExceptionMessage;
import com.pythonstrup.demo.common.utils.message.ExceptionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsernameNotFoundErrorSwagger {
    @Schema(description = "에러 명칭", example = ExceptionStatus.USER_NOT_FOUND)
    private String status;

    @Schema(description = "상세 메시지", example = ExceptionMessage.USER_NOT_FOUND)
    private String message;

    @Schema(description = "에러 목록", example = "null", type="null")
    private List<String> errors;
}
