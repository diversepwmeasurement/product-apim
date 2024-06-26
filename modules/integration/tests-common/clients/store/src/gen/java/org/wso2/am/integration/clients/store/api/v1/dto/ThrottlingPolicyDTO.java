/*
 * WSO2 API Manager - Developer Portal
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Developer Portal**. Please see [full OpenAPI Specification](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.7.206/components/apimgt/org.wso2.carbon.apimgt.rest.api.store.v1/src/main/resources/devportal-api.yaml) of the API which is written using [OAS 3.0](http://swagger.io/) specification.  # Authentication The Developer Portal REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the API, you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_devportal\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown below to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_devportal\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api_devportal\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for devportal REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_password>&scope=<scopes separated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:subscribe apim:api_key\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:subscribe apim:api_key\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5bc0161b8aa7e701d7bf) 
 *
 * The version of the OpenAPI document: v3
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.store.api.v1.dto;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.wso2.am.integration.clients.store.api.v1.dto.MonetizationInfoDTO;
import org.wso2.am.integration.clients.store.api.v1.dto.ThrottlingPolicyPermissionInfoDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* ThrottlingPolicyDTO
*/

public class ThrottlingPolicyDTO {
        public static final String SERIALIZED_NAME_NAME = "name";
        @SerializedName(SERIALIZED_NAME_NAME)
            private String name;

        public static final String SERIALIZED_NAME_DESCRIPTION = "description";
        @SerializedName(SERIALIZED_NAME_DESCRIPTION)
            private String description;

            /**
* Gets or Sets policyLevel
*/
    @JsonAdapter(PolicyLevelEnum.Adapter.class)
public enum PolicyLevelEnum {
        APPLICATION("application"),
        
        SUBSCRIPTION("subscription");

private String value;

PolicyLevelEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static PolicyLevelEnum fromValue(String value) {
    for (PolicyLevelEnum b : PolicyLevelEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<PolicyLevelEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final PolicyLevelEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public PolicyLevelEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return PolicyLevelEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_POLICY_LEVEL = "policyLevel";
        @SerializedName(SERIALIZED_NAME_POLICY_LEVEL)
            private PolicyLevelEnum policyLevel;

        public static final String SERIALIZED_NAME_ATTRIBUTES = "attributes";
        @SerializedName(SERIALIZED_NAME_ATTRIBUTES)
            private Map<String, String> attributes = null;

        public static final String SERIALIZED_NAME_REQUEST_COUNT = "requestCount";
        @SerializedName(SERIALIZED_NAME_REQUEST_COUNT)
            private Long requestCount;

        public static final String SERIALIZED_NAME_DATA_UNIT = "dataUnit";
        @SerializedName(SERIALIZED_NAME_DATA_UNIT)
            private String dataUnit;

        public static final String SERIALIZED_NAME_UNIT_TIME = "unitTime";
        @SerializedName(SERIALIZED_NAME_UNIT_TIME)
            private Long unitTime;

        public static final String SERIALIZED_NAME_TIME_UNIT = "timeUnit";
        @SerializedName(SERIALIZED_NAME_TIME_UNIT)
            private String timeUnit;

        public static final String SERIALIZED_NAME_RATE_LIMIT_COUNT = "rateLimitCount";
        @SerializedName(SERIALIZED_NAME_RATE_LIMIT_COUNT)
            private Integer rateLimitCount = 0;

        public static final String SERIALIZED_NAME_RATE_LIMIT_TIME_UNIT = "rateLimitTimeUnit";
        @SerializedName(SERIALIZED_NAME_RATE_LIMIT_TIME_UNIT)
            private String rateLimitTimeUnit;

            /**
* Default quota limit type
*/
    @JsonAdapter(QuotaPolicyTypeEnum.Adapter.class)
public enum QuotaPolicyTypeEnum {
        REQUESTCOUNT("REQUESTCOUNT"),
        
        BANDWIDTHVOLUME("BANDWIDTHVOLUME");

private String value;

QuotaPolicyTypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static QuotaPolicyTypeEnum fromValue(String value) {
    for (QuotaPolicyTypeEnum b : QuotaPolicyTypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<QuotaPolicyTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final QuotaPolicyTypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public QuotaPolicyTypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return QuotaPolicyTypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_QUOTA_POLICY_TYPE = "quotaPolicyType";
        @SerializedName(SERIALIZED_NAME_QUOTA_POLICY_TYPE)
            private QuotaPolicyTypeEnum quotaPolicyType;

            /**
* This attribute declares whether this tier is available under commercial or free 
*/
    @JsonAdapter(TierPlanEnum.Adapter.class)
public enum TierPlanEnum {
        FREE("FREE"),
        
        COMMERCIAL("COMMERCIAL");

private String value;

TierPlanEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static TierPlanEnum fromValue(String value) {
    for (TierPlanEnum b : TierPlanEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<TierPlanEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final TierPlanEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public TierPlanEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return TierPlanEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_TIER_PLAN = "tierPlan";
        @SerializedName(SERIALIZED_NAME_TIER_PLAN)
            private TierPlanEnum tierPlan;

        public static final String SERIALIZED_NAME_STOP_ON_QUOTA_REACH = "stopOnQuotaReach";
        @SerializedName(SERIALIZED_NAME_STOP_ON_QUOTA_REACH)
            private Boolean stopOnQuotaReach;

        public static final String SERIALIZED_NAME_MONETIZATION_ATTRIBUTES = "monetizationAttributes";
        @SerializedName(SERIALIZED_NAME_MONETIZATION_ATTRIBUTES)
            private MonetizationInfoDTO monetizationAttributes;

        public static final String SERIALIZED_NAME_THROTTLING_POLICY_PERMISSIONS = "throttlingPolicyPermissions";
        @SerializedName(SERIALIZED_NAME_THROTTLING_POLICY_PERMISSIONS)
            private ThrottlingPolicyPermissionInfoDTO throttlingPolicyPermissions;


        public ThrottlingPolicyDTO name(String name) {
        
        this.name = name;
        return this;
        }

    /**
        * Get name
    * @return name
    **/
      @ApiModelProperty(example = "Platinum", required = true, value = "")
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


        public ThrottlingPolicyDTO description(String description) {
        
        this.description = description;
        return this;
        }

    /**
        * Get description
    * @return description
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "Allows 50 request(s) per minute.", value = "")
    
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


        public ThrottlingPolicyDTO policyLevel(PolicyLevelEnum policyLevel) {
        
        this.policyLevel = policyLevel;
        return this;
        }

    /**
        * Get policyLevel
    * @return policyLevel
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "subscription", value = "")
    
    public PolicyLevelEnum getPolicyLevel() {
        return policyLevel;
    }


    public void setPolicyLevel(PolicyLevelEnum policyLevel) {
        this.policyLevel = policyLevel;
    }


        public ThrottlingPolicyDTO attributes(Map<String, String> attributes) {
        
        this.attributes = attributes;
        return this;
        }

    /**
        * Custom attributes added to the throttling policy 
    * @return attributes
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "{}", value = "Custom attributes added to the throttling policy ")
    
    public Map<String, String> getAttributes() {
        return attributes;
    }


    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }


        public ThrottlingPolicyDTO requestCount(Long requestCount) {
        
        this.requestCount = requestCount;
        return this;
        }

    /**
        * Maximum number of requests which can be sent within a provided unit time 
    * @return requestCount
    **/
      @ApiModelProperty(example = "50", required = true, value = "Maximum number of requests which can be sent within a provided unit time ")
    
    public Long getRequestCount() {
        return requestCount;
    }


    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }


        public ThrottlingPolicyDTO dataUnit(String dataUnit) {
        
        this.dataUnit = dataUnit;
        return this;
        }

    /**
        * Unit of data allowed to be transfered. Allowed values are \&quot;KB\&quot;, \&quot;MB\&quot; and \&quot;GB\&quot; 
    * @return dataUnit
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "KB", value = "Unit of data allowed to be transfered. Allowed values are \"KB\", \"MB\" and \"GB\" ")
    
    public String getDataUnit() {
        return dataUnit;
    }


    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }


        public ThrottlingPolicyDTO unitTime(Long unitTime) {
        
        this.unitTime = unitTime;
        return this;
        }

    /**
        * Get unitTime
    * @return unitTime
    **/
      @ApiModelProperty(example = "60000", required = true, value = "")
    
    public Long getUnitTime() {
        return unitTime;
    }


    public void setUnitTime(Long unitTime) {
        this.unitTime = unitTime;
    }


        public ThrottlingPolicyDTO timeUnit(String timeUnit) {
        
        this.timeUnit = timeUnit;
        return this;
        }

    /**
        * Get timeUnit
    * @return timeUnit
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "min", value = "")
    
    public String getTimeUnit() {
        return timeUnit;
    }


    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }


        public ThrottlingPolicyDTO rateLimitCount(Integer rateLimitCount) {
        
        this.rateLimitCount = rateLimitCount;
        return this;
        }

    /**
        * Burst control request count
    * @return rateLimitCount
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "10", value = "Burst control request count")
    
    public Integer getRateLimitCount() {
        return rateLimitCount;
    }


    public void setRateLimitCount(Integer rateLimitCount) {
        this.rateLimitCount = rateLimitCount;
    }


        public ThrottlingPolicyDTO rateLimitTimeUnit(String rateLimitTimeUnit) {
        
        this.rateLimitTimeUnit = rateLimitTimeUnit;
        return this;
        }

    /**
        * Burst control time unit
    * @return rateLimitTimeUnit
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "min", value = "Burst control time unit")
    
    public String getRateLimitTimeUnit() {
        return rateLimitTimeUnit;
    }


    public void setRateLimitTimeUnit(String rateLimitTimeUnit) {
        this.rateLimitTimeUnit = rateLimitTimeUnit;
    }


        public ThrottlingPolicyDTO quotaPolicyType(QuotaPolicyTypeEnum quotaPolicyType) {
        
        this.quotaPolicyType = quotaPolicyType;
        return this;
        }

    /**
        * Default quota limit type
    * @return quotaPolicyType
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "REQUESTCOUNT", value = "Default quota limit type")
    
    public QuotaPolicyTypeEnum getQuotaPolicyType() {
        return quotaPolicyType;
    }


    public void setQuotaPolicyType(QuotaPolicyTypeEnum quotaPolicyType) {
        this.quotaPolicyType = quotaPolicyType;
    }


        public ThrottlingPolicyDTO tierPlan(TierPlanEnum tierPlan) {
        
        this.tierPlan = tierPlan;
        return this;
        }

    /**
        * This attribute declares whether this tier is available under commercial or free 
    * @return tierPlan
    **/
      @ApiModelProperty(example = "FREE", required = true, value = "This attribute declares whether this tier is available under commercial or free ")
    
    public TierPlanEnum getTierPlan() {
        return tierPlan;
    }


    public void setTierPlan(TierPlanEnum tierPlan) {
        this.tierPlan = tierPlan;
    }


        public ThrottlingPolicyDTO stopOnQuotaReach(Boolean stopOnQuotaReach) {
        
        this.stopOnQuotaReach = stopOnQuotaReach;
        return this;
        }

    /**
        * If this attribute is set to false, you are capabale of sending requests even if the request count exceeded within a unit time 
    * @return stopOnQuotaReach
    **/
      @ApiModelProperty(example = "true", required = true, value = "If this attribute is set to false, you are capabale of sending requests even if the request count exceeded within a unit time ")
    
    public Boolean isStopOnQuotaReach() {
        return stopOnQuotaReach;
    }


    public void setStopOnQuotaReach(Boolean stopOnQuotaReach) {
        this.stopOnQuotaReach = stopOnQuotaReach;
    }


        public ThrottlingPolicyDTO monetizationAttributes(MonetizationInfoDTO monetizationAttributes) {
        
        this.monetizationAttributes = monetizationAttributes;
        return this;
        }

    /**
        * Get monetizationAttributes
    * @return monetizationAttributes
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public MonetizationInfoDTO getMonetizationAttributes() {
        return monetizationAttributes;
    }


    public void setMonetizationAttributes(MonetizationInfoDTO monetizationAttributes) {
        this.monetizationAttributes = monetizationAttributes;
    }


        public ThrottlingPolicyDTO throttlingPolicyPermissions(ThrottlingPolicyPermissionInfoDTO throttlingPolicyPermissions) {
        
        this.throttlingPolicyPermissions = throttlingPolicyPermissions;
        return this;
        }

    /**
        * Get throttlingPolicyPermissions
    * @return throttlingPolicyPermissions
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public ThrottlingPolicyPermissionInfoDTO getThrottlingPolicyPermissions() {
        return throttlingPolicyPermissions;
    }


    public void setThrottlingPolicyPermissions(ThrottlingPolicyPermissionInfoDTO throttlingPolicyPermissions) {
        this.throttlingPolicyPermissions = throttlingPolicyPermissions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            ThrottlingPolicyDTO throttlingPolicy = (ThrottlingPolicyDTO) o;
            return Objects.equals(this.name, throttlingPolicy.name) &&
            Objects.equals(this.description, throttlingPolicy.description) &&
            Objects.equals(this.policyLevel, throttlingPolicy.policyLevel) &&
            Objects.equals(this.attributes, throttlingPolicy.attributes) &&
            Objects.equals(this.requestCount, throttlingPolicy.requestCount) &&
            Objects.equals(this.dataUnit, throttlingPolicy.dataUnit) &&
            Objects.equals(this.unitTime, throttlingPolicy.unitTime) &&
            Objects.equals(this.timeUnit, throttlingPolicy.timeUnit) &&
            Objects.equals(this.rateLimitCount, throttlingPolicy.rateLimitCount) &&
            Objects.equals(this.rateLimitTimeUnit, throttlingPolicy.rateLimitTimeUnit) &&
            Objects.equals(this.quotaPolicyType, throttlingPolicy.quotaPolicyType) &&
            Objects.equals(this.tierPlan, throttlingPolicy.tierPlan) &&
            Objects.equals(this.stopOnQuotaReach, throttlingPolicy.stopOnQuotaReach) &&
            Objects.equals(this.monetizationAttributes, throttlingPolicy.monetizationAttributes) &&
            Objects.equals(this.throttlingPolicyPermissions, throttlingPolicy.throttlingPolicyPermissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, policyLevel, attributes, requestCount, dataUnit, unitTime, timeUnit, rateLimitCount, rateLimitTimeUnit, quotaPolicyType, tierPlan, stopOnQuotaReach, monetizationAttributes, throttlingPolicyPermissions);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class ThrottlingPolicyDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    policyLevel: ").append(toIndentedString(policyLevel)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    requestCount: ").append(toIndentedString(requestCount)).append("\n");
    sb.append("    dataUnit: ").append(toIndentedString(dataUnit)).append("\n");
    sb.append("    unitTime: ").append(toIndentedString(unitTime)).append("\n");
    sb.append("    timeUnit: ").append(toIndentedString(timeUnit)).append("\n");
    sb.append("    rateLimitCount: ").append(toIndentedString(rateLimitCount)).append("\n");
    sb.append("    rateLimitTimeUnit: ").append(toIndentedString(rateLimitTimeUnit)).append("\n");
    sb.append("    quotaPolicyType: ").append(toIndentedString(quotaPolicyType)).append("\n");
    sb.append("    tierPlan: ").append(toIndentedString(tierPlan)).append("\n");
    sb.append("    stopOnQuotaReach: ").append(toIndentedString(stopOnQuotaReach)).append("\n");
    sb.append("    monetizationAttributes: ").append(toIndentedString(monetizationAttributes)).append("\n");
    sb.append("    throttlingPolicyPermissions: ").append(toIndentedString(throttlingPolicyPermissions)).append("\n");
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

