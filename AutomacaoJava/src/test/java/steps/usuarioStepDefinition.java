package steps;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;


public class usuarioStepDefinition {
    private Map<String, String> expectedUser = new HashMap<>();
    String baseUrl = "https://petstore.swagger.io/v2/user";

    @Quando("eu faco um POST com os segunites valores:")
    public void euFacoUmPOSTComOsSegunitesValores(Map<String, String> user) {
        expectedUser = user;

        given().
                contentType(ContentType.JSON).
                body(user).
                when().post(baseUrl).
                then().
                contentType(ContentType.JSON).
                statusCode(200);
    }
    @Entao("quando faco um GET para {word}, o usuario criado nao e retornado")
    public void quandoFacoUmGETParaRafaelOUsuarioCriadoNaoERetornado(String endpoint) {
        when().get(baseUrl + endpoint).
                then().
                contentType(ContentType.JSON).
                statusCode(404).
                body("username", is(expectedUser.get("username")));
    }
}