package com.example.marek.frenchapp;

/**
 * Created by marek on 16.04.2018.
 */

public class Word {

    /**
     * @param NO_RESOURCES no image for word
     * @param englishTranslation english word
     * @param frenchTranslation  french transaltion for english word
     */

    public final static int NO_RESOURCES = -1;
    private String englishTranslation;
    private String frenchTranslation;
    private int imageResource = NO_RESOURCES;
    private int musicResources = NO_RESOURCES;

    public Word(String englishTranslation, String frenchTranslation) {
        this.englishTranslation = englishTranslation;
        this.frenchTranslation = frenchTranslation;

    }

    public Word(String englishTranslation, String frenchTranslation, int imageResource) {
        this.englishTranslation = englishTranslation;
        this.frenchTranslation = frenchTranslation;
        this.imageResource = imageResource;
    }

    public Word(String englishTranslation, String frenchTranslation, int imageResource, int musicResources) {
        this.englishTranslation = englishTranslation;
        this.frenchTranslation = frenchTranslation;
        this.imageResource = imageResource;
        this.musicResources = musicResources;
    }


    /**
     * return englishTranslation
     */
    public String getDefaultTranslation() {

        return englishTranslation;
    }

    /**
     * return frenchTransaltion
     */
    public String getFrenchTranslation() {
        return frenchTranslation;
    }

    public int getImageResourceId() {
        return imageResource;
    }

    public int getMusicResources() {
        return musicResources;
    }

    public boolean hasImage() {

        return imageResource != NO_RESOURCES;
    }

    public boolean hasMusic() {
        return musicResources != NO_RESOURCES;
    }

}
