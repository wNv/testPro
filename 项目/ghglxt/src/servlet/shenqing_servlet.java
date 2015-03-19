package servlet;

import gongong.CmUtil;
import gongong.DateUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TGoods;
import model.TShenqing;
import db.DB;

public class shenqing_servlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");

        if(type.endsWith("goodsList"))
        {
        	goodsList(req, res);
        }
        if(type.endsWith("toAddSq"))
        {
        	toAddSq(req, res);
        }
        if(type.endsWith("sqAdd"))
        {
        	sqAdd(req, res);
        }
        if(type.endsWith("sqMana"))
        {
        	sqMana(req, res);
        }
        if(type.endsWith("spMana"))
        {
        	spMana(req, res);
        }
        if(type.endsWith("toAddSp"))
        {
        	toAddSp(req, res);
        }
		if(type.endsWith("spAdd"))
		{
			spAdd(req, res);
		}
	}
	
	public void spAdd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = Long.parseLong(req.getParameter("id"));
		int zhuangtai = Integer.parseInt(req.getParameter("zhuangtai"));
		String huifu = req.getParameter("huifu");
		
		String sql = "update t_shenqing set zhuangtai = ?,huifu = ? where id = ?";
		
		Object[] params={zhuangtai,huifu,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "物品采购审批成功!");
		req.setAttribute("path", "shenqing?type=spMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void sqAdd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = CmUtil.getPkId();
		long goodsId = Long.parseLong(req.getParameter("goodsId"));
		String shuliang = req.getParameter("shuliang");
		String shenqingshijian = req.getParameter("shenqingshijian");
		String beizhu = req.getParameter("beizhu");
		int zhuangtai = 0;
		String huifu = "";
		
		String sql = "insert into t_shenqing values (?,?,?,?,?,?,?)";
		
		Object[] params={id,goodsId,shuliang,shenqingshijian,beizhu,zhuangtai,huifu};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "采购申请添加成功!");
		req.setAttribute("path", "shenqing?type=sqMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void goodsList(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List goodsList = new ArrayList();
		
		String sql = "select * from t_goods where del='no'";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TGoods goods = new TGoods();
				
				goods.setId(rs.getLong("id"));
				goods.setBianhao(rs.getString("bianhao"));
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setLeibie(rs.getString("leibie"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setXinghao(rs.getString("xinghao"));
				goods.setGuige(rs.getString("guige"));
				goods.setBeizhu(rs.getString("beizhu"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("admin/shenqing/goodsList.jsp").forward(req, res);		
	}

	public void toAddSq(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		TGoods goods = new TGoods();
		
		long goodsId = Long.parseLong(req.getParameter("id"));
		
		String sql = "select * from t_goods where id = ?";
		
		Object[] params={goodsId};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				goods.setId(rs.getLong("id"));
				goods.setBianhao(rs.getString("bianhao"));
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setLeibie(rs.getString("leibie"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setXinghao(rs.getString("xinghao"));
				goods.setGuige(rs.getString("guige"));
				goods.setBeizhu(rs.getString("beizhu"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		GregorianCalendar gc = new GregorianCalendar();
		String riqi = DateUtils.formatDate2Str(gc.getTime(), "yyyy-MM-dd");
		
		req.setAttribute("riqi", riqi);
		req.setAttribute("goods", goods);
		req.getRequestDispatcher("admin/shenqing/shenqingAdd.jsp").forward(req, res);	
	}

	public void sqMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List sqList = new ArrayList();
		
		String sql = "select ta.*,tb.mingcheng,tb.leibie from t_shenqing ta ,t_goods tb where ta.goods_id = tb.id";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TShenqing shenqing = new TShenqing();
				
				shenqing.setId(rs.getLong("id"));
				shenqing.setSpmc(rs.getString("mingcheng"));
				shenqing.setSplb(rs.getString("leibie"));
				shenqing.setShuliang(rs.getString("shuliang"));
				shenqing.setShenqingshijian(rs.getString("shenqingshijian"));
				shenqing.setBeizhu(rs.getString("beizhu"));
				shenqing.setStrZt(getStrZt(rs.getInt("zhuangtai")));
				
				sqList.add(shenqing);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();

		req.setAttribute("sqList", sqList);
		req.getRequestDispatcher("admin/shenqing/resultList.jsp").forward(req, res);	
	}
	
	public void spMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List sqList = new ArrayList();
		
		String sql = "select ta.*,tb.mingcheng,tb.leibie from t_shenqing ta ,t_goods tb where ta.goods_id = tb.id and ta.zhuangtai=0";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TShenqing shenqing = new TShenqing();
				
				shenqing.setId(rs.getLong("id"));
				shenqing.setSpmc(rs.getString("mingcheng"));
				shenqing.setSplb(rs.getString("leibie"));
				shenqing.setShuliang(rs.getString("shuliang"));
				shenqing.setShenqingshijian(rs.getString("shenqingshijian"));
				shenqing.setBeizhu(rs.getString("beizhu"));
				shenqing.setStrZt(getStrZt(rs.getInt("zhuangtai")));
				
				sqList.add(shenqing);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();

		req.setAttribute("sqList", sqList);
		req.getRequestDispatcher("admin/shenqing/shenpiList.jsp").forward(req, res);	
	}
	
	public void toAddSp(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		TShenqing shenqing = new TShenqing();
		
		long id = Long.parseLong(req.getParameter("id"));
		
		String sql = "select ta.*,tb.mingcheng,tb.leibie from t_shenqing ta ,t_goods tb where ta.goods_id = tb.id and ta.id=?";
		
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				shenqing.setId(rs.getLong("id"));
				shenqing.setSpmc(rs.getString("mingcheng"));
				shenqing.setSplb(rs.getString("leibie"));
				shenqing.setShuliang(rs.getString("shuliang"));
				shenqing.setShenqingshijian(rs.getString("shenqingshijian"));
				shenqing.setBeizhu(rs.getString("beizhu"));
				shenqing.setStrZt(getStrZt(rs.getInt("zhuangtai")));
				
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("shenqing", shenqing);
		req.getRequestDispatcher("admin/shenqing/shenpiAdd.jsp").forward(req, res);
	}
	
	//获取状态描述
	private String getStrZt(int zt){
		String result = "";
		switch(zt){
			case 0:
				result = "待审核";
				break;
			case 1:
				result = "审核通过";
				break;
			case 2:
				result = "审核不通过";
				break;
		}
		
		return result;
	}
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
}
