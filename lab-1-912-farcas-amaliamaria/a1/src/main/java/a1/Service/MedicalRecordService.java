package a1.Service;

import a1.DTO.update.MedicalRecordReadDTO;
import a1.DTO.join.MedicalRecordDTO;
import a1.Domain.MedicalRecord;
import a1.Repository.MedicalRecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService{
    @PersistenceContext
    private EntityManager entityManager;

    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecordDTO> getMedicalRecords() {
        List<MedicalRecordDTO> medicalRecords = new ArrayList<>();
        for(MedicalRecord a: medicalRecordRepository.findAll()){
            medicalRecords.add(new MedicalRecordDTO(a));
        }

        return medicalRecords;
    }

    public MedicalRecordDTO getMedicalRecordById(Long medicalRecordId) {
        boolean exists = medicalRecordRepository.existsById(medicalRecordId);
        if(!exists){
            throw new IllegalStateException("No medical records with id "+ medicalRecordId);
        }
        return new MedicalRecordDTO(medicalRecordRepository.getReferenceById(medicalRecordId));
    }


    public void addNewMedicalRecord(MedicalRecordDTO medicalRecordDTO) {

        MedicalRecord medicalRecord = new MedicalRecord(medicalRecordDTO.getDateOfBirth(), medicalRecordDTO.getWeight(), medicalRecordDTO.isVaccine(), medicalRecordDTO.isSpecialCare());

        medicalRecordRepository.save(medicalRecord);
    }

    public void deleteMedicalRecord(Long medicalRecordId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(medicalRecordId).orElseThrow(()-> new IllegalStateException("No medical records with id "+ medicalRecordId));

        medicalRecordRepository.delete(medicalRecord);
    }


    public void updateMedicalRecord(MedicalRecordReadDTO medicalRecordDTO) {

        MedicalRecord medicalRecord = entityManager.find(MedicalRecord.class, medicalRecordDTO.getId());
        if (medicalRecord == null) {
            throw new IllegalStateException("Medical record with ID " + medicalRecord.getId() + " not found");
        }

        if(medicalRecordDTO.getWeight()!=null && medicalRecordDTO.getWeight()>0){
            medicalRecord.setWeight(medicalRecordDTO.getWeight());
        }

        if(medicalRecordDTO.getDateOfBirth()!=null){
            medicalRecord.setDateOfBirth(medicalRecordDTO.getDateOfBirth());
        }

        if(medicalRecordDTO.getSpecialCare()!=null){
            if(medicalRecordDTO.getSpecialCare()==-1) {
                medicalRecord.setSpecialCare(false);
            }
            else{
                medicalRecord.setSpecialCare(true);
            }
        }

        if(medicalRecordDTO.getVaccine()!=null){
            if(medicalRecordDTO.getVaccine()==-1) {
                medicalRecord.setVaccine(false);
            }
            else{
                medicalRecord.setVaccine(true);
            }
        }

        medicalRecordRepository.save(medicalRecord);
    }
}
