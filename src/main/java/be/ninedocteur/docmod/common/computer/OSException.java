package be.ninedocteur.docmod.common.computer;

public class OSException extends Exception{
    @Override
    public String getMessage() {
        return "This OS does not have a correct arch value. Please report this error to the author of the add-on.";
    }
}
