package entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {
	private static final long serialVersionUID=77582587775L;
	
	//������������
	public static final int LOGIN_REQ=1;
	public static final int STARTEXAM_REQ=2;
	public static final int FINISH_REQ=3;
	
	private int type;
	//���ݴ洢��map
	private Map<String,Object> parameters;
	
	public Request(int type){
		this.type=type;
		parameters=new HashMap<String,Object>();
	}
	//��Mapȥ������
	public Object getParameter(String paraName){
		return parameters.get(paraName);
	}
	
	public void addParameter(String paraName,Object paraValue){
		parameters.put(paraName, paraValue);
	}
	
	public void removeParameter(String paraName){
		parameters.remove(paraName);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}

	

