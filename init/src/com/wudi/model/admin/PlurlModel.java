package com.wudi.model.admin;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.wudi.bean.Plurl;
import com.wudi.util.StringUtil;

/**
 * 
* @ClassName: PlurlModel
* @Description: TODO 表模型
* @author xiao
* @date 2018年11月29日下午2:45:13
*
 */
public class PlurlModel extends Model<PlurlModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "plurl";
	public static final PlurlModel dao = new PlurlModel();
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public double getValue() {
		return get("value");
	}
	public void setValue(double value) {
		set("value", value);
	}
	public int getOrdernum() {
		return get("ordernum");
	}
	public void setOrdernum(int ordernum) {
		set("ordernum", ordernum);
	}
	public int getExpect() {
		return get("expect");
	}
	public void setExpect(int expect) {
		set("expect", expect);
	}
	public int getResult() {
		return get("result");
	}
	public void setResult(int result) {
		set("result", result);
	}
	public int getSpf() {
		return get("spf");
	}
	public void setSpf(int spf) {
		set("spf", spf);
	}
	public int getPei() {
		return get("pei");
	}
	public void setPei(int pei) {
		set("pei", pei);
	}
	
	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static PlurlModel getModeById(String id){
		return dao.findById(id);
	}
	/**
	 * 查询列表
	* @Title: getList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    参数
	* @return List<NavsModel>    返回类型
	* @throws
	 */
		public static List<PlurlModel> getList() {
			String sql="select * from "+tableName+"";
			List<PlurlModel> list=dao.find(sql);
			return list;
		}
		public static PlurlModel getModel(String expect) {
			String sql="select * from "+tableName+" where expect=?";
			PlurlModel m=dao.findFirst(sql,expect);
			return m;
		}
		/**
		 * 保存
		* @Description:???
		* @return boolean    返回类型
		* @throws
		 */
	public static boolean saveModel(double value,int ordernum,int expect,int result,int pei,int spf) {
		PlurlModel m=new PlurlModel();
		m.setValue(value);
		m.setOrdernum(ordernum);
		m.setExpect(expect);
		m.setResult(result);
		m.setPei(pei);
		m.setSpf(spf);
		return m.save();
	}
	public static boolean saveList(String expect,List<Plurl> list) {
		PlurlModel model=getModel(expect);
		if(model!=null) {
			//先删除掉,再保存
			String sql="delete from "+tableName+" where expect=?";
			Db.delete(sql, expect);
		}
		List<PlurlModel> pmlist=new ArrayList<>();
		for(Plurl p:list) {
			PlurlModel m=new PlurlModel();
			m.setExpect(p.getExpect());
			m.setOrdernum(p.getOrdernum());
			m.setPei(p.getPei());
			m.setResult(p.getResult());
			m.setSpf(p.getSpf());
			m.setValue(p.getValue());
			m.setId(StringUtil.getId());
			pmlist.add(m);
		}
		
		 Db.batchSave(pmlist, 100);
		
		return true;
	}
}