package com.project.scheduler.dto.result;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> {

    private int code;
    private String status;
    private String message;
    private T content;
}
