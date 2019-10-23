package translator.business;

import io.github.cdimascio.dotenv.Dotenv;
import java.net.*;
import translator.entities.Language;

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

  public String getJSON(URL url) {
    try {
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.connect();
      int status = con.getResponseCode();
      switch (status) {
        case 200:
        case 201:
          BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
          StringBuilder sb = new StringBuilder();
          String line;
          while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
          }
          br.close();
          return sb.toString();
      }
    } catch (Exception e) {
      return "";
    }
  }

  public String translate(Language from, Language to, String text) {
    try {
      URL url = new URL(getRequestUrl(text, from.getName(), to.getName()));
      String data = getJSON(url);
      return data;

    } catch (Exception e) {
      return "";
    }
  }
}
