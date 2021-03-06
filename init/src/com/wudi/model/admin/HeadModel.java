package com.wudi.model.admin;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.bean.CaiHead;
import com.wudi.util.MyUtil;

public class HeadModel extends Model<HeadModel>{
	private static final long serialVersionUID = 7448105882425197123L;
	public static final HeadModel dao = new HeadModel();
	public static final String tableName = "cai_head";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public void setfsendtime(String fsendtime) {
		set("fsendtime", fsendtime);
	}
	public String getfsendtime() {
		return get("fsendtime");
	}
	public String getupdatetime() {
		return get("updatetime");
	}
	public void setupdatetime(String updatetime) {
		set("updatetime", updatetime);
	}
	public int getactive() {
		return get("active");
	}
	public void setactive(int active) {
		set("active", active);
	}
	
	public static boolean saveModel(CaiHead h) {
		HeadModel m=dao.findById(h.getExpect());
		if(m!=null) {
			m.setfsendtime(h.getFsendtime());
			m.setupdatetime(h.getUpdatetime());
			m.setactive(h.getActive());
			m.update();
		}else {
			m=new HeadModel();
			m.setfsendtime(h.getFsendtime());
			m.setupdatetime(h.getUpdatetime());
			m.setactive(h.getActive());
			m.setId(h.getExpect());
			m.save();
		}
		return true;
	}
	public static Page<HeadModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!MyUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where id like '%"+key+"%'");
		}
		from_sql.append(" ORDER BY id DESC ");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	
	public static List<HeadModel> getList(){
		String sql="select * from "+tableName+" ORDER BY fsendtime DESC";
		List<HeadModel> list=dao.find(sql);
		return list;
	}
}
