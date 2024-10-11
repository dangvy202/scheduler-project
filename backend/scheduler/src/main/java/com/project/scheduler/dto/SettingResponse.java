package com.project.scheduler.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingResponse{
    UserSettingResponse userSetting;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder
    public static class UserSettingResponse {
        private int dayOfMonth;
        private String dayOfWeek;
        private String frequency;
        private String timeOfDay;
        private UserResponse user;
        private ReportResponse report;

        @Data
        @FieldDefaults(level = AccessLevel.PRIVATE)
        @Builder
        public static class UserResponse {
            private String email;
            private String name;
        }

        @Data
        @FieldDefaults(level = AccessLevel.PRIVATE)
        @Builder
        public static class ReportResponse {
            private String reportName;
            private String description;
        }
    }
}
