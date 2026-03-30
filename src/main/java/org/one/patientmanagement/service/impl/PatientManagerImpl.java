package org.one.patientmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.one.patientmanagement.domain.models.Attachment;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Prescription;
import org.one.patientmanagement.domain.models.Vitals;
import org.one.patientmanagement.repository.AttachmentRepository;
import org.one.patientmanagement.repository.PatientRepository;
import org.one.patientmanagement.repository.VitalsRepository;
import org.one.patientmanagement.service.PatientManager;

public class PatientManagerImpl implements PatientManager {
	
	private final PatientRepository repo;
	private final VitalsRepository vitalsRepo;
	private AttachmentRepository attachRepo;
	
	public PatientManagerImpl(PatientRepository repo, VitalsRepository vitalsRepo, AttachmentRepository attachRepo) {
		this.repo = repo;
		this.vitalsRepo = vitalsRepo;
		this.attachRepo = attachRepo;
	}

	@Override
	public Patient create(Patient patient) {
		return repo.save(patient);
	}

	@Override
	public void remove(Patient patient) {
		// TODO Auto-generated method stub
	}

	@Override
	public Patient update(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attachment> getAttachments(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Vitals> getVitals(Patient patient) {
		return null;
	}

	@Override
	public List<Prescription> getPrescriptions(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Patient> getById(long patientId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Patient> getByAccountId(long accountId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getDoctors(long patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prescription recordPrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vitals setVitals(Vitals vitals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attachment addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		
	}
	
}
