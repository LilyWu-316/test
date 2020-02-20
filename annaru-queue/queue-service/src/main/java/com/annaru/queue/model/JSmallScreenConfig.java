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
 * @Description 小屏配置表
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@ApiModel(value = "小屏配置表")
@TableName("j_small_screen_config")
public class JSmallScreenConfig extends Model<JSmallScreenConfig> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "小屏编号")
	@TableField("s_screen_no")
	private Integer sScreenNo;

    @ApiModelProperty(value = "小屏ip")
	@TableField("s_screen_ip")
	private String sScreenIp;

    @ApiModelProperty(value = "小屏名称")
	@TableField("s_screen__name")
	private String sScreenName;

    @ApiModelProperty(value = "小屏模板Id(small_screen_template表中id)")
	@TableField("sst_id")
	private Integer sstId;

    @ApiModelProperty(value = "小屏屏保id(small_screen_protection表中id)")
	@TableField("ssp_id")
	private Integer sspId;

    @ApiModelProperty(value = "屏保状态（0：启用；1禁用；）")
	@TableField("protection_status")
	private Integer protectionStatus;

    @ApiModelProperty(value = "叫号声音状态（0：启用；1：禁用；）")
	@TableField("voice_status")
	private Integer voiceStatus;

    @ApiModelProperty(value = "视频状态（0：启用；1：禁用；）")
	@TableField("video_status")
	private Integer videoStatus;

    @ApiModelProperty(value = "视频id(video_info表中id)")
	@TableField("video_info_id")
	private Integer videoInfoId;

    @ApiModelProperty(value = "屏幕提示内容Id(screen_content表中id)")
	@TableField("screen_content_id")
	private Integer screenContentId;

    @ApiModelProperty(value = "小屏logo")
	@TableField("s_screen_logo")
	private String sScreenLogo;

    @ApiModelProperty(value = "小屏抬头")
	@TableField("s_screen_title")
	private String sScreenTitle;

    @ApiModelProperty(value = "机构名称")
	@TableField("institution_name")
	private String institutionName;

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

	public void setSScreenNo(Integer sScreenNo) {
		this.sScreenNo = sScreenNo;
	}

	public Integer getSScreenNo() {
		return sScreenNo;
	}

	public void setSScreenIp(String sScreenIp) {
		this.sScreenIp = sScreenIp;
	}

	public String getSScreenIp() {
		return sScreenIp;
	}

	public void setSScreenName(String sScreenName) {
		this.sScreenName = sScreenName;
	}

	public String getSScreenName() {
		return sScreenName;
	}

	public void setSstId(Integer sstId) {
		this.sstId = sstId;
	}

	public Integer getSstId() {
		return sstId;
	}

	public void setSspId(Integer sspId) {
		this.sspId = sspId;
	}

	public Integer getSspId() {
		return sspId;
	}

	public void setProtectionStatus(Integer protectionStatus) {
		this.protectionStatus = protectionStatus;
	}

	public Integer getProtectionStatus() {
		return protectionStatus;
	}

	public void setVoiceStatus(Integer voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

	public Integer getVoiceStatus() {
		return voiceStatus;
	}

	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}

	public Integer getVideoStatus() {
		return videoStatus;
	}

	public void setVideoInfoId(Integer videoInfoId) {
		this.videoInfoId = videoInfoId;
	}

	public Integer getVideoInfoId() {
		return videoInfoId;
	}

	public void setScreenContentId(Integer screenContentId) {
		this.screenContentId = screenContentId;
	}

	public Integer getScreenContentId() {
		return screenContentId;
	}

	public void setSScreenLogo(String sScreenLogo) {
		this.sScreenLogo = sScreenLogo;
	}

	public String getSScreenLogo() {
		return sScreenLogo;
	}

	public void setSScreenTitle(String sScreenTitle) {
		this.sScreenTitle = sScreenTitle;
	}

	public String getSScreenTitle() {
		return sScreenTitle;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getInstitutionName() {
		return institutionName;
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
