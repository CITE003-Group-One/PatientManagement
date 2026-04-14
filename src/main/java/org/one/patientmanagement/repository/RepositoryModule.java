package org.one.patientmanagement.repository;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.one.patientmanagement.repository.impl.AccountRepositoryImpl;
import org.one.patientmanagement.repository.impl.AppointmentRepositoryImpl;
import org.one.patientmanagement.repository.impl.AttachmentRepositoryImpl;
import org.one.patientmanagement.repository.impl.ConsultationRepositoryImpl;
import org.one.patientmanagement.repository.impl.DoctorRepositoryImpl;
import org.one.patientmanagement.repository.impl.PatientRepositoryImpl;
import org.one.patientmanagement.repository.impl.PrescriptionRepositoryImpl;
import org.one.patientmanagement.repository.impl.ScheduleRepositoryImpl;
import org.one.patientmanagement.repository.impl.VitalsRepositoryImpl;

public class RepositoryModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(AccountRepository.class).to(AccountRepositoryImpl.class).in(Singleton.class);
        bind(AttachmentRepository.class).to(AttachmentRepositoryImpl.class).in(Singleton.class);
        bind(AppointmentRepository.class).to(AppointmentRepositoryImpl.class).in(Singleton.class);
        bind(ConsultationRepository.class).to(ConsultationRepositoryImpl.class).in(Singleton.class);
        bind(PatientRepository.class).to(PatientRepositoryImpl.class).in(Singleton.class);
        bind(PrescriptionRepository.class).to(PrescriptionRepositoryImpl.class).in(Singleton.class);
        bind(VitalsRepository.class).to(VitalsRepositoryImpl.class).in(Singleton.class);
        bind(ScheduleRepository.class).to(ScheduleRepositoryImpl.class).in(Singleton.class);
        bind(DoctorRepository.class).to(DoctorRepositoryImpl.class).in(Singleton.class);
    }
}
