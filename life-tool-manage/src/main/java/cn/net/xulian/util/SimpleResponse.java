package cn.net.xulian.util;

import java.io.Serializable;

public class SimpleResponse implements Serializable {
	private static final long serialVersionUID = -5238115197608725618L;
	private ReturnCode code = ReturnCode.SUCCESS;
	private String msg;

	public SimpleResponse() {
	}

	public SimpleResponse(boolean flag) {
		if (flag) {
			this.code = ReturnCode.SUCCESS;
			this.msg = "操作成功";
		} else {
			this.code = ReturnCode.FAILURE;
			this.msg = "操作失败";
		}
	}

	public String getCode() {
		return this.code.getValue();
	}

	public void setCode(ReturnCode code) {
		this.code = code;
	}

	public void setCode(boolean flag) {
		if (flag) {
			this.code = ReturnCode.SUCCESS;
		} else
			this.code = ReturnCode.FAILURE;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
