package com.project.scheduler.enums;

public enum ReportEnum {
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY"),
    YEARLY("YEARLY");
    ReportEnum(String frequencyValue){this.frequencyValue = frequencyValue;}
    private String frequencyValue;
    public String getFrequencyValue(){return frequencyValue;}
}
