package com.intuit.benten.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptException;
import java.io.InputStream;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@SpringBootTest(classes={HtmlToImageConverter.class})
public class HtmlToImageConverterTest {

    @Test
    public void testGenerateImage() throws ScriptException {
        HtmlToImageConverter.decorateHtml("helllo");
        InputStream image =  HtmlToImageConverter.generateImage("jnnkanknva");
        Assertions.assertNotNull(image);
    }

}
