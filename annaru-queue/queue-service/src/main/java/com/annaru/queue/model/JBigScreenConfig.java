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
 * @Description 大屏配置表
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
@ApiModel(value = "大屏配置表")
@TableName("j_big_screen_config")
public class JBigScreenConfig extends Model<JBigScreenConfig> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "大屏编号")
	@TableField("b_screen_no")
	private Integer bScreenNo;

    @ApiModelProperty(value = "大屏ip")
	@TableField("b_screen_ip")
	private String bScreenIp;

    @ApiModelProperty(value = "小屏编号(small_screen_config中s_screen_no)")
	@TableField("s_screen_id")
	private Integer sScreenId;

    @ApiModelProperty(value = "大屏名称")
	@TableField("b_screen__name")
	private String bScreenName;

    @ApiModelProperty(value = "大屏模板Id(big_screen_template表中id)")
	@TableField("bst_id")
	private Integer bstId;

    @ApiModelProperty(value = "行数")
	@TableField("line_number")
	private Integer lineNumber;

    @ApiModelProperty(value = "屏幕提示内容Id(screen_content表中id)")
	@TableField("bcreen_content_id")
	private Integer bcreenContentId;

    @ApiModelProperty(value = "大屏logo")
	@TableField("b_screen_logo")
	private String bScreenLogo;

    @ApiModelProperty(value = "大屏抬头")
	@TableField("b_screen_title")
	private String bScreenTitle;

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

	public void setBScreenNo(Integer bScreenNo) {
		this.bScreenNo = bScreenNo;
	}

	public Integer getBScreenNo() {
		return bScreenNo;
	}

	public void setBScreenIp(String bScreenIp) {
		this.bScreenIp = bScreenIp;
	}

	public String getBScreenIp() {
		return bScreenIp;
	}

	public void setSScreenId(Integer sScreenId) {
		this.sScreenId = sScreenId;
	}

	public Integer getSScreenId() {
		return sScreenId;
	}

	public void setBScreenName(String bScreenName) {
		this.bScreenName = bScreenName;
	}

	public String getBScreenName() {
		return bScreenName;
	}

	public void setBstId(Integer bstId) {
		this.bstId = bstId;
	}

	public Integer getBstId() {
		return bstId;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setBcreenContentId(Integer bcreenContentId) {
		this.bcreenContentId = bcreenContentId;
	}

	public Integer getBcreenContentId() {
		return bcreenContentId;
	}

	public void setBScreenLogo(String bScreenLogo) {
		this.bScreenLogo = bScreenLogo;
	}

	public String getBScreenLogo() {
		return bScreenLogo;
	}

	public void setBScreenTitle(String bScreenTitle) {
		this.bScreenTitle = bScreenTitle;
	}

	public String getBScreenTitle() {
		return bScreenTitle;
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
