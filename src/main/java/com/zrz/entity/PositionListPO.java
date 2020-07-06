package com.zrz.entity;

public class PositionListPO {
    /**
     * 位置id
     */
    private String id;

    /**
     * 位置名称
     */
    private String name;

    /**
     * location_id
     */
    private Integer locationId;

    /**
     * 横坐标前缀值
     */
    private Double positionX;

    /**
     * 纵坐标前缀值
     */
    private Double positionY;

    /**
     * 横坐标尾数最小值
     */
    private Integer positionXMin;

    /**
     * 横坐标尾数最大值
     */
    private Integer positionXMax;

    /**
     * 纵坐标尾数最小值
     */
    private Integer positionYMin;

    /**
     * 纵坐标尾数最大值
     */
    private Integer positionYMax;

    /**
     * 是否可用
     */
    private String isEnabled;

    /**
     * 状态
     */
    private String status;

    /**
     * 位置id
     * @return id 位置id
     */
    public String getId() {
        return id;
    }

    /**
     * 位置id
     * @param id 位置id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 位置名称
     * @return name 位置名称
     */
    public String getName() {
        return name;
    }

    /**
     * 位置名称
     * @param name 位置名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * location_id
     * @return location_id location_id
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * location_id
     * @param locationId location_id
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * 横坐标前缀值
     * @return position_x 横坐标前缀值
     */
    public Double getPositionX() {
        return positionX;
    }

    /**
     * 横坐标前缀值
     * @param positionX 横坐标前缀值
     */
    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    /**
     * 纵坐标前缀值
     * @return position_y 纵坐标前缀值
     */
    public Double getPositionY() {
        return positionY;
    }

    /**
     * 纵坐标前缀值
     * @param positionY 纵坐标前缀值
     */
    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    /**
     * 横坐标尾数最小值
     * @return position_x_min 横坐标尾数最小值
     */
    public Integer getPositionXMin() {
        return positionXMin;
    }

    /**
     * 横坐标尾数最小值
     * @param positionXMin 横坐标尾数最小值
     */
    public void setPositionXMin(Integer positionXMin) {
        this.positionXMin = positionXMin;
    }

    /**
     * 横坐标尾数最大值
     * @return position_x_max 横坐标尾数最大值
     */
    public Integer getPositionXMax() {
        return positionXMax;
    }

    /**
     * 横坐标尾数最大值
     * @param positionXMax 横坐标尾数最大值
     */
    public void setPositionXMax(Integer positionXMax) {
        this.positionXMax = positionXMax;
    }

    /**
     * 纵坐标尾数最小值
     * @return position_y_min 纵坐标尾数最小值
     */
    public Integer getPositionYMin() {
        return positionYMin;
    }

    /**
     * 纵坐标尾数最小值
     * @param positionYMin 纵坐标尾数最小值
     */
    public void setPositionYMin(Integer positionYMin) {
        this.positionYMin = positionYMin;
    }

    /**
     * 纵坐标尾数最大值
     * @return position_y_max 纵坐标尾数最大值
     */
    public Integer getPositionYMax() {
        return positionYMax;
    }

    /**
     * 纵坐标尾数最大值
     * @param positionYMax 纵坐标尾数最大值
     */
    public void setPositionYMax(Integer positionYMax) {
        this.positionYMax = positionYMax;
    }

    /**
     * 是否可用
     * @return is_enabled 是否可用
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * 是否可用
     * @param isEnabled 是否可用
     */
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
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
}