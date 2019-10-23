package calculator.business;

import calculator.entities.Language;
import io.github.cdimascio.dotenv.Dotenv;

public class TranslatorImpl implements Translator {

	private String apiKey;

	public void loadEnv() {
		Dotenv dotenv = Dotenv.load();
		apiKey = dotenv.get("API_KEY");
	}

    public String preprocess(String text) {
        String[] tokens = text.split("\\s");
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        }
        return String.join(" ", tokens);
    }

    @Override
    public String translate(Language from, Language to, String text) {
    }
}
