package id.hike.apps.android_mpos_mumu.features.pelanggan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by getwiz on 15/06/17.
 */

public class ModelGenerateToken {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("refresh_token")
    public String refreshToken;
    @SerializedName("scope")
    public String scope;
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("expires_in")
    public Integer expiresIn;
    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }
}
