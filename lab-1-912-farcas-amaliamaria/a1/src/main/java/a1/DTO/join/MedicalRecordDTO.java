package a1.DTO.join;

import a1.Domain.MedicalRecord;

import java.time.LocalDate;

public class MedicalRecordDTO{

    private Long id;
    private LocalDate dateOfBirth;
    private Integer age;
    private Integer weight;
    private boolean vaccine;
    private boolean specialCare;

    public MedicalRecordDTO(Long id, LocalDate dateOfBirth, Integer age, Integer weight, boolean vaccine, boolean specialCare) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        this.id = medicalRecord.getId();
        this.dateOfBirth = medicalRecord.getDateOfBirth();
        this.age = medicalRecord.getAge();
        this.weight = medicalRecord.getWeight();
        this.vaccine = medicalRecord.isVaccine();
        this.specialCare = medicalRecord.isSpecialCare();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isSpecialCare() {
        return specialCare;
    }

    public void setSpecialCare(boolean specialCare) {
        this.specialCare = specialCare;
    }

    @Override
    public String toString() {
        return "MedicalRecordDTO{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", weight=" + weight +
                ", vaccine=" + vaccine +
                ", specialCare=" + specialCare +
                '}';
    }
}
