package a1.Controller;

import a1.DTO.update.MedicalRecordReadDTO;
import a1.DTO.join.MedicalRecordDTO;
import a1.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecordDTO> getMedicalRecords(){
        return medicalRecordService.getMedicalRecords();
    }

    @GetMapping(path = "{medicalRecordId}")
    public MedicalRecordDTO getMedicalRecordById(@PathVariable("medicalRecordId") Long medicalRecordId){
        return medicalRecordService.getMedicalRecordById(medicalRecordId);
    }

    @PostMapping
    public void addNewMedicalRecord(@RequestBody MedicalRecordDTO medicalRecord){
        medicalRecordService.addNewMedicalRecord(medicalRecord);
    }

    @DeleteMapping(path = "{medicalRecordId}")
    public void deleteMedicalRecord(@PathVariable("medicalRecordId") Long medicalRecordId){
        medicalRecordService.deleteMedicalRecord(medicalRecordId);
    }

    @PutMapping
    public void updateMedicalRecord(@RequestBody MedicalRecordReadDTO medicalRecord){
        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

}
