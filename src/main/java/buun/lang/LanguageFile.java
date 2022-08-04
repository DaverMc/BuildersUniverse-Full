package buun.lang;

import buun.io.PropertiesFile;

import java.io.File;
import java.util.Locale;

public class LanguageFile extends PropertiesFile {

    public LanguageFile(Locale language, File dir){
        super(dir, language.getLanguage());
    }

    public String getProcessedMessage(LangueKeys key, Object...args){
        String rawMessage = getRawMessage(key);
        if(rawMessage == null) rawMessage = key.getDefaultMessage();
        return new MessageProcessor(rawMessage)
                .setArguments(args)
                .replacePaths()
                .replaceColor()
                .replaceParagraphs()
                .getMessage();
    }

    public String getRawMessage(LangueKeys key){
        return getValue(key.getPath());
    }
}
