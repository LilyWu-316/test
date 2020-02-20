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
 * @Description screen_conf与video_info的关联表
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@ApiModel(value = "screen_conf与video_info的关联表")
@TableName("j_rel_screen_conf_video_info")
public class JRelScreenConfVideoInfo extends Model<JRelScreenConfVideoInfo> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "视频id(video_info表中id字段)")
	@TableField("video_info_id")
	private Integer videoInfoId;

    @ApiModelProperty(value = "顺序")
	private Integer sort;

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

	public void setVideoInfoId(Integer videoInfoId) {
		this.videoInfoId = videoInfoId;
	}

	public Integer getVideoInfoId() {
		return videoInfoId;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
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
