package com.resume.data;

import java.util.ArrayList;
import java.util.List;

public class ResumeData {

    // ---------------- PERSONAL INFO ----------------
    private String fullName;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private String address;
    private String linkedin;
    private String github;
    private String summary;

    // NEW: PROFILE PHOTO
    private String photoPath;  // store file path of selected image


    // ---------------- EDUCATION ----------------
    private String degree;
    private String branch;
    private String college;
    private String startYear;
    private String endYear;
    private String cgpa;
    private String courses;

    // ---------------- SKILLS ----------------
    private String skills;

    // ---------------- EXPERIENCE ----------------
    private String expTitle;
    private String expCompany;
    private String expDuration;
    private String expDescription;


    // ---------------- PROJECTS ----------------
    public static class Project {
        public String title;
        public String description;
        public String tech;
        public String duration;

        public Project() {}

        public Project(String title, String description, String tech, String duration) {
            this.title = title;
            this.description = description;
            this.tech = tech;
            this.duration = duration;
        }
    }

    private List<Project> projects = new ArrayList<>();


    // ---------------- CERTIFICATIONS ----------------
    private List<String> certifications = new ArrayList<>();


    // ------------------------------------------------
    //               GETTERS + SETTERS
    // ------------------------------------------------

    // PERSONAL INFO
    public String getFullName() { return fullName; }
    public void setFullName(String x) { fullName = x; }

    public String getEmail() { return email; }
    public void setEmail(String x) { email = x; }

    public String getPhone() { return phone; }
    public void setPhone(String x) { phone = x; }

    public String getDob() { return dob; }
    public void setDob(String x) { dob = x; }

    public String getGender() { return gender; }
    public void setGender(String x) { gender = x; }

    public String getAddress() { return address; }
    public void setAddress(String x) { address = x; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String x) { linkedin = x; }

    public String getGithub() { return github; }
    public void setGithub(String x) { github = x; }

    public String getSummary() { return summary; }
    public void setSummary(String x) { summary = x; }


    // ---------------- PHOTO SUPPORT ----------------
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String path) {
        this.photoPath = path;
    }


    // EDUCATION
    public String getDegree() { return degree; }
    public void setDegree(String x) { degree = x; }

    public String getBranch() { return branch; }
    public void setBranch(String x) { branch = x; }

    public String getCollege() { return college; }
    public void setCollege(String x) { college = x; }

    public String getStartYear() { return startYear; }
    public void setStartYear(String x) { startYear = x; }

    public String getEndYear() { return endYear; }
    public void setEndYear(String x) { endYear = x; }

    public String getCgpa() { return cgpa; }
    public void setCgpa(String x) { cgpa = x; }

    public String getCourses() { return courses; }
    public void setCourses(String x) { courses = x; }


    // SKILLS
    public String getSkills() { return skills; }
    public void setSkills(String x) { skills = x; }


    // EXPERIENCE
    public String getExpTitle() { return expTitle; }
    public void setExpTitle(String x) { expTitle = x; }

    public String getExpCompany() { return expCompany; }
    public void setExpCompany(String x) { expCompany = x; }

    public String getExpDuration() { return expDuration; }
    public void setExpDuration(String x) { expDuration = x; }

    public String getExpDescription() { return expDescription; }
    public void setExpDescription(String x) { expDescription = x; }


    // ---------------- PROJECTS ----------------
    public void addProject(Project p) {
        projects.add(p);
    }

    public List<Project> getProjects() {
        return projects;
    }


    // ---------------- CERTIFICATIONS ----------------
    public void addCertification(String c) {
        certifications.add(c);
    }

    public void clearCertifications() {
        certifications.clear();
    }

    public List<String> getCertifications() {
        return certifications;
    }
}
