package com.nttdata.glue;

import com.nttdata.steps.PetStoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class PetStoreStepDef {
    @Steps
    PetStoreStep tienda;

    @Given("la url es {string}")
    public void laUrlEs(String url) {
        tienda.definirURL(url);
    }

    @When("creo la orden de pedido mascota  con {string} con {string} y {string}")
    public void creoLaOrdenconIDYPetIDYCantidad(String idOrden, String idMascosta, String cantidad) {
        tienda.crearOrden(idOrden, idMascosta, cantidad);
    }

    @Then("valido el codigo de respuesa sea {int}")
    public void validoElCodigoDeRespuesaSea(int statusCode) {
       tienda.validacionRespuesta(statusCode);
    }

    @And("valido el estado de la orden sea {string}")
    public void validoElEstadoDelaOrden(String estadoOrden) {
        tienda.validarEstadoOrden(estadoOrden);
    }

    @When("consulto una orden con el {string}")
    public void  validarCodigoRespuesta(String orderId){
        tienda.validarCodigoRespuesta(orderId);
    }
    @Then("el código de respuesta es {int}")
    public void validarCodigoRespuesta(int status){
        tienda.validarCodigoRespuesta(status);
    }

    @And("el ID de la orden en la respuesta es {string}")
    public void validarIdOrden(String expectedOrderId){
        tienda.validarIdOrden(expectedOrderId);
    }

    @And("el petID de la orden es {string}")
    public  void validarPetIDOrden(String expectedPetId){
        tienda.validarPetIdOrden(expectedPetId);
    }

    @And("el mensaje de error en la respuesta es {string}")
    public void validarErrorResponse(String messageError){
        tienda.validarErrorResponse(messageError);
    }

}
