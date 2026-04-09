package org.one.patientmanagement.repository;

import com.google.inject.AbstractModule;
import org.one.patientmanagement.repository.impl.AppointmentRepositoryImpl;
import org.one.patientmanagement.repository.impl.AttachmentRepositoryImpl;
import org.one.patientmanagement.repository.impl.ConsultationRepositoryImpl;
import org.one.patientmanagement.repository.impl.PatientRepositoryImpl;
import org.one.patientmanagement.repository.impl.PrescriptionRepositoryImpl;
import org.one.patientmanagement.repository.impl.VitalsRepositoryImpl;

public class RepositoryModule extends AbstractModule {
    
    @Override
    protected void configure() {
//        bind(AccountRepository.class);
        bind(AttachmentRepository.class).to(AttachmentRepositoryImpl.class);
        bind(AppointmentRepository.class).to(AppointmentRepositoryImpl.class);
        bind(ConsultationRepository.class).to(ConsultationRepositoryImpl.class);
        bind(PatientRepository.class).to(PatientRepositoryImpl.class);
        bind(PrescriptionRepository.class).to(PrescriptionRepositoryImpl.class);
        bind(VitalsRepository.class).to(VitalsRepositoryImpl.class);
    }
}
