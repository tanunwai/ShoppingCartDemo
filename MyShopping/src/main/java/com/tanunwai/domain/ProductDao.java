package com.tanunwai.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tanunwai.beans.Cart;
import com.tanunwai.beans.Product;

public class ProductDao implements IDao<Product, String>{
	private DataSource dataSource;

	@Override
	public boolean insert(Product entity) throws SQLException {		
		return false;
	}

	@Override
	public boolean update(Product entity) throws SQLException {			
		return false;
	}

	@Override
	public Product selectObject(String key) throws SQLException {
		Product products=null;
		int pId=Integer.parseInt(key);
		if(dataSource==null) {
			throw new SQLException("資料來源尚未注入");
		}else {
			try(Connection con=dataSource.getConnection()){
				String sql="select * from products where id=?";
				PreparedStatement prst=con.prepareStatement(sql);
				prst.setInt(1, pId);
				ResultSet rs=prst.executeQuery();
				while(rs.next()) {
					products=new Product();
					products.setId(rs.getInt("id"));
					products.setName(rs.getString("name"));
					products.setCategory(rs.getString("category"));
					products.setPrice(rs.getDouble("price"));
					products.setImages(rs.getString("images"));
				}
			}catch(Exception e) {
				System.out.println("System Problem from pDao:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return products;
	}

	@Override
	public List<Product> selectForObject(String key) throws SQLException {
		return null;
	}

	@Override
	public void setDataSource(DataSource dataSource) throws SQLException {
		this.dataSource=dataSource;
	}

	@Override
	public List<Product> getAllObject() throws SQLException {
		List<Product> data=new ArrayList<>();
		if(dataSource == null) {
			throw new SQLException("資料來源尚未注入");
		}else {
			try(Connection con=dataSource.getConnection()){
				String sql="select * from products";
				PreparedStatement prst=con.prepareStatement(sql);
				ResultSet rs=prst.executeQuery();
				while(rs.next()) {
					Product pt=new Product();
					pt.setId(rs.getInt("id"));
					pt.setName(rs.getString("name"));
					pt.setPrice(rs.getDouble("price"));
					pt.setCategory(rs.getString("category"));
					pt.setImages(rs.getString("images"));
					data.add(pt);
				}
			}catch(Exception e) {
				throw new SQLException("System Error;Beacuase:"+e.getMessage());				
			}
		}
		return data;
	}
	/*get the total price*/
	public double getTotalPrice(List<Cart> listCart) throws Exception {
		double sum=0;
		if(dataSource==null) {
			throw new SQLException("資料來源尚未注入");
		}else {
			try (Connection con=dataSource.getConnection()){
				if(listCart.size()>0) {
					for(Cart items:listCart) {
						String sql="select * from products where id=?";
						PreparedStatement prst=con.prepareStatement(sql);
						prst.setInt(1, items.getId());
						ResultSet rs=prst.executeQuery();
						while(rs.next()) {
							sum+=rs.getDouble("price")*items.getQuantity();
						}
					}
				}
			}catch(Exception e) {
				System.out.println("Error for get Price:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return sum;				
	}
	/*get the history of CarList*/
	public List<Cart> getProducts(List<Cart> cartList) throws Exception{
		List<Cart> products=new ArrayList<>();
		if(dataSource==null) {
			throw new SQLException("資料來源尚未注入!!!");
		}else {
			if(cartList.size()>0) {
				for(Cart items:cartList) {
					String sql="select * from products where id=?";
					try(Connection con=dataSource.getConnection()){
						PreparedStatement prst=con.prepareStatement(sql);
						prst.setInt(1, items.getId());
						ResultSet rs=prst.executeQuery();
						while(rs.next()) {
							Cart ct=new Cart();
							ct.setId(rs.getInt("id"));
							ct.setName(rs.getString("name"));
							ct.setCategory(rs.getString("category"));
							ct.setPrice(rs.getDouble("price")*items.getQuantity());
							ct.setQuantity(items.getQuantity());
							products.add(ct);
						}
					}catch(Exception e) {
						System.out.println("System Error:Beacuase:"+e.getMessage());
						e.printStackTrace();
					}
				}
			}			
		}
		return products;
	}
}
