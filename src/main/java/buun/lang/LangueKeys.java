package buun.lang;

public enum LangueKeys {

    CATEGORY_INVALID_NAME("",""),
    CATEGORY_INVALID_PREFIX("", ""),
    CATEGORY_ALREADY_EXISTS("", "");

    private final String path;
    private final String defaultMessage;

    LangueKeys(String path, String defaultMessage){
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    public String getPath(){
        return this.path;
    }

    public String getDefaultMessage(){
        return this.defaultMessage;
    }
}
