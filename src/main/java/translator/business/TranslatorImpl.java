package translator.business;

import  io.github.cdimascio.dotenv.Dotenv;
import translator.entities.Language;

public class TranslatorImpl implements Translator {

  private String apiKey;

  TranslatorImpl() {
    loadEnv(); 	
  }

  private void loadEnv() {
    Dotenv dotenv = Dotenv.load();
    apiKey = dotenv.get("API_KEY");
  }

  private String getRequestUrl(String text, String langFrom, String langTo) {
    return String.format(
        "https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s-%s",
        apiKey, text, langFrom, langTo);
  }

  public String translate(Language from, Language to, String text) {
    return "Hola Mundo";
  }
}
