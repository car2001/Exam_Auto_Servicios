package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class PetStoreStep {
    Response response;
    private String URL_BASE;

    public void definirURL(String url) {
        URL_BASE = url;
    }

    public void crearOrden(String idOrden, String idMascosta, String cantidad) {

        String requestBody = String.format("{\"id\": \"%s\", \"petId\": \"%s\", \"quantity\": \"%s\", \"status\": \"placed\"}",
                idOrden, idMascosta, cantidad);

        response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .post("/store/order")
                .then()
                .log().all()
                .extract().response();
    }

    public void validacionRespuesta(int statusCode) {
        Assert.assertEquals("Validacion de Respuesta", statusCode, response.statusCode());
    }

    public void validarEstadoOrden(String estadoOrden) {
        String estadoActual = response.jsonPath().getString("status");
        Assert.assertEquals("Validacion de Estado", estadoOrden, estadoActual);
    }

    public void validarCodigoRespuesta(String orderId){
        response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .pathParam("orderId", orderId)
                .log().all()
                .get("/store/order/{orderId}");
    }

    public void validarCodigoRespuesta(int statusCode) {
        Assert.assertEquals("Validacion de Respuesta", statusCode, response.statusCode());
    }

    public void validarIdOrden(String expectedOrderId) {
        String actualOrderId = response.jsonPath().getString("id");
        Assert.assertEquals("Valodacion orderID", expectedOrderId, actualOrderId);
    }

    public void validarPetIdOrden(String expectedPetId){
        String actualPetId = response.jsonPath().getString("petId");
        Assert.assertEquals("Valodacion petId", expectedPetId, actualPetId);
    }

    public void validarErrorResponse(String messageError){
        String mensajeErrorActual = response.jsonPath().getString("message");
        Assert.assertEquals("Validacion mensaje error", messageError, mensajeErrorActual);

    }
}
