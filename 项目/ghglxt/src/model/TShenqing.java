package model;

public class TShenqing {
	private long id;
	private long goodsId;
	private String shuliang;
	private String shenqingshijian;
	private String beizhu;
	private long zhuangtai;
	private String huifu;
	
	private String strZt;	//状态描述
	private String spmc;	//商品名称
	private String splb;	//商品类别
	
	private long sfcg;		//是否已采购 0未采购 非0表示已采购
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	public String getShuliang() {
		return shuliang;
	}
	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}
	public String getShenqingshijian() {
		return shenqingshijian;
	}
	public void setShenqingshijian(String shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public long getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(long zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public String getHuifu() {
		return huifu;
	}
	public void setHuifu(String huifu) {
		this.huifu = huifu;
	}
	public String getStrZt() {
		return strZt;
	}
	public void setStrZt(String strZt) {
		this.strZt = strZt;
	}
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	public String getSplb() {
		return splb;
	}
	public void setSplb(String splb) {
		this.splb = splb;
	}
	public long getSfcg() {
		return sfcg;
	}
	public void setSfcg(long sfcg) {
		this.sfcg = sfcg;
	}
}
