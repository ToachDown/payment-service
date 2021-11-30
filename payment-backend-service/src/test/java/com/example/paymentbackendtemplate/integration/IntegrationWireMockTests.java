package com.example.paymentbackendtemplate.integration;

import com.example.paymentbackendtemplate.helpers.JsonTestHelper;
import com.github.tomakehurst.wiremock.WireMockServer;
import net.minidev.json.parser.ParseException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationWireMockTests {

    CloseableHttpClient httpClient;

    WireMockServer server;

    @BeforeEach
    void init() {
        httpClient = HttpClients.createDefault();
        server = new WireMockServer();
        configureFor("localhost", 8080);
        server.start();
    }

    @AfterEach
    void endWork() {
        server.stop();
    }

    @Test
    void serverStartingTest() {
        assertTrue(server.isRunning());
    }

    @Test
    void statusTest() throws IOException {
        stubFor(post("/api/start-payment")
                .willReturn(ok()));

        HttpPost request = new HttpPost("http://localhost:8080/api/start-payment");
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
    }

    @Test
    void whenNotExistsUrlStatusTest() throws IOException {
        stubFor(post("/api/start-payment")
                .willReturn(ok()));

        HttpPost request = new HttpPost("http://localhost:8080/api/users");
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

    @Test
    void validStartPaymentTest() throws IOException, ParseException {
        stubFor(post("/api/start-payment")
                .withRequestBody(equalToJson(JsonTestHelper.getStartRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkApprovedResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getStartRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/start-payment");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: application/json");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(new BasicResponseHandler().handleResponse(httpResponse)).isEqualTo(JsonTestHelper.getOkApprovedResponseJson());
    }

    @Test
    void whenBrokenRequestBodyValidStartPaymentTest() throws IOException, ParseException {
        stubFor(post("/api/start-payment")
                .withRequestBody(equalToJson(JsonTestHelper.getStartRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkApprovedResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getStartBrokenRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/start-payment");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: text/plain");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

    @Test
    void validUpdatePaymentTest() throws IOException, ParseException {
        stubFor(post("/api/update-payment")
                .withRequestBody(equalToJson(JsonTestHelper.getUpdateRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getProcessingResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getUpdateRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/update-payment");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: application/json");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(new BasicResponseHandler().handleResponse(httpResponse)).isEqualTo(JsonTestHelper.getProcessingResponseJson());
    }

    @Test
    void whenBrokenRequestBodyValidUpdatePaymentTest() throws IOException, ParseException {
        stubFor(post("/api/update-payment")
                .withRequestBody(equalToJson(JsonTestHelper.getUpdateRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getProcessingResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getUpdateBrokenRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/update-payment");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: text/plain");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

    @Test
    void validCapturePaymentTest() throws IOException, ParseException {
        stubFor(post("/api/capture-payment")
                .withRequestBody(equalToJson(JsonTestHelper.getCaptureRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkFailureResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getCaptureRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/capture-payment");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: application/json");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(new BasicResponseHandler().handleResponse(httpResponse)).isEqualTo(JsonTestHelper.getOkFailureResponseJson());
    }

    @Test
    void whenBrokenRequestBodyValidCapturePaymentTest() throws IOException, ParseException {
        stubFor(post("/api/capture-payment")
                .withRequestBody(equalToJson(JsonTestHelper.getCaptureRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkFailureResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getCaptureBrokenRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/capture-payment");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: text/plain");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

    @Test
    void validRefundPaymentTest() throws IOException, ParseException {
        stubFor(post("/api/refund")
                .withRequestBody(equalToJson(JsonTestHelper.getRefundRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkDeclinedResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getRefundRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/refund");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: application/json");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(new BasicResponseHandler().handleResponse(httpResponse)).isEqualTo(JsonTestHelper.getOkDeclinedResponseJson());
    }

    @Test
    void whenBrokenRequestBodyValidRefundPaymentTest() throws IOException, ParseException {
        stubFor(post("/api/refund")
                .withRequestBody(equalToJson(JsonTestHelper.getRefundRequestJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkDeclinedResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getRefundBrokenRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/refund");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: text/plain");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

    @Test
    void validCancelPaymentTest() throws IOException, ParseException {
        stubFor(post("/api/cancel")
                .withRequestBody(equalToJson(JsonTestHelper.getCancelRequestJson()))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody(JsonTestHelper.getErrorResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getCancelRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/cancel");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: application/json");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(500);
    }

    @Test
    void whenBrokenRequestBodyValidCancelPaymentTest() throws IOException, ParseException {
        stubFor(post("/api/cancel")
                .withRequestBody(equalToJson(JsonTestHelper.getCancelRequestJson()))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody(JsonTestHelper.getErrorResponseJson())
                        .withHeader("Content-type", "application/json")));

        StringEntity stringEntity = new StringEntity(JsonTestHelper.getCancelBrokenRequestJson());
        HttpPost request = new HttpPost("http://localhost:8080/api/cancel");
        request.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: text/plain");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

    @Test
    void validGetPaymentTest() throws IOException, ParseException {
        stubFor(get("/api/bluecode/get-payment/61a2dd60-4f62-11ec-81d3-0242ac130003")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkDeclinedResponseJson())
                        .withHeader("Content-type", "application/json")));

        HttpGet request = new HttpGet("http://localhost:8080/api/bluecode/get-payment/61a2dd60-4f62-11ec-81d3-0242ac130003");
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: application/json");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(new BasicResponseHandler().handleResponse(httpResponse)).isEqualTo(JsonTestHelper.getOkDeclinedResponseJson());
    }

    @Test
    void whenInvalidIdGetPaymentTest() throws IOException, ParseException {
        stubFor(get("/api/bluecode/get-payment/61a2dd60-4f62-11ec-81d3-0242ac130003")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JsonTestHelper.getOkDeclinedResponseJson())
                        .withHeader("Content-type", "application/json")));

        HttpGet request = new HttpGet("http://localhost:8080/api/bluecode/get-payment/89a1dd60-4b6n-13ec-c1d3-0242ac134003");
        HttpResponse httpResponse = httpClient.execute(request);

        assertThat(httpResponse.getEntity().getContentType().toString()).isEqualTo("Content-Type: text/plain");
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }

}
