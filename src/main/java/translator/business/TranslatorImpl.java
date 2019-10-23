package translator.business;

import translator.entities.Language;
import io.github.cdimascio.dotenv.Dotenv;

public class TranslatorImpl implements Translator {

	private String apiKey;

	public void loadEnv() {
		Dotenv dotenv = Dotenv.load();
		apiKey = dotenv.get("API_KEY");
	}

    @Override
    public String translate(Language from, Language to, String text) {
        return "Hola Mundo";
    }
}
