package buun.user;

import buun.BuildersUniverse;
import buun.lang.LangueKeys;

import java.util.Locale;

public interface User {

    boolean hasPermission(String permission);

    void sendMessage(String message);

    Locale getLanguage();

    default void sendMessage(LangueKeys key, Object...args){
        sendMessage(BuildersUniverse.getLanguageManager().getUserMessage(this, key, args));
    }

}
