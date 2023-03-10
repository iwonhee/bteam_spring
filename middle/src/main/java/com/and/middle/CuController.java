package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import customer.CustomerVO;

@RestController
public class CuController {
	@Autowired @Qualifier("hanul") SqlSession sql;	
	// 규칙 : Customer 모듈에 관련된 맵핑은 모두 .cu로 끝낼것
	
	//delete.cu 요청시 customer테이블에서 해당 id의 정보 삭제
	@RequestMapping(value="/delete.cu", produces="text/html; charset=utf-8")
	public String delete(int id) {
		
		int result = sql.delete("cu.delete", id);	
		
		if(result==0) {
			return "해당 고객 없음!";
		}
		
		return "고객번호 ["+id+"] 의 정보 삭제";
		
	}
	
	
	@RequestMapping(value="/select.cu", produces="text/html; charset=utf-8")
	public String select() {
		List<CustomerVO> list = sql.selectList("cu.select");
		
		return new Gson().toJson(list);
	}
	
	
	
}
