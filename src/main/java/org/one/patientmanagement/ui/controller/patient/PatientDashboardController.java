/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.one.patientmanagement.ui.core.dto.ConsultationData;
import org.one.patientmanagement.domain.enums.ConsultationType;
import org.one.patientmanagement.domain.models.Consultation;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.service.AccountManager;
import org.one.patientmanagement.service.ConsultationService;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.PatientDashboard;

/**
 *
 * @author KAROL JOHN
 */
public class PatientDashboardController extends AbstractController<PatientDashboard, PatientDashboardController> {

    private final PatientViewModel model;
    private final ConsultationService consultation;
    private final DoctorManager doctor;
    private final PatientManager patient;
    private final AccountManager account;

    @Inject
    public PatientDashboardController(PatientDashboard view, PatientViewModel model, ConsultationService consultation, DoctorManager doctor, PatientManager patient, AccountManager account) {
        super(view);

        this.model = model;
        this.consultation = consultation;
        this.doctor = doctor;
        this.patient = patient;
        this.account = account;

        loadHistory();
        loadInfoBox();
        loadFields();
    }

    public void loadHistory() {
        var consultations = consultation.getConsultations(model.getPatient().id(), 0L, ConsultationType.GENERAL, ConsultationType.DIAGNOSIS);
        var doctorIds = consultations.stream()
                .map(Consultation::doctorId)
                .distinct()
                .toList();
        var doctorMap = doctor.getAllByIds(doctorIds)
                .stream()
                .collect(Collectors.toMap(Doctor::id, d -> d));
        var consultationData = consultations.stream()
                .map(c -> new ConsultationData(
                c.type(),
                c.title(),
                c.description(),
                doctorMap.get(c.doctorId()).name(),
                c.createdAt()
        )).toList();

        view.loadHistory(consultationData);
    }

    private void loadInfoBox() {
        Map<String, String> map = new LinkedHashMap<>();
        var patient = model.getPatient();

        map.put("First Name", patient.firstName());
        map.put("Last Name", patient.lastName());
        map.put("Sex", patient.sex());
        map.put("Age", String.valueOf(patient.getAge()));
        map.put("Birthday", patient.birthday().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        map.put("Address", patient.address());

        view.loadInfoBox(map);
    }

    private void loadFields() {
        
        patient.getById(model.getPatient().id()).ifPresent(p -> {
            account.getById(p.accountId()).ifPresent(a -> {
                view.loadFields(a.user(), p.contact());
            });
        });
        
        
    }

    public void onSave() {
        // TODO handle onsave in appointment
    }
}
