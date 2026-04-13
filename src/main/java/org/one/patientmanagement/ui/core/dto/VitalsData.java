/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.core.dto;

import org.one.patientmanagement.domain.models.Vitals;

public class VitalsData {

    private Integer systolicBp;
    private Integer diastolicBp;
    private Integer heartRate;
    private Double temperature;
    private Double weight;
    private Double height;
    private final Vitals model;

    public VitalsData(Vitals v) {
        this.model = v;
        this.systolicBp = v.systolicBp();
        this.diastolicBp = v.diastolicBp();
        this.heartRate = v.heartRate();
        this.temperature = v.temperature();
        this.weight = v.weight();
        this.height = v.weight();
    }

    public Integer getSystolicBp() {
        return systolicBp;
    }

    public void setSystolicBp(Integer systolicBp) {
        this.systolicBp = systolicBp;
    }

    public Integer getDiastolicBp() {
        return diastolicBp;
    }

    public void setDiastolicBp(Integer diastolicBp) {
        this.diastolicBp = diastolicBp;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Vitals convert() {
        return new Vitals(
                model.id(),
                systolicBp,
                diastolicBp,
                heartRate,
                temperature,
                weight,
                height,
                model.patientId(),
                model.recordedAt()
        );
    }
}
