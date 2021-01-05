package com.rstyle.softlab.services;

import java.util.List;

public interface DAO<T> {

	List<T> all();
	T create(T entity);
	T read(Long id);
	void update(T entity);
	void delete(T entity);
}
