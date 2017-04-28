package cn.net.xulian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.net.xulian.service.UserService;
import cn.net.xulian.util.ObjectResponse;

@RestController
public class WebController {
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @param type  查询的种类
	 * @param queryId 查询id
	 * @return
	 */
	@RequestMapping(value = "lifeTool/findById", method = RequestMethod.GET)
	@ResponseBody
	public ObjectResponse<JSONObject> getMetadataById(@RequestParam("type") String type,@RequestParam("id") String queryId){
		ObjectResponse<JSONObject> objResp = new ObjectResponse<JSONObject>();
		JSONObject json = new JSONObject();
		String tag = null;  //表明查询的对象
		Object data = null;
		if(type.equals("user")){
			System.out.println("---------"+userService.findUserInfoById(queryId));
			data = userService.findUserInfoById(queryId);
			tag = type;
		}
		if(data==null){
			objResp.setCode(false);
			objResp.setMsg("查询"+type+"失败！");
		}else{
			objResp.setCode(true);
			objResp.setMsg("查询成功！");
			json.put(tag, (JSONObject) JSONObject.toJSON(data));
		}
	System.out.println(objResp.getData());
		return objResp;
	}
}
