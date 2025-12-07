# ResumeBuilderApp
A sleek Java desktop app for building professional resumes effortlessly.

# Overview
My ResumeBuilderApp is a Java Swing-based desktop application that allows users to:
Enter personal information
Add education, experience, projects, skills, and certifications
Generate a clean and structured PDF resume
View and manage data across multiple intuitive pages
Ideal for students, job seekers, or anyone looking to quickly craft a professional-looking resume.

# Features
Simple login system (username: ayesha, password: 1234)
Clean and modern GUI built with Java Swing
Sections: Personal Info, Education, Experience, Projects, Skills, Certifications, Summary
Generates downloadable PDF resume using iText

# App icon + branding integration

# Project Structure
ResumeBuilderApp/
├── src/                   # Source code
│   └── com/resume/…
├── bin/                   # Compiled class files
├── lib/                   # External JARs (e.g., iText PDF)
│   └── itextpdf-5.5.13.3.jar
├── assets/                # App icon and other images
│   └── app_icon.png
├── README.md              # Project README
└── Main.java              # App entry point

# How to Run
Clone the repo:
git clone https://github.com/your-username/ResumeBuilderApp.git
cd ResumeBuilderApp

# Compile:
javac -cp "lib/itextpdf-5.5.13.3.jar" -d bin $(find src -name "*.java")

# Run the app:
java -cp "bin:lib/itextpdf-5.5.13.3.jar" com.resume.Main
For Windows, use ; instead of : for classpath.

# Requirements
Java JDK 8 or higher
iTextPDF library (included in /lib)
A Java-friendly terminal (or IDE like IntelliJ/Eclipse/VS Code)

# License
This project is open-source and free to use for educational and personal purposes.
