package com.project.scheduler.dto.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultDTO<T> {

    private int code;
    private String status;
    private String message;
    private T content;
}
