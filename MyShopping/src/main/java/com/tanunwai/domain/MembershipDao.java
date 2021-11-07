package com.tanunwai.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tanunwai.beans.MembershipBeans;

public class MembershipDao implements IDao<MembershipBeans, String> {
	private DataSource dataSource;	

	@Override
	public boolean insert(MembershipBeans entity) throws SQLException {
		boolean isInsert=true;	
		if(entity.getUserName().equals("")||entity.getLoginpass().equals("")
				||entity.getRealName().equals("")||entity.getEmail().equals("")
				|| entity.getPhone().equals("") ||entity.getSex().equals("")) 
		
		{
			isInsert=false;
			return isInsert;
		}
		if (dataSource == null) {
			throw new SQLException("資料來源尚未注入");
		} else {
			try (Connection con = dataSource.getConnection()) {				
				String sql = "insert into sakila.memberships(username,password,realname,email,phone,sex) values(?,?,?,?,?,?)";
				PreparedStatement preStm = con.prepareStatement(sql);
				preStm.setString(1, entity.getUserName());
				preStm.setString(2, entity.getLoginpass());
				preStm.setString(3, entity.getRealName());
				preStm.setString(4, entity.getEmail());
				preStm.setString(5, entity.getPhone());
				preStm.setString(6, entity.getSex());
				preStm.executeUpdate();				
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
			return isInsert;
		}
		
	}

	@Override
	public boolean update(MembershipBeans entity) throws SQLException {
		boolean isUpdate=true;	
		if(entity.getUserName().equals("")||entity.getLoginpass().equals("")
				||entity.getRealName().equals("")||entity.getEmail().equals("")
				|| entity.getPhone().equals("") ||entity.getSex().equals("")
				|| entity.getBirthday().equals("") || entity.getAddress().equals("")) 
		
		{
			isUpdate=false;
			return isUpdate;
		}
		if (dataSource == null) {
			throw new SQLException("資料來源尚未注入");
		} else {
			try (Connection con = dataSource.getConnection()) {
				String sql = "update sakila.memberships set username=?,password=?,realname=?,email=?,phone=?,sex=?,birthday=?,address=? where memberid=?";
				PreparedStatement preStm = con.prepareStatement(sql);
				preStm.setString(1, entity.getUserName());
				preStm.setString(2, entity.getLoginpass());
				preStm.setString(3, entity.getRealName());
				preStm.setString(4, entity.getEmail());
				preStm.setString(5, entity.getPhone());
				preStm.setString(6, entity.getSex());
				preStm.setString(7, entity.getBirthday());
				preStm.setString(8, entity.getAddress());
				preStm.setInt(9, entity.getMemberId());
				preStm.executeUpdate();
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
		}
		return isUpdate;
	}

	@Override
	public MembershipBeans selectObject(String key) throws SQLException {
		MembershipBeans member = null;
		if (dataSource == null) {
			throw new SQLException("資料來源尚未注入");
		} else {
			String[] items = key.split(",");
			try (Connection con = dataSource.getConnection()) {
				String sql = "select count(*) as counter from sakila.memberships where username=? and password=?";
				PreparedStatement preStm = con.prepareStatement(sql);
				preStm.setString(1, items[0]);
				preStm.setString(2, items[1]);
				ResultSet rs = preStm.executeQuery();
				rs.next();
				if (rs.getInt("counter") > 0) {
					member = new MembershipBeans();
					member.setUserName(items[0]);
					member.setLoginpass(items[1]);
				}
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
		}
		return member;
	}

	@Override
	public List<MembershipBeans> selectForObject(String key) throws SQLException {
		List<MembershipBeans> data = new ArrayList<>();
		if (dataSource == null) {
			throw new SQLException("資料來源尚未注入");
		} else {
			try (Connection con = dataSource.getConnection()) {
				String sql = "select memberid,username,password,realname,email,phone,sex,birthday,address from sakila.memberships where username=?";
				PreparedStatement preStm = con.prepareStatement(sql);
				preStm.setString(1, key);
				ResultSet rs = preStm.executeQuery();
				while (rs.next()) {
					MembershipBeans member = new MembershipBeans();
					member.setMemberId(rs.getInt("memberid"));
					member.setUserName(rs.getString("username"));
					member.setLoginpass(rs.getString("password"));
					member.setRealName(rs.getString("realname"));
					member.setEmail(rs.getString("email"));
					member.setPhone(rs.getString("phone"));
					member.setSex(rs.getString("sex"));
					member.setBirthday(rs.getString("birthday"));
					member.setAddress(rs.getString("address"));
					data.add(member);
				}
			}
		}
		return data;
	}

	@Override
	public void setDataSource(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	@Override
	public List<MembershipBeans> getAllObject() throws SQLException {
		return null;
	}

}