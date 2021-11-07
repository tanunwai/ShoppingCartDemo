package com.tanunwai.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tanunwai.beans.Order;
import com.tanunwai.beans.Product;

public class OrderDao implements IDao<Order,String>{
	private DataSource dataSource;
	
	@Override
	public boolean insert(Order entity) throws SQLException {
		boolean isInsert=false;
		if(dataSource==null) {
			isInsert=false;
			throw new SQLException("資料來源尚未注入!!!");
		}else {
			String sql="insert into sakila.orders(p_id,u_id,o_quantity,o_date) value(?,?,?,?)";
			try(Connection con=dataSource.getConnection()){
				PreparedStatement prst=con.prepareStatement(sql);
				prst.setInt(1, entity.getId());
				prst.setInt(2, entity.getUid());
				prst.setInt(3, entity.getQuantity());
				prst.setString(4, entity.getCreateDate());
				prst.executeUpdate();
				isInsert=true;
			}
		}
		return isInsert;
	}

	@Override
	public boolean update(Order entity) throws SQLException {
		return false;
	}

	@Override
	public Order selectObject(String key) throws SQLException {
		return null;
	}

	@Override
	public List<Order> selectForObject(String key) throws SQLException {
		List<Order> orderData=new ArrayList<>();
		int keyId=Integer.parseInt(key);
		if(dataSource==null) {
			throw new SQLException("資料來源尚未注入");
		}else {
			try(Connection con=dataSource.getConnection()){
				String sql="select * from orders where u_id=? order by orders.o_id desc";
				PreparedStatement prst=con.prepareStatement(sql);
				prst.setInt(1, keyId);
				ResultSet rs=prst.executeQuery();
				while(rs.next()) {
					Order oModel=new Order();
					int pId=rs.getInt("p_id");
					ProductDao pDao=new ProductDao();
					pDao.setDataSource(dataSource);
					Product products=pDao.selectObject(String.valueOf(pId));
					oModel.setOrderId(rs.getInt("o_id"));
					oModel.setId(pId);
					oModel.setName(products.getName());
					oModel.setCategory(products.getCategory());
					oModel.setPrice(products.getPrice()*rs.getInt("o_quantity"));
					oModel.setQuantity(rs.getInt("o_quantity"));
					oModel.setCreateDate(rs.getString("o_date"));
					orderData.add(oModel);
				}
			}
		}
		return orderData;
	}

	@Override
	public List<Order> getAllObject() throws SQLException {
		return null;
	}

	@Override
	public void setDataSource(DataSource dataSource) throws SQLException {
		this.dataSource=dataSource;
	}
	
	public double getTotalOrderPrice(List<Order> ordersList) throws SQLException {
		double sum=0;
		if(dataSource==null) {
			throw new SQLException("資料來源尚未注入");
		}else {
			try(Connection con=dataSource.getConnection()){				
				if(ordersList.size()>0) {
					for(Order items:ordersList) {
						String sql="select * from orders where o_id=?";
						PreparedStatement prst=con.prepareStatement(sql);
						prst.setInt(1, items.getOrderId());
						ResultSet rs=prst.executeQuery();
						while(rs.next()) {
							sum+=rs.getInt("o_quantity")*items.getPrice();
						}
					}
				}
			}catch(Exception e) {
				System.out.println("Error Problem in orderPrice;Beacause:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return sum;
	}

	public void cancelOrder(int id) throws SQLException {
		if(dataSource==null) {
			throw new SQLException("資料來源尚未注入");
		}else {
			try(Connection con=dataSource.getConnection()){
				String sql="delete from orders where o_id=?";
				PreparedStatement prst=con.prepareStatement(sql);
				prst.setInt(1, id);
				prst.execute();
			}catch(Exception e){
				System.out.println("System Problem in oDao;Beacuse:"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
