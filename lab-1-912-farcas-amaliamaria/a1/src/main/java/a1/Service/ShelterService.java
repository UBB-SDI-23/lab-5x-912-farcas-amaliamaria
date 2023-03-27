package a1.Service;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getIndividual.ShelterListDTO;
import a1.DTO.join.ShelterDTO;
import a1.DTO.reports.AverageAgeVolunteersShelterDTO;
import a1.DTO.reports.VaccinatedAnimalsRatioSheltersDTO;
import a1.Domain.Shelter;
import a1.Repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ShelterService implements IShelterService{

    private final ShelterRepository shelterRepository;

    @Autowired
    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public List<ShelterIdDTO> getShelters() {
        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findAll()){
            shelters.add(new ShelterIdDTO(a));
        }

        return shelters;
    }

    /*public List<Optional<Shelter>> getSheltersFilter(String name, String city) {
        if(name!=null){
            return Collections.singletonList(shelterRepository.findShelterByName(name));
        }
        if(city!=null){
            return Collections.singletonList(shelterRepository.findShelterByCity(city));
        }
        return null;
    }*/

    public ShelterListDTO getShelterById(Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElseThrow(()->new IllegalStateException("shelter with id" + shelterId + " does not exist"));

        ShelterListDTO shelterDTO = new ShelterListDTO(shelter);

        return shelterDTO;
    }

    public void addNewShelter(ShelterDTO shelterDTO) {
        Optional<Shelter> shelterOptional = shelterRepository.findShelterByPhoneNumber(shelterDTO.getPhoneNumber());
        if(shelterOptional.isPresent()){
            throw new IllegalStateException("Phone number already exists");
        }

        shelterOptional = shelterRepository.findShelterByPostalCode(shelterDTO.getPostalCode());
        if(shelterOptional.isPresent()){
            throw new IllegalStateException("Postal code already exists");
        }

        if(shelterDTO.getPhoneNumber().toString().length()!=11){
            throw new IllegalStateException("Invalid phone number");
        }

        if(shelterDTO.getCapacity()<=0){
            throw new IllegalStateException("Invalid capacity");
        }

        shelterRepository.save(new Shelter(shelterDTO.getName(), shelterDTO.getCity(), shelterDTO.getPostalCode(), shelterDTO.getPhoneNumber(), shelterDTO.getCapacity()));
    }

    public void deleteShelter(Long shelterId) {
        boolean exists = shelterRepository.existsById(shelterId);
        if(!exists){
            throw  new IllegalStateException("shelter with id" + shelterId + " does not exist");
        }
        shelterRepository.deleteById(shelterId);
    }

    public void updateShelter(ShelterDTO shelterDTO) {
        Shelter updateShelter = shelterRepository.findById(shelterDTO.getId()).orElseThrow(() -> new IllegalStateException("shelter with id" + shelterDTO.getId() + "does not exist"));

        if(shelterDTO.getName()!=null && shelterDTO.getName().length()>0){
            updateShelter.setName(shelterDTO.getName());
        }

        if(shelterDTO.getCity()!=null && shelterDTO.getCity().length()>0){
            updateShelter.setCity(shelterDTO.getCity());
        }

        if(shelterDTO.getCapacity()>0){
            updateShelter.setCapacity(shelterDTO.getCapacity());
        }

        if(shelterDTO.getPhoneNumber()!=null && shelterDTO.getPhoneNumber().toString().length()==11){
            updateShelter.setPhoneNumber(shelterDTO.getPhoneNumber());
        }

        if(shelterDTO.getPostalCode()!=null && shelterDTO.getPostalCode().toString().length()==6){
            updateShelter.setPostalCode(shelterDTO.getPostalCode());
        }

        shelterRepository.save(updateShelter);
    }

    public List<ShelterIdDTO> getSheltersGreaterCapacity(Integer capacity) {
        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findAll()){
            if(a.getCapacity()>capacity) {
                shelters.add(new ShelterIdDTO(a));
            }
        }

        return shelters;
    }

/*    public String exportReport(String reportFormat, List<IDTO> list, String name) throws FileNotFoundException, JRException {
        List<AverageAgeVolunteersShelterDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll()){
            averageList.add(new AverageAgeVolunteersShelterDTO(s));
        }

        File file = ResourceUtils.getFile("classpath:" + name+".jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created by", "Ama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Ama"+"\\" + name + ".html");
        }

        return "report generated in path C:\\Users\\Ama";
    }*/

    public List<AverageAgeVolunteersShelterDTO> exportReportVolunteer() {
        List<AverageAgeVolunteersShelterDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll()){
            averageList.add(new AverageAgeVolunteersShelterDTO(s));
        }

        return averageList;
/*        File file = ResourceUtils.getFile("classpath:volunteer.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(averageList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created by", "Ama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Ama"+"\\volunteers.html");
        }

        return jasperPrint;*/
    }

    public List<VaccinatedAnimalsRatioSheltersDTO> exportReportAnimal(){
        List<VaccinatedAnimalsRatioSheltersDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll()){
            averageList.add(new VaccinatedAnimalsRatioSheltersDTO(s));
        }
/*
        File file = ResourceUtils.getFile("classpath:animalVaccine.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(averageList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created by", "Ama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Ama"+"\\animalVaccine.html");
        }*/

        return averageList;
    }
}
