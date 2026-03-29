package org.one.patientmanagement.domain.enums;

public enum AppointmentBlock {
    MORNING("M"),
    AFTERNOON("A");
    
    private final String prefix;
    
    private AppointmentBlock(String prefix) {    
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return prefix;
    }
}
