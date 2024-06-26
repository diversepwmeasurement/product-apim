/*
 * Service Catalog APIs
 * This specifies a **RESTful API** for Service Catalog.  # Authentication Our REST APIs are protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_service_catalog\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_service_catalog\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api123\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for Service Catalog REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd>&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=service_catalog:service_view service_catalog:service_write\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"service_catalog:service_view service_catalog:service_write\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change. 
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.service.catalog.api.v1.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* ServiceDTO
*/

public class ServiceDTO {
        public static final String SERIALIZED_NAME_ID = "id";
        @SerializedName(SERIALIZED_NAME_ID)
            private String id;

        public static final String SERIALIZED_NAME_NAME = "name";
        @SerializedName(SERIALIZED_NAME_NAME)
            private String name;

        public static final String SERIALIZED_NAME_DESCRIPTION = "description";
        @SerializedName(SERIALIZED_NAME_DESCRIPTION)
            private String description;

        public static final String SERIALIZED_NAME_VERSION = "version";
        @SerializedName(SERIALIZED_NAME_VERSION)
            private String version;

        public static final String SERIALIZED_NAME_SERVICE_KEY = "serviceKey";
        @SerializedName(SERIALIZED_NAME_SERVICE_KEY)
            private String serviceKey;

        public static final String SERIALIZED_NAME_SERVICE_URL = "serviceUrl";
        @SerializedName(SERIALIZED_NAME_SERVICE_URL)
            private String serviceUrl;

            /**
* The type of the provided API definition
*/
    @JsonAdapter(DefinitionTypeEnum.Adapter.class)
public enum DefinitionTypeEnum {
        OAS2("OAS2"),
        
        OAS3("OAS3"),
        
        WSDL1("WSDL1"),
        
        WSDL2("WSDL2"),
        
        GRAPHQL_SDL("GRAPHQL_SDL"),
        
        ASYNC_API("ASYNC_API");

private String value;

DefinitionTypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static DefinitionTypeEnum fromValue(String value) {
    for (DefinitionTypeEnum b : DefinitionTypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<DefinitionTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final DefinitionTypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public DefinitionTypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return DefinitionTypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_DEFINITION_TYPE = "definitionType";
        @SerializedName(SERIALIZED_NAME_DEFINITION_TYPE)
            private DefinitionTypeEnum definitionType;

            /**
* The security type of the endpoint
*/
    @JsonAdapter(SecurityTypeEnum.Adapter.class)
public enum SecurityTypeEnum {
        BASIC("BASIC"),
        
        DIGEST("DIGEST"),
        
        OAUTH2("OAUTH2"),
        
        X509("X509"),
        
        API_KEY("API_KEY"),
        
        NONE("NONE");

private String value;

SecurityTypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static SecurityTypeEnum fromValue(String value) {
    for (SecurityTypeEnum b : SecurityTypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<SecurityTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final SecurityTypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public SecurityTypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return SecurityTypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_SECURITY_TYPE = "securityType";
        @SerializedName(SERIALIZED_NAME_SECURITY_TYPE)
            private SecurityTypeEnum securityType = SecurityTypeEnum.NONE;

        public static final String SERIALIZED_NAME_MUTUAL_S_S_L_ENABLED = "mutualSSLEnabled";
        @SerializedName(SERIALIZED_NAME_MUTUAL_S_S_L_ENABLED)
            private Boolean mutualSSLEnabled = false;

        public static final String SERIALIZED_NAME_USAGE = "usage";
        @SerializedName(SERIALIZED_NAME_USAGE)
            private Integer usage;

        public static final String SERIALIZED_NAME_CREATED_TIME = "createdTime";
        @SerializedName(SERIALIZED_NAME_CREATED_TIME)
            private String createdTime;

        public static final String SERIALIZED_NAME_LAST_UPDATED_TIME = "lastUpdatedTime";
        @SerializedName(SERIALIZED_NAME_LAST_UPDATED_TIME)
            private String lastUpdatedTime;

        public static final String SERIALIZED_NAME_MD5 = "md5";
        @SerializedName(SERIALIZED_NAME_MD5)
            private String md5;

        public static final String SERIALIZED_NAME_DEFINITION_URL = "definitionUrl";
        @SerializedName(SERIALIZED_NAME_DEFINITION_URL)
            private String definitionUrl;


        public ServiceDTO id(String id) {
        
        this.id = id;
        return this;
        }

    /**
        * Get id
    * @return id
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "01234567-0123-0123-0123-012345678901", value = "")
    
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


        public ServiceDTO name(String name) {
        
        this.name = name;
        return this;
        }

    /**
        * Get name
    * @return name
    **/
      @ApiModelProperty(example = "Pizzashack-Endpoint", required = true, value = "")
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


        public ServiceDTO description(String description) {
        
        this.description = description;
        return this;
        }

    /**
        * Get description
    * @return description
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "A Catalog Entry that exposes a REST endpoint", value = "")
    
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


        public ServiceDTO version(String version) {
        
        this.version = version;
        return this;
        }

    /**
        * Get version
    * @return version
    **/
      @ApiModelProperty(example = "v1", required = true, value = "")
    
    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


        public ServiceDTO serviceKey(String serviceKey) {
        
        this.serviceKey = serviceKey;
        return this;
        }

    /**
        * Get serviceKey
    * @return serviceKey
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "Pizzashack-Endpoint-1.0.0", value = "")
    
    public String getServiceKey() {
        return serviceKey;
    }


    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }


        public ServiceDTO serviceUrl(String serviceUrl) {
        
        this.serviceUrl = serviceUrl;
        return this;
        }

    /**
        * Get serviceUrl
    * @return serviceUrl
    **/
      @ApiModelProperty(example = "http://localhost/pizzashack", required = true, value = "")
    
    public String getServiceUrl() {
        return serviceUrl;
    }


    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }


        public ServiceDTO definitionType(DefinitionTypeEnum definitionType) {
        
        this.definitionType = definitionType;
        return this;
        }

    /**
        * The type of the provided API definition
    * @return definitionType
    **/
      @ApiModelProperty(example = "OAS3", required = true, value = "The type of the provided API definition")
    
    public DefinitionTypeEnum getDefinitionType() {
        return definitionType;
    }


    public void setDefinitionType(DefinitionTypeEnum definitionType) {
        this.definitionType = definitionType;
    }


        public ServiceDTO securityType(SecurityTypeEnum securityType) {
        
        this.securityType = securityType;
        return this;
        }

    /**
        * The security type of the endpoint
    * @return securityType
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "BASIC", value = "The security type of the endpoint")
    
    public SecurityTypeEnum getSecurityType() {
        return securityType;
    }


    public void setSecurityType(SecurityTypeEnum securityType) {
        this.securityType = securityType;
    }


        public ServiceDTO mutualSSLEnabled(Boolean mutualSSLEnabled) {
        
        this.mutualSSLEnabled = mutualSSLEnabled;
        return this;
        }

    /**
        * Whether Mutual SSL is enabled for the endpoint
    * @return mutualSSLEnabled
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "false", value = "Whether Mutual SSL is enabled for the endpoint")
    
    public Boolean isMutualSSLEnabled() {
        return mutualSSLEnabled;
    }


    public void setMutualSSLEnabled(Boolean mutualSSLEnabled) {
        this.mutualSSLEnabled = mutualSSLEnabled;
    }


        public ServiceDTO usage(Integer usage) {
        
        this.usage = usage;
        return this;
        }

    /**
        * Number of usages of the service in APIs
    * @return usage
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "1", value = "Number of usages of the service in APIs")
    
    public Integer getUsage() {
        return usage;
    }


    public void setUsage(Integer usage) {
        this.usage = usage;
    }


        public ServiceDTO createdTime(String createdTime) {
        
        this.createdTime = createdTime;
        return this;
        }

    /**
        * Get createdTime
    * @return createdTime
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "2020-02-20T13:57:16.229", value = "")
    
    public String getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }


        public ServiceDTO lastUpdatedTime(String lastUpdatedTime) {
        
        this.lastUpdatedTime = lastUpdatedTime;
        return this;
        }

    /**
        * Get lastUpdatedTime
    * @return lastUpdatedTime
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "2020-02-20T13:57:16.229", value = "")
    
    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }


    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }


        public ServiceDTO md5(String md5) {
        
        this.md5 = md5;
        return this;
        }

    /**
        * Get md5
    * @return md5
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "36583a6a249b410e7fc4f892029709cac09763ddb230e1a829d5f9134d1abd07", value = "")
    
    public String getMd5() {
        return md5;
    }


    public void setMd5(String md5) {
        this.md5 = md5;
    }


        public ServiceDTO definitionUrl(String definitionUrl) {
        
        this.definitionUrl = definitionUrl;
        return this;
        }

    /**
        * Get definitionUrl
    * @return definitionUrl
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "https://petstore.swagger.io/v2/swagger.json", value = "")
    
    public String getDefinitionUrl() {
        return definitionUrl;
    }


    public void setDefinitionUrl(String definitionUrl) {
        this.definitionUrl = definitionUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            ServiceDTO service = (ServiceDTO) o;
            return Objects.equals(this.id, service.id) &&
            Objects.equals(this.name, service.name) &&
            Objects.equals(this.description, service.description) &&
            Objects.equals(this.version, service.version) &&
            Objects.equals(this.serviceKey, service.serviceKey) &&
            Objects.equals(this.serviceUrl, service.serviceUrl) &&
            Objects.equals(this.definitionType, service.definitionType) &&
            Objects.equals(this.securityType, service.securityType) &&
            Objects.equals(this.mutualSSLEnabled, service.mutualSSLEnabled) &&
            Objects.equals(this.usage, service.usage) &&
            Objects.equals(this.createdTime, service.createdTime) &&
            Objects.equals(this.lastUpdatedTime, service.lastUpdatedTime) &&
            Objects.equals(this.md5, service.md5) &&
            Objects.equals(this.definitionUrl, service.definitionUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, version, serviceKey, serviceUrl, definitionType, securityType, mutualSSLEnabled, usage, createdTime, lastUpdatedTime, md5, definitionUrl);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class ServiceDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    serviceKey: ").append(toIndentedString(serviceKey)).append("\n");
    sb.append("    serviceUrl: ").append(toIndentedString(serviceUrl)).append("\n");
    sb.append("    definitionType: ").append(toIndentedString(definitionType)).append("\n");
    sb.append("    securityType: ").append(toIndentedString(securityType)).append("\n");
    sb.append("    mutualSSLEnabled: ").append(toIndentedString(mutualSSLEnabled)).append("\n");
    sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
    sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
    sb.append("    lastUpdatedTime: ").append(toIndentedString(lastUpdatedTime)).append("\n");
    sb.append("    md5: ").append(toIndentedString(md5)).append("\n");
    sb.append("    definitionUrl: ").append(toIndentedString(definitionUrl)).append("\n");
sb.append("}");
return sb.toString();
}

/**
* Convert the given object to string with each line indented by 4 spaces
* (except the first line).
*/
private String toIndentedString(Object o) {
if (o == null) {
return "null";
}
return o.toString().replace("\n", "\n    ");
}

}

