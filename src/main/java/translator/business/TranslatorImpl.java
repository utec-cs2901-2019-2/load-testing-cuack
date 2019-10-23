package translator.business;

import  io.github.cdimascio.dotenv.Dotenv;
import translator.entities.Language;
import java.net.*;

public class TranslatorImpl implements Translator {

  private String apiKey;

  public TranslatorImpl() {
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

    public String preprocess(String text) {
        String[] tokens = text.split("\\s");
         for (int i = 0; i < tokens.length; i++) {
             tokens[i] = tokens[i].toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
         }
         return String.join(" ", tokens);
     }

  public String translate(Language from, Language to, String text) {
        try {
            URL url = new URL(getRequestUrl(text, from.getName(), to.getName()));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String response = con.getResponseMessage();
            return response;
        }
        catch(Exception e) {
            return "";
        }
  }
}
