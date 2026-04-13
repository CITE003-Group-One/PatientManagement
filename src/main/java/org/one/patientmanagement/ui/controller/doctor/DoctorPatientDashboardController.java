/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import org.one.patientmanagement.domain.dto.ConsultationData;
import org.one.patientmanagement.domain.dto.PrescriptionData;
import org.one.patientmanagement.domain.enums.VitalsType;
import org.one.patientmanagement.domain.models.Consultation;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Prescription;
import org.one.patientmanagement.domain.models.Vitals;
import org.one.patientmanagement.service.ConsultationService;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.controller.AbstractComponentController;
import org.one.patientmanagement.ui.view.DoctorPatientDashboard;

/**
 *
 * @author KAROL JOHN
 */
public class DoctorPatientDashboardController extends AbstractComponentController<DoctorPatientDashboard, DoctorPatientDashboardController> {

    private final Doctor doctor;
    private final PatientManager patientManager;
    private Patient patient;
    private final ConsultationService consultationManager;
    private final DoctorManager doctorManager;
    private List<ConsultationData> consultations;

    @Inject
    public DoctorPatientDashboardController(Doctor doctor, PatientManager patientManager, ConsultationService consultationManager, DoctorManager doctorManager) {
        this.patientManager = patientManager;
        this.consultationManager = consultationManager;
        this.doctorManager = doctorManager;
        this.doctor = doctor;
    }

    // Called from parent controller
    public void setPatient(Patient patient) {
        this.patient = patient;
        patientManager.getVitals(patient).ifPresentOrElse(v -> view.setInfo(patient, v),
                () -> {
                    // TODO show dialog
                });
    }

    public void onVitalsEdit(VitalsType type, Consumer<Vitals> setVitals) {
        String vitalsName = switch (type) {
            case WEIGHT ->
                "Weight in kg";
            case HEART_RATE ->
                "Heart Rate";
            case BLOOD_PRESSURE ->
                "Blood Pressure";
            case TEMPERATURE ->
                "Temperature in celcius";
            default -> throw new IllegalStateException("Unexpected value: " + (type));
        };
        
    }

    public void onSearch(String query) {
        String q = query.toLowerCase().trim();

        var filtered = consultations.stream()
                .filter(c -> c.title() != null
                && c.title().toLowerCase().contains(q))
                .toList();

    }

    @Override
    public void onAttached() {
        List<Prescription> prescriptions = patientManager.getPrescriptions(patient);
        List<Consultation> consultations = consultationManager.getConsultations(patient.id());

        var doctorIds = prescriptions.stream()
                .map(Prescription::doctorId)
                .distinct()
                .toList();

        doctorIds.addAll(
                consultations.stream().map(Consultation::doctorId).toList()
        );

        var doctorMap = doctorManager.getAllByIds(doctorIds)
                .stream()
                .collect(Collectors.toMap(Doctor::id, d -> d));

        var prescriptionData = prescriptions.stream()
                .map(p -> {
                    var doctor = doctorMap.get(p.doctorId());

                    boolean isActive
                            = p.createdAt()
                                    .plus(p.duration())
                                    .isAfter(LocalDateTime.now());

                    return new PrescriptionData(
                            p.medicationName(),
                            doctor != null ? doctor.name() : "Unknown",
                            p.frequency(),
                            p.duration(),
                            isActive,
                            p.createdAt()
                    );
                })
                .toList();

        this.consultations = consultations.stream()
                .map(c -> {
                    var doctor = doctorMap.get(c.doctorId());

                    return new ConsultationData(
                            c.type(),
                            c.title(),
                            c.description(),
                            doctor != null ? doctor.name() : "Unknown",
                            c.createdAt()
                    );
                })
                .toList();

        view.loadConsultation(this.consultations);
        view.loadPrescription(prescriptionData);
    }

    // TODO show prescription add dialog
    public void onPrescriptionAdd() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // TODO show prescription add dialog
    public void onMedicalRecordAdd() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
