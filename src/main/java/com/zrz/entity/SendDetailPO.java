package com.zrz.entity;

public class SendDetailPO {
    /**
     * 表ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 横坐标
     */
    private Double positionX;

    /**
     * 纵坐标
     */
    private Double positionY;

    /**
     * 位置id
     */
    private String positionType;

    /**
     * 表ID
     * @return id 表ID
     */
    public String getId() {
        return id;
    }

    /**
     * 表ID
     * @param id 表ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 用户ID
     * @return user_id 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 发送时间
     * @return send_time 发送时间
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间
     * @param sendTime 发送时间
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 横坐标
     * @return position_x 横坐标
     */
    public Double getPositionX() {
        return positionX;
    }

    /**
     * 横坐标
     * @param positionX 横坐标
     */
    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    /**
     * 纵坐标
     * @return position_y 纵坐标
     */
    public Double getPositionY() {
        return positionY;
    }

    /**
     * 纵坐标
     * @param positionY 纵坐标
     */
    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    /**
     * 位置id
     * @return position_type 位置id
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * 位置id
     * @param positionType 位置id
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType == null ? null : positionType.trim();
    }
}