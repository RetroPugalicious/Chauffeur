package com.rnb.chauffeur;

public class PlaceModal {

    private String placeName;
    private String placeRating;
    private String placeReviewNum;
    private String placeDistance;
    private String placePrice;
    private String imgURL;
    private String phone;
    private String address;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String url) {
        this.imgURL = url;
    }

    // creating getter and setter methods
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceRating() {
        return placeRating;
    }

    public void setPlaceRating(String placeRating) {
        this.placeRating = placeRating;
    }

    public String getPlaceReviewNum() {
        return placeReviewNum;
    }

    public void setPlaceReviewNum(String placeReviewNum) {
        this.placeReviewNum = placeReviewNum;
    }

    public String getPlaceDistance() {
        return placeDistance;
    }

    public void setPlaceDistance(String placeDistance) {
        this.placeDistance = placeDistance;
    }

    public String getPlacePrice() {
        return placePrice;
    }

    public void setPlacePrice(String placePrice) {
        this.placePrice = placePrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // constructor.
    public PlaceModal(String placeName, String placeRating, String placeReviewNum, String placeDistance, String imgURL, String placePrice, String phone, String address) {
        this.placeName = placeName;
        this.placeRating = placeRating;
        this.placeReviewNum = placeReviewNum;
        this.placeDistance = placeDistance;
        this.imgURL = imgURL;
        this.placePrice = placePrice;
        this.phone = phone;
        this.address = address;
    }
}
