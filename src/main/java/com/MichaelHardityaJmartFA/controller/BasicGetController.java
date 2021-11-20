package com.MichaelHardityaJmartFA.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.MichaelHardityaJmartFA.dbjson.JsonTable;
import com.MichaelHardityaJmartFA.dbjson.Serializable;

public interface BasicGetController<T extends Serializable> {
 
	@GetMapping("/{id}")
	default T getbyId(int id) {

		return null;
	}
	abstract JsonTable<T> getJsonTable();
	@GetMapping("/page")
	default List<T> getPage (int page, int pageSize){
		return null;
	}
}
