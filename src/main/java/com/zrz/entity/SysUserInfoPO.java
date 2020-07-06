package com.zrz.entity;

import java.io.Serializable;

public class SysUserInfoPO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6617718511293542128L;

	/**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后登录时间
     */
    private String lastLogin;

    /**
     * token值
     */
    private String token;

    /**
     * token更新时间
     */
    private Integer tokenUpdateTime;

    /**
     * 成功次数
     */
    private Integer successTimes;

    /**
     * 早上执行时间(时)
     */
    private Integer exAmHour;

    /**
     * 早上执行时间(分)
     */
    private Integer exAmMinute;

    /**
     * 早上执行时间(秒)
     */
    private Integer exAmSecond;

    /**
     * 晚上执行时间(时)
     */
    private Integer exPmHour;

    /**
     * 晚上执行时间(分)
     */
    private Integer exPmMinute;

    /**
     * 晚上执行时间(秒)
     */
    private Integer exPmSecond;

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
    private String positionId;

    /**
     * 是否可用
     */
    private String isEnabled;

    /**
     * 状态
     */
    private String status;

    /**
     * 用户ID
     * @return id 用户ID
     */
    public String getId() {
        return id;
    }

    /**
     * 用户ID
     * @param id 用户ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 用户名
     * @return name 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 账号
     * @return account 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 最后登录时间
     * @return last_login 最后登录时间
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * 最后登录时间
     * @param lastLogin 最后登录时间
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin == null ? null : lastLogin.trim();
    }

    /**
     * token值
     * @return token token值
     */
    public String getToken() {
        return token;
    }

    /**
     * token值
     * @param token token值
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * token更新时间
     * @return token_update_time token更新时间
     */
    public Integer getTokenUpdateTime() {
        return tokenUpdateTime;
    }

    /**
     * token更新时间
     * @param tokenUpdateTime token更新时间
     */
    public void setTokenUpdateTime(Integer tokenUpdateTime) {
        this.tokenUpdateTime = tokenUpdateTime;
    }

    /**
     * 成功次数
     * @return success_times 成功次数
     */
    public Integer getSuccessTimes() {
        return successTimes;
    }

    /**
     * 成功次数
     * @param successTimes 成功次数
     */
    public void setSuccessTimes(Integer successTimes) {
        this.successTimes = successTimes;
    }

    /**
     * 早上执行时间(时)
     * @return ex_am_hour 早上执行时间(时)
     */
    public Integer getExAmHour() {
        return exAmHour;
    }

    /**
     * 早上执行时间(时)
     * @param exAmHour 早上执行时间(时)
     */
    public void setExAmHour(Integer exAmHour) {
        this.exAmHour = exAmHour;
    }

    /**
     * 早上执行时间(分)
     * @return ex_am_minute 早上执行时间(分)
     */
    public Integer getExAmMinute() {
        return exAmMinute;
    }

    /**
     * 早上执行时间(分)
     * @param exAmMinute 早上执行时间(分)
     */
    public void setExAmMinute(Integer exAmMinute) {
        this.exAmMinute = exAmMinute;
    }

    /**
     * 早上执行时间(秒)
     * @return ex_am_second 早上执行时间(秒)
     */
    public Integer getExAmSecond() {
        return exAmSecond;
    }

    /**
     * 早上执行时间(秒)
     * @param exAmSecond 早上执行时间(秒)
     */
    public void setExAmSecond(Integer exAmSecond) {
        this.exAmSecond = exAmSecond;
    }

    /**
     * 晚上执行时间(时)
     * @return ex_pm_hour 晚上执行时间(时)
     */
    public Integer getExPmHour() {
        return exPmHour;
    }

    /**
     * 晚上执行时间(时)
     * @param exPmHour 晚上执行时间(时)
     */
    public void setExPmHour(Integer exPmHour) {
        this.exPmHour = exPmHour;
    }

    /**
     * 晚上执行时间(分)
     * @return ex_pm_minute 晚上执行时间(分)
     */
    public Integer getExPmMinute() {
        return exPmMinute;
    }

    /**
     * 晚上执行时间(分)
     * @param exPmMinute 晚上执行时间(分)
     */
    public void setExPmMinute(Integer exPmMinute) {
        this.exPmMinute = exPmMinute;
    }

    /**
     * 晚上执行时间(秒)
     * @return ex_pm_second 晚上执行时间(秒)
     */
    public Integer getExPmSecond() {
        return exPmSecond;
    }

    /**
     * 晚上执行时间(秒)
     * @param exPmSecond 晚上执行时间(秒)
     */
    public void setExPmSecond(Integer exPmSecond) {
        this.exPmSecond = exPmSecond;
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
     * @return position_id 位置id
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * 位置id
     * @param positionId 位置id
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
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