package translator;

import  translator.business.Translator;
import translator.business.TranslatorImpl;
import translator.entities.Language;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TranslatorImplTest {
    Translator translator;
    Language from;
    Language to;

    @BeforeMethod
    public void setUp() throws Exception {
        translator = new TranslatorImpl();
        from = new Language("EN", "English");
        to = new Language("ES", "Spanish");
    }

    @Test(invocationCount = 100, threadPoolSize = 5)
    public void testTranslateDummy() throws Exception {
        String response = translator.translate(from, to, "Hello World");
        Assert.assertEquals(response, "Hola Mundo");
    }
    @Test(invocationCount = 100 ,threadPoolSize = 5)
    public void testTranslateSpaces() throws Exception{
        String response = translator.translate(from, to, "Hello    World");
        Assert.assertEquals(response, "Hola Mundo");

    }
    @Test(invocationCount = 100 ,threadPoolSize = 5)
    public void testTranslateMayus() throws Exception{
        String response = translator.translate(from, to, "HELLO WORLD");
        Assert.assertEquals(response, "HOLA MUNDO");
    }
    @Test(invocationCount = 100, threadPoolSize = 5)
    public void testTranslateQuotes() throws Exception {
        String response = translator.translate(from, to, "Hello \" World");
        Assert.assertEquals(response, "Hola Mundo");
    }

}
