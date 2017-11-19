package com.changan.homecloud.po;

/**
 * 统一返回的数据格式
 * 
 * @author ljh
 *
 */
public class Result<T> {

	/**
	 * 200：请求成功，并得到正确结果 500：请求成功，但服务器出现异常错误 400：错误的请求格式
	 */
	private int code;

	private String msg;

	private T data;

	/**
	 * 推送为空
	 */
	public static final int PUSH_FAILED = 99;

	/**
	 * 参数为空
	 */
	public static final int PARAMS_NULL = 1000;

	/**
	 * 参数错误
	 */
	public static final int PARAMS_ERROR = 1009;

	/**
	 * 服务器发生异常
	 */
	public static final int SERVER_ERROR = 500;

	/**
	 * 请求成功并结果正确
	 */
	public static final int SUCCESS = 200;

	/**
	 * 要插入的数据已存在
	 * 
	 * @return
	 */
	public static final int ALREAY_ATTACHED = 1001;

	/**
	 * 未查询到记录
	 */
	public static final int NO_RECORD = 1002;

	/**
	 * 要插入的数据已存在
	 * 
	 * @return
	 */
	public static final int ALREAY_BIND = 1003;

	/**
	 * 插入失败，重试
	 * 
	 * @return
	 */
	public static final int INSERT_FAILED = 1004;

	/**
	 * 更新失败，重试
	 * 
	 * @return
	 */
	public static final int UPDATE_FAILED = 1005;

	/**
	 * 设备关机
	 * 
	 * @return
	 */
	public static final int DEVICE_OFFLINE = 1008;

	/**
	 * 设备关机
	 * 
	 * @return
	 */
	public static final int DELETE_FAILED = 1010;

	/**
	 * 指令已被消费
	 * 
	 * @return
	 */
	public static final int ALREADY_USED = 1011;

	/**
	 * 登录失败
	 * 
	 * @return
	 */
	public static final int LOGIN_FAILED = 1012;

	/**
	 * 鉴权失败
	 * 
	 * @return
	 */
	public static final int AUTH_FAILED = 1013;

	public int getCode() {
		return code;
	}

	public Result<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {

		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}

	/**
	 * 返回错误
	 * 
	 * @param code
	 * @param msg
	 */
	public Result<T> wrong(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}

	/**
	 * 返回带参成功
	 * 
	 * @param data
	 */
	public Result<T> ok(T data) {
		this.code = SUCCESS;
		this.msg = "success";
		this.data = data;
		return this;
	}

	/**
	 * 返回成功
	 */
	public Result<T> ok() {
		return ok(null);
	}

	/**
	 * 参数为空
	 * 
	 * @param msg
	 * @return
	 */
	public Result<T> paramNull() {
		return wrong(PARAMS_NULL, "参数为空");
	}

	/**
	 * 未查询到数据
	 * 
	 * @param msg
	 * @return
	 */
	public Result<T> noRecord(String msg) {
		return wrong(NO_RECORD, msg);
	}

	// public Result<T> serverError(String msg) {
	// return wrong(SERVER_ERROR, msg);
	// }

	public Result<T> serverError() {
		return wrong(SERVER_ERROR, "服务器异常");
	}

	public Result<T> alreadyExist(String msg) {
		return wrong(ALREAY_BIND, msg);
	}

	public Result<T> alreadyAttached(String msg) {
		return wrong(ALREAY_ATTACHED, msg);
	}

	public Result<T> paramsError(String msg) {
		return wrong(PARAMS_ERROR, msg);
	}

	public Result<T> insertFailed(String msg) {
		return wrong(INSERT_FAILED, msg);
	}

	public Result<T> deviceOffline(String msg) {
		// "您的车辆已离线,请开机后重试"
		return wrong(INSERT_FAILED, msg);
	}

	public Result<T> updateFailed(String msg) {
		return wrong(UPDATE_FAILED, msg);
	}

	public Result<T> deleteFailed() {
		return wrong(DELETE_FAILED, "删除失败");
	}

	public Result<T> alreadyUsed() {
		return wrong(ALREADY_USED, "错误:指令已完成，请不要重复操作");
	}

	public Result<T> pushFailed(String msg) {
		return wrong(ALREADY_USED, msg);
	}

	public Result<T> loginFailed(String msg) {
		return wrong(LOGIN_FAILED, msg);
	}

	public Result<T> authFailed(String msg) {
		return wrong(LOGIN_FAILED, msg);
	}
}
