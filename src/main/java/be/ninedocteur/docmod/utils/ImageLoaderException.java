package be.ninedocteur.docmod.utils;

public class ImageLoaderException extends Exception{
    @Override
    public String getMessage() {
        return "Image is null. Can't get resolution.";
    }
}
