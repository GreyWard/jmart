package com.MichaelHardityaJmartFA.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.MichaelHardityaJmartFA.dbjson.JsonTable;
import com.MichaelHardityaJmartFA.dbjson.Serializable;

public interface BasicGetController<T extends Serializable> {
 
	@GetMapping("/{id}")
	default T getbyId(@PathVariable int id) {
		return null;
	}
	abstract JsonTable<T> getJsonTable();
	@GetMapping("/page")
	default List<T> getPage (@RequestParam int page, @RequestParam int pageSize){
		getJsonTable();
		return null;
	}
}
