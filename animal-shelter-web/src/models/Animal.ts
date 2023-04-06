import {MedicalRecord} from "./MedicalRecord";

export interface Animal{
    id:number;
    name:string;
    medicalRecord:MedicalRecord;
    microchipNumber:number;
    dayBroughtIn:string;
    dateAdopted: string;
    volunteersAssigned: {
        assignmentDay: string;
        Volunteer: number;
    }[];
}