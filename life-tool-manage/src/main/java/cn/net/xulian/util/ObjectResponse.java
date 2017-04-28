package cn.net.xulian.util;

public class ObjectResponse<T> extends SimpleResponse {
	private static final long serialVersionUID = 4386424983471523514L;
	private T data;

	public T getData() {
		return this.data;
	}

	public ObjectResponse() {
	}

	public ObjectResponse(boolean flag) {
		new SimpleResponse(flag);
	}

	public void setData(T data) {
		this.data = data;
	}


}
