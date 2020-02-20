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
 * @Description 取号机表
 * @Author jyehui
 * @Date  2020-02-03 18:46:29
 */
@ApiModel(value = "取号机表")
@TableName("j_qh_machine")
public class JQhMachine extends Model<JQhMachine> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "科目ID")
	@TableField("subject_id")
	private Integer subjectId;

    @ApiModelProperty(value = "取号机编号")
	@TableField("machine_no")
	private Integer machineNo;

    @ApiModelProperty(value = "取号机ip")
	@TableField("machine_ip")
	private String machineIp;

    @ApiModelProperty(value = "取号机名称")
	@TableField("machine_name")
	private String machineName;

    @ApiModelProperty(value = "取号机开放状态（0：关闭；1开放；2：强制开放）")
	@TableField("machine_open_status")
	private Integer machineOpenStatus;

    @ApiModelProperty(value = "开放时间段")
	@TableField("open_hours")
	private String openHours;

    @ApiModelProperty(value = "是否删除（0：未删除；1：已删除）")
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

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setMachineNo(Integer machineNo) {
		this.machineNo = machineNo;
	}

	public Integer getMachineNo() {
		return machineNo;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineOpenStatus(Integer machineOpenStatus) {
		this.machineOpenStatus = machineOpenStatus;
	}

	public Integer getMachineOpenStatus() {
		return machineOpenStatus;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getOpenHours() {
		return openHours;
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
