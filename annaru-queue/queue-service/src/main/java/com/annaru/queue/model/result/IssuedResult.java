package com.annaru.queue.model.result;

import com.annaru.queue.utils.JacksonUtils;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 订单下发返回结果
 */
public class IssuedResult extends Model<IssuedResult> {

	private static final long serialVersionUID = 1L;

	/**
	 * 存件码
	 */
	private String putintoPin;

	/**
	 * 取件码
	 */
	private String takeoutPin;

	public String getPutintoPin() {
		return putintoPin;
	}

	public void setPutintoPin(String putintoPin) {
		this.putintoPin = putintoPin;
	}

	public String getTakeoutPin() {
		return takeoutPin;
	}

	public void setTakeoutPin(String takeoutPin) {
		this.takeoutPin = takeoutPin;
	}

	@Override
	public String toString() {
        return JacksonUtils.toJson(this);
	}
}
