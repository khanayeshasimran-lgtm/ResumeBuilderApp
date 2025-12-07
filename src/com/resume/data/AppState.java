package com.resume.data;

public class AppState {

    // A SINGLE shared ResumeData instance used by the whole app
    private static ResumeData resumeData = new ResumeData();

    public static ResumeData getResumeData() {
        return resumeData;
    }

    // If you ever want to reset everything
    public static void reset() {
        resumeData = new ResumeData();
    }
}
