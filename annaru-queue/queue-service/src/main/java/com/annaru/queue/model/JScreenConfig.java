package com.annaru.queue.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.annaru.queue.utils.JacksonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import java.io.Serializable;
import java.util.Date;
import com.annaru.queue.utils.JacksonUtils;

/**
 * @Description 屏幕信息配置表
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@ApiModel(value = "屏幕信息配置表")
@TableName("j_screen_config")
public class JScreenConfig extends Model<JScreenConfig> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "屏幕编号")
	@TableField("screen_no")
	private Integer screenNo;

    @ApiModelProperty(value = "屏幕名称")
	@TableField("screen_name")
	private String screenName;

    @ApiModelProperty(value = "屏幕ip")
	@TableField("screen_ip")
	private String screenIp;

    @ApiModelProperty(value = "屏幕文字内容")
	@TableField("screen_text")
	private String screenText;

    @ApiModelProperty(value = "屏幕图片")
	@TableField("screen_picture")
	private String screenPicture;

    @ApiModelProperty(value = "屏幕状态（0：启用；1：禁用；）")
	@TableField("screen_status")
	private Integer screenStatus;

    @ApiModelProperty(value = "是否删除（0：未删除；1：已删除；）")
	@TableField("is_delete")
	private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
	@TableField("create_time")
	private Date createTime;

    @ApiModelProperty(value = "修改时间")
	@TableField("update_time")
	private Date updateTime;


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setScreenNo(Integer screenNo) {
		this.screenNo = screenNo;
	}

	public Integer getScreenNo() {
		return screenNo;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenIp(String screenIp) {
		this.screenIp = screenIp;
	}

	public String getScreenIp() {
		return screenIp;
	}

	public void setScreenText(String screenText) {
		this.screenText = screenText;
	}

	public String getScreenText() {
		return screenText;
	}

	public void setScreenPicture(String screenPicture) {
		this.screenPicture = screenPicture;
	}

	public String getScreenPicture() {
		return screenPicture;
	}

	public void setScreenStatus(Integer screenStatus) {
		this.screenStatus = screenStatus;
	}

	public Integer getScreenStatus() {
		return screenStatus;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

    @Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
        return JacksonUtils.toJson(this);
	}
}
