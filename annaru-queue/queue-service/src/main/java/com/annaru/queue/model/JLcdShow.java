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
 * @Description 叫号表
 * @Author jyehui
 * @Date  2020-02-03 18:46:29
 */
@ApiModel(value = "叫号表")
@TableName("j_lcd_show")
public class JLcdShow extends Model<JLcdShow> {
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
	@TableId
	private Integer id;

    @ApiModelProperty(value = "小屏地址")
	private String xpdz;

    @ApiModelProperty(value = "医生姓名")
	private String ysxm;

    @ApiModelProperty(value = "医生工号")
	private String ysgh;

    @ApiModelProperty(value = "房间名称")
	private String fjmc;

    @ApiModelProperty(value = "医生格言")
	private String ysgy;

    @ApiModelProperty(value = "排队号码")
	private String pdhm;

    @ApiModelProperty(value = "病人姓名")
	private String brxm;

    @ApiModelProperty(value = "创建时间")
	@TableField("create_time")
	private Date createTime;

    @ApiModelProperty(value = "修改时间")
	@TableField("update_time")
	private Date updateTime;

    @ApiModelProperty(value = "数据来源（0：第三方；1：本地）")
	@TableField("data_source")
	private Integer dataSource;

    @ApiModelProperty(value = "状态(0：正常、1：失效)")
	private Integer status;


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setXpdz(String xpdz) {
		this.xpdz = xpdz;
	}

	public String getXpdz() {
		return xpdz;
	}

	public void setYsxm(String ysxm) {
		this.ysxm = ysxm;
	}

	public String getYsxm() {
		return ysxm;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setYsgy(String ysgy) {
		this.ysgy = ysgy;
	}

	public String getYsgy() {
		return ysgy;
	}

	public void setPdhm(String pdhm) {
		this.pdhm = pdhm;
	}

	public String getPdhm() {
		return pdhm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getBrxm() {
		return brxm;
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

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getDataSource() {
		return dataSource;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
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
