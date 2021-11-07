package com.tanunwai.domain;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface IDao<E,K> {
	public boolean insert(E entity) throws SQLException;
	public boolean update(E entity) throws SQLException;
	public E selectObject(K key) throws SQLException;
	public List<E> selectForObject(K key) throws SQLException;
	public List<E> getAllObject() throws SQLException;
	public void setDataSource(DataSource dataSource) throws SQLException;
}