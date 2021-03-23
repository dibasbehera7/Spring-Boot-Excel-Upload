package com.dibasb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/excel")
public class MasterDataController {

	@Autowired
	private MasterDataRepo masterDataRepo;
	
	@GetMapping("/datalist")
	public ResponseEntity<?> listDataAll(){
		List<MasterData> dataList = masterDataRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dataList);
		
	}
	
}
