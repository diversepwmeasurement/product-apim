/*
 * WSO2 API Manager - Publisher API
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Publisher**.  # Authentication The Publisher REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_publisher\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_publisher\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api123\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for publisher REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:api_view apim:api_create\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:api_create apim:api_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a09044034b5c3c1b01a9) 
 *
 * The version of the OpenAPI document: v2
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.publisher.api.v1;

import org.wso2.am.integration.clients.publisher.api.ApiCallback;
import org.wso2.am.integration.clients.publisher.api.ApiClient;
import org.wso2.am.integration.clients.publisher.api.ApiException;
import org.wso2.am.integration.clients.publisher.api.ApiResponse;
import org.wso2.am.integration.clients.publisher.api.Configuration;
import org.wso2.am.integration.clients.publisher.api.Pair;
import org.wso2.am.integration.clients.publisher.api.ProgressRequestBody;
import org.wso2.am.integration.clients.publisher.api.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.am.integration.clients.publisher.api.v1.dto.APIMonetizationInfoDTO;
import org.wso2.am.integration.clients.publisher.api.v1.dto.APIMonetizationUsageDTO;
import org.wso2.am.integration.clients.publisher.api.v1.dto.APIRevenueDTO;
import org.wso2.am.integration.clients.publisher.api.v1.dto.ErrorDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiMonetizationApi {
    private ApiClient localVarApiClient;

    public ApiMonetizationApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ApiMonetizationApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for addAPIMonetization
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param apIMonetizationInfoDTO Monetization data object (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK. Monetization status changed successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call addAPIMonetizationCall(String apiId, APIMonetizationInfoDTO apIMonetizationInfoDTO, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = apIMonetizationInfoDTO;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/monetize"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call addAPIMonetizationValidateBeforeCall(String apiId, APIMonetizationInfoDTO apIMonetizationInfoDTO, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling addAPIMonetization(Async)");
        }
        
        // verify the required parameter 'apIMonetizationInfoDTO' is set
        if (apIMonetizationInfoDTO == null) {
            throw new ApiException("Missing the required parameter 'apIMonetizationInfoDTO' when calling addAPIMonetization(Async)");
        }
        

        okhttp3.Call localVarCall = addAPIMonetizationCall(apiId, apIMonetizationInfoDTO, _callback);
        return localVarCall;

    }

    /**
     * Configure Monetization for a Given API
     * This operation can be used to configure monetization for a given API. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param apIMonetizationInfoDTO Monetization data object (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK. Monetization status changed successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public void addAPIMonetization(String apiId, APIMonetizationInfoDTO apIMonetizationInfoDTO) throws ApiException {
        addAPIMonetizationWithHttpInfo(apiId, apIMonetizationInfoDTO);
    }

    /**
     * Configure Monetization for a Given API
     * This operation can be used to configure monetization for a given API. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param apIMonetizationInfoDTO Monetization data object (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK. Monetization status changed successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> addAPIMonetizationWithHttpInfo(String apiId, APIMonetizationInfoDTO apIMonetizationInfoDTO) throws ApiException {
        okhttp3.Call localVarCall = addAPIMonetizationValidateBeforeCall(apiId, apIMonetizationInfoDTO, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Configure Monetization for a Given API (asynchronously)
     * This operation can be used to configure monetization for a given API. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param apIMonetizationInfoDTO Monetization data object (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK. Monetization status changed successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call addAPIMonetizationAsync(String apiId, APIMonetizationInfoDTO apIMonetizationInfoDTO, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = addAPIMonetizationValidateBeforeCall(apiId, apIMonetizationInfoDTO, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAPIMonetization
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Monetization status for each tier returned successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAPIMonetizationCall(String apiId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/monetization"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAPIMonetizationValidateBeforeCall(String apiId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling getAPIMonetization(Async)");
        }
        

        okhttp3.Call localVarCall = getAPIMonetizationCall(apiId, _callback);
        return localVarCall;

    }

    /**
     * Get Monetization Status for each Tier in a Given API
     * This operation can be used to get monetization status for each tier in a given API 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Monetization status for each tier returned successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public void getAPIMonetization(String apiId) throws ApiException {
        getAPIMonetizationWithHttpInfo(apiId);
    }

    /**
     * Get Monetization Status for each Tier in a Given API
     * This operation can be used to get monetization status for each tier in a given API 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Monetization status for each tier returned successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> getAPIMonetizationWithHttpInfo(String apiId) throws ApiException {
        okhttp3.Call localVarCall = getAPIMonetizationValidateBeforeCall(apiId, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Get Monetization Status for each Tier in a Given API (asynchronously)
     * This operation can be used to get monetization status for each tier in a given API 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Monetization status for each tier returned successfully.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAPIMonetizationAsync(String apiId, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAPIMonetizationValidateBeforeCall(apiId, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAPIRevenue
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a total revenue returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAPIRevenueCall(String apiId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/revenue"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAPIRevenueValidateBeforeCall(String apiId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling getAPIRevenue(Async)");
        }
        

        okhttp3.Call localVarCall = getAPIRevenueCall(apiId, _callback);
        return localVarCall;

    }

    /**
     * Get Total Revenue Details of a Given Monetized API with Meterd Billing
     * This operation can be used to get details of total revenue details of a given monetized API with meterd billing. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return APIRevenueDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a total revenue returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
     </table>
     */
    public APIRevenueDTO getAPIRevenue(String apiId) throws ApiException {
        ApiResponse<APIRevenueDTO> localVarResp = getAPIRevenueWithHttpInfo(apiId);
        return localVarResp.getData();
    }

    /**
     * Get Total Revenue Details of a Given Monetized API with Meterd Billing
     * This operation can be used to get details of total revenue details of a given monetized API with meterd billing. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return ApiResponse&lt;APIRevenueDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a total revenue returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<APIRevenueDTO> getAPIRevenueWithHttpInfo(String apiId) throws ApiException {
        okhttp3.Call localVarCall = getAPIRevenueValidateBeforeCall(apiId, null);
        Type localVarReturnType = new TypeToken<APIRevenueDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get Total Revenue Details of a Given Monetized API with Meterd Billing (asynchronously)
     * This operation can be used to get details of total revenue details of a given monetized API with meterd billing. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a total revenue returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAPIRevenueAsync(String apiId, final ApiCallback<APIRevenueDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAPIRevenueValidateBeforeCall(apiId, _callback);
        Type localVarReturnType = new TypeToken<APIRevenueDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSubscriptionUsage
     * @param subscriptionId Subscription Id  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a pending invoice returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested Subscription does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSubscriptionUsageCall(String subscriptionId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/subscriptions/{subscriptionId}/usage"
            .replaceAll("\\{" + "subscriptionId" + "\\}", localVarApiClient.escapeString(subscriptionId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getSubscriptionUsageValidateBeforeCall(String subscriptionId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'subscriptionId' is set
        if (subscriptionId == null) {
            throw new ApiException("Missing the required parameter 'subscriptionId' when calling getSubscriptionUsage(Async)");
        }
        

        okhttp3.Call localVarCall = getSubscriptionUsageCall(subscriptionId, _callback);
        return localVarCall;

    }

    /**
     * Get Details of a Pending Invoice for a Monetized Subscription with Metered Billing.
     * This operation can be used to get details of a pending invoice for a monetized subscription with meterd billing. 
     * @param subscriptionId Subscription Id  (required)
     * @return APIMonetizationUsageDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a pending invoice returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested Subscription does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public APIMonetizationUsageDTO getSubscriptionUsage(String subscriptionId) throws ApiException {
        ApiResponse<APIMonetizationUsageDTO> localVarResp = getSubscriptionUsageWithHttpInfo(subscriptionId);
        return localVarResp.getData();
    }

    /**
     * Get Details of a Pending Invoice for a Monetized Subscription with Metered Billing.
     * This operation can be used to get details of a pending invoice for a monetized subscription with meterd billing. 
     * @param subscriptionId Subscription Id  (required)
     * @return ApiResponse&lt;APIMonetizationUsageDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a pending invoice returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested Subscription does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<APIMonetizationUsageDTO> getSubscriptionUsageWithHttpInfo(String subscriptionId) throws ApiException {
        okhttp3.Call localVarCall = getSubscriptionUsageValidateBeforeCall(subscriptionId, null);
        Type localVarReturnType = new TypeToken<APIMonetizationUsageDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get Details of a Pending Invoice for a Monetized Subscription with Metered Billing. (asynchronously)
     * This operation can be used to get details of a pending invoice for a monetized subscription with meterd billing. 
     * @param subscriptionId Subscription Id  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Details of a pending invoice returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future). <br>  * Last-Modified - Date and time the resource has been modified the last time. Used by caches, or in conditional requests (Will be supported in future). <br>  * Content-Type - The content type of the body. <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested Subscription does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSubscriptionUsageAsync(String subscriptionId, final ApiCallback<APIMonetizationUsageDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSubscriptionUsageValidateBeforeCall(subscriptionId, _callback);
        Type localVarReturnType = new TypeToken<APIMonetizationUsageDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
