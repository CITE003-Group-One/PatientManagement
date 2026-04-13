/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.one.patientmanagement.ui.core.dto.ConsultationData;
import org.one.patientmanagement.ui.core.dto.PrescriptionData;
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
import org.one.patientmanagement.ui.core.dto.ConsultationInput;
import org.one.patientmanagement.ui.core.dto.VitalsData;
import org.one.patientmanagement.ui.model.DoctorViewModel;
import org.one.patientmanagement.ui.view.DoctorPatientDashboard;
import org.one.patientmanagement.ui.view.dialog.MedicalRecordDialogs;
import org.one.patientmanagement.ui.view.dialog.VitalsDialogs;

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
    public DoctorPatientDashboardController(DoctorViewModel model, PatientManager patientManager, ConsultationService consultationManager, DoctorManager doctorManager) {
        this.patientManager = patientManager;
        this.consultationManager = consultationManager;
        this.doctorManager = doctorManager;
        this.doctor = model.getDoctor();
    }

    // Called from parent controller
    public void setPatient(Patient patient) {
        this.patient = patient;
        patientManager.getVitals(patient).ifPresentOrElse(v -> view.setInfo(patient, v),
                () -> {
                    // TODO show dialog
                });
    }

    public void onVitalsEdit(VitalsType type, VitalsData vitalsData, Consumer<Vitals> setVitals) {
        String label = switch (type) {
            case WEIGHT ->
                "Weight";
            case HEART_RATE ->
                "Heart Rate";
            case BLOOD_PRESSURE ->
                "Blood Pressure";
            case TEMPERATURE ->
                "Temperature in celsius";
        };

        try {
            if (type == VitalsType.BLOOD_PRESSURE) {
                var input = VitalsDialogs.showInputDual(label,
                        String.valueOf(vitalsData.getSystolicBp()),
                        String.valueOf(vitalsData.getDiastolicBp()), view);
                vitalsData.setSystolicBp(Integer.valueOf(input.getFirst()));
                vitalsData.setDiastolicBp(Integer.valueOf(input.getSecond()));
            } else {
                var input = VitalsDialogs.showInputSingle(label, switch (type) {
                    case WEIGHT ->
                        String.valueOf(vitalsData.getWeight());
                    case HEART_RATE ->
                        String.valueOf(vitalsData.getHeartRate());
                    case TEMPERATURE ->
                        String.valueOf(vitalsData.getTemperature());
                    default ->
                        throw new AssertionError(type.name());
                }, view);
                switch (type) {
                    case WEIGHT ->
                        vitalsData.setWeight(Double.valueOf(input));
                    case HEART_RATE ->
                        vitalsData.setHeartRate(Integer.valueOf(input));
                    case TEMPERATURE ->
                        vitalsData.setTemperature(Double.valueOf(input));
                    default ->
                        throw new AssertionError(type.name());
                }
            }
        } catch (NumberFormatException e) {
            VitalsDialogs.showError(label, view);
            return;
        }

        setVitals.accept(vitalsData.convert());
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
                .sorted(Comparator.comparing(Prescription::createdAt).reversed())
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

        this.consultations = mapConsultations(consultations);
        view.loadConsultation(this.consultations);

        view.loadPrescription(prescriptionData);
    }

    private ConsultationData toConsultationData(Consultation c, Map<Long, Doctor> doctorMap) {
        var doc = doctorMap.get(c.doctorId());
        return new ConsultationData(
                c.type(),
                c.title(),
                c.description(),
                doc != null ? doc.name() : "Unknown",
                c.createdAt()
        );
    }

    private Map<Long, Doctor> buildDoctorMap(List<Long> ids) {
        return doctorManager.getAllByIds(ids).stream()
                .collect(Collectors.toMap(Doctor::id, d -> d));
    }

    private List<ConsultationData> mapConsultations(List<Consultation> consultations) {
        var doctorMap = buildDoctorMap(
                consultations.stream().map(Consultation::doctorId).distinct().toList()
        );
        return consultations.stream()
                .sorted(Comparator.comparing(Consultation::createdAt).reversed())
                .map(c -> toConsultationData(c, doctorMap))
                .toList();
    }

    public void onPrescriptionAdd() {
    // TODO show prescription add dialog
    }

    public void onMedicalRecordAdd() {
        ConsultationInput input = MedicalRecordDialogs.showConsultationInput(view);
        if (input == null) {
            return;
        }

        consultationManager.create(new Consultation(0L, input.type(), input.title(), input.description(), doctor.id(), patient.id(), LocalDateTime.now()));
        this.consultations = mapConsultations(consultationManager.getConsultations(patient.id()));
        view.loadConsultation(this.consultations);
    }

}
