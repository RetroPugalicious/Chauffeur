package com.rnb.chauffeur;

public class PlaceModal {

    // variables for our coursename,
    // description,tracks and duration,imageId.
    private String courseName;
    private String courseDuration;
    private String courseTracks;
    private String courseDescription;
    private String imgURL;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String url) {
        this.imgURL = url;
    }

    // creating getter and setter methods
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseTracks() {
        return courseTracks;
    }

    public void setCourseTracks(String courseTracks) {
        this.courseTracks = courseTracks;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    // constructor.
    public PlaceModal(String courseName, String courseDuration, String courseTracks, String courseDescription, String imgURL) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseTracks = courseTracks;
        this.courseDescription = courseDescription;
        this.imgURL = imgURL;
    }
}
