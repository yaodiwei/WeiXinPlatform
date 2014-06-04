package com.lgbear.weixinplatform.api.domain;

public class LinkMessage extends WeixinMessage {

    private String msgType = WeixinMessage.LINK;
    private String title;
    private String description;
    private String url;
    private String msgId;

    public LinkMessage() {
    }

    public LinkMessage(String msgType, String title, String description,
            String url, String msgId) {
        super();
        this.msgType = msgType;
        this.title = title;
        this.description = description;
        this.url = url;
        this.msgId = msgId;
    }

    @Override
    public String getMsgType() {
        return msgType;
    }

    @Override
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
