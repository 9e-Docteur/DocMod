package be.ninedocteur.docmod.common.computer;

public class NullArchException extends Exception{
    @Override
    public String getMessage() {
        return "This OS have a null arch.";
    }
}
