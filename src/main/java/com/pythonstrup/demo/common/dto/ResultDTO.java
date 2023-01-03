package com.pythonstrup.demo.common.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ResultDTO<D> {
    private final String resultCode;
    private final String message;
    private final D data;
}
