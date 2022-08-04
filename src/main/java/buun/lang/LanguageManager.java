package buun.lang;

import buun.io.FileUtils;
import buun.user.User;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LanguageManager {

    private final Map<Locale, LanguageFile> languages;

    private final File languageDir;

    public LanguageManager(File pluginDir){
        this.languageDir = FileUtils.createFile(pluginDir, "languages");
        this.languages = loadLanguages();
    }

    private Map<Locale, LanguageFile> loadLanguages(){
        File[] contents = languageDir.listFiles();
        if(contents == null) return new HashMap<>();
        Map<Locale, LanguageFile> languageFileMap = new HashMap<>();
        Arrays.stream(contents).forEach(file -> {
            String languageName = file.getName().split("\\.")[0];
            Locale locale = Locale.forLanguageTag(languageName);
            languageFileMap.put(locale, new LanguageFile(locale, languageDir));
        });
        return languageFileMap;
    }

    public String getUserMessage(User user, LangueKeys key, Object...args){
        LanguageFile langFile = languages.get(user.getLanguage());
        if(langFile == null) return new MessageProcessor(key.getDefaultMessage())
                .setArguments(args)
                .replaceParagraphs()
                .replaceColor()
                .replacePaths()
                .getMessage();
        return langFile.getProcessedMessage(key, args);
    }

    public void sendUserMessage(User user, LangueKeys key, Object...args){
        user.sendMessage(getUserMessage(user, key, args));
    }



}
