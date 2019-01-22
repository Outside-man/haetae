/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.manager;

import us.betahouse.haetae.asset.request.AssetRequest;

/**
 * @author yiyuk.hxy
 * @version : AssetRequestBuilder.java 2019/01/22 16:58 yiyuk.hxy
 */
public class AssetRequestBuilder extends AssetRequest {
    private String assetId;
    private String assetName;
    private String status;
    private String orginazationId;
    private String type;
    private Integer amount;
    private Integer remain;

    private AssetRequestBuilder() {
    }
    private AssetRequestBuilder(Builder builder) {
        assetId = builder.assetId;
        assetName = builder.assetName;
        status = builder.status;
        orginazationId = builder.orginazationId;
        type = builder.type;
        amount = builder.amount;
        remain = builder.remain;
    }

    public static final class Builder {
        private String assetId;
        private String assetName;
        private String status;
        private String orginazationId;
        private String type;
        private Integer amount;
        private Integer remain;

        public Builder() {
        }

        public Builder assetId(String val) {
            assetId = val;
            return this;
        }

        public Builder assetName(String val) {
            assetName = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder orginazationId(String val) {
            orginazationId = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder amount(Integer val) {
            amount = val;
            return this;
        }

        public Builder remain(Integer val) {
            remain = val;
            return this;
        }

        public AssetRequestBuilder build() {
            return new AssetRequestBuilder(this);
        }
    }
}
