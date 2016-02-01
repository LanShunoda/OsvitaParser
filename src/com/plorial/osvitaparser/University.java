package com.plorial.osvitaparser;

/**
 * Created by plorial on 01.02.16.
 */
public class University {
    public final String name;
    public final String city;

    public University(String name, String city) {
        this.name = name;
        this.city = city;
    }

    private String yearOfFoundation;
    private String status;
    private String accreditation;
    private String documentAfterFinish;
    private String formOfEducation;
    private String qualificationLevels;
    private String address;
    private String telephone;
    private String telephoneOfSelectionCommittee;
    private String universitySite;

    private String[] trainingAreas;

    public String getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(String yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getDocumentAfterFinish() {
        return documentAfterFinish;
    }

    public void setDocumentAfterFinish(String documentAfterFinish) {
        this.documentAfterFinish = documentAfterFinish;
    }

    public String getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(String formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public String getQualificationLevels() {
        return qualificationLevels;
    }

    public void setQualificationLevels(String qualificationLevels) {
        this.qualificationLevels = qualificationLevels;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephoneOfSelectionCommittee() {
        return telephoneOfSelectionCommittee;
    }

    public void setTelephoneOfSelectionCommittee(String telephoneOfSelectionCommittee) {
        this.telephoneOfSelectionCommittee = telephoneOfSelectionCommittee;
    }

    public String getUniversitySite() {
        return universitySite;
    }

    public void setUniversitySite(String universitySite) {
        this.universitySite = universitySite;
    }

    public String[] getTrainingAreas() {
        return trainingAreas;
    }

    public void setTrainingAreas(String[] trainingAreas) {
        this.trainingAreas = trainingAreas;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append("\n");
        builder.append(city);
        builder.append("\n");
        builder.append(getAccreditation());
        builder.append("\n");
        builder.append(getAddress());
        builder.append("\n");
        builder.append(getDocumentAfterFinish());
        builder.append("\n");
        builder.append(getFormOfEducation());
        builder.append("\n");
        builder.append(getQualificationLevels());
        builder.append("\n");
        builder.append(getStatus());
        builder.append("\n");
        builder.append(getTelephone());
        builder.append("\n");
        builder.append(getTelephoneOfSelectionCommittee());
        builder.append("\n");
        builder.append(getYearOfFoundation());
        builder.append("\n");
        builder.append(getUniversitySite());

        return builder.toString();
    }
}
