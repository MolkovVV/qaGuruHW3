import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SelenideGithub {
    private String exampleExtendTestClass = "@ExtendWith({SoftAssertsExtension.class})\n" +
            "class Tests {\n" +
            "  @Test\n" +
            "  void test() {\n" +
            "    Configuration.assertionMode = SOFT;\n" +
            "    open(\"page.html\");\n" +
            "\n" +
            "    $(\"#first\").should(visible).click();\n" +
            "    $(\"#second\").should(visible).click();\n" +
            "  }\n" +
            "}" +
            "";

    private String exampleRegisterExtensionInsideTestClass = "class Tests {\n" +
            "  @RegisterExtension \n" +
            "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
            "\n" +
            "  @Test\n" +
            "  void test() {\n" +
            "    Configuration.assertionMode = SOFT;\n" +
            "    open(\"page.html\");\n" +
            "\n" +
            "    $(\"#first\").should(visible).click();\n" +
            "    $(\"#second\").should(visible).click();\n" +
            "  }\n" +
            "}";

    @BeforeEach
    public void setBasicConfiguration(){
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x10800";
    }

    @Test
    public void selenidePageWikiAssertion(){
        open("selenide/selenide");
        $("#wiki-tab").click();
        $("li.wiki-more-pages-link button").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $(byText("SoftAssertions")).click();

        String actualExtendTestClass =  $(byId("user-content-3-using-junit5-extend-test-class"))
                .ancestor("h4")
                .sibling(0)
                .$("pre")
                .getText();
        Assertions.assertEquals(exampleExtendTestClass ,actualExtendTestClass, "\"Using JUnit5 extend test class\" values is not correct");

        String actualRegisterExtensionInsideTestClass =  $(byId("user-content-3-using-junit5-extend-test-class"))
                .ancestor("h4")
                .sibling(2)
                .$("pre")
                .getText();
        Assertions.assertEquals(exampleRegisterExtensionInsideTestClass ,actualRegisterExtensionInsideTestClass, "\"register extension inside test class\" values is not correct");
    }
}
