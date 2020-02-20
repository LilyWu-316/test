package com.annaru.queue.model.result;

import com.annaru.queue.utils.JacksonUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 订单下发返回结果
 */
public class IssuedCode extends IssuedResult {

	/**
	 * 状态码
	 */
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
