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
 * @Description 小屏模板表
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@ApiModel(value = "小屏模板表")
@TableName("j_small_screen_template")
public class JSmallScreenTemplate extends Model<JSmallScreenTemplate> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "小屏模板名称")
	@TableField("s_template_name")
	private String sTemplateName;

    @ApiModelProperty(value = "小屏模板图")
	@TableField("s_template_picture")
	private String sTemplatePicture;

    @ApiModelProperty(value = "模板状态（0：启用；1禁用）")
	private Integer status;

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

	public void setSTemplateName(String sTemplateName) {
		this.sTemplateName = sTemplateName;
	}

	public String getSTemplateName() {
		return sTemplateName;
	}

	public void setSTemplatePicture(String sTemplatePicture) {
		this.sTemplatePicture = sTemplatePicture;
	}

	public String getSTemplatePicture() {
		return sTemplatePicture;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
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
