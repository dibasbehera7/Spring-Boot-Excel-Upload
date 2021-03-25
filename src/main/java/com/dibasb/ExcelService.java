package com.dibasb;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

	@Autowired
	MasterDataRepo masterDataRepo;

	public void save(MultipartFile file) {
		try {
			
			
			List<MasterData> masterdatas = ExcelHelper.excelToMasterData(file.getInputStream());
			for(MasterData list: masterdatas ) {
				list.setFileName(file.getOriginalFilename());
				list.setFileContent(file.getBytes());
				list.setActionOn(new Timestamp(new Date().getTime()));
				list.setCompleteOn(new Timestamp(new Date().getTime()));
				long seconds = TimeUnit.MILLISECONDS.toSeconds(list.getCompleteOn().getTime() - list.getRequestOn().getTime());
				list.setTimeTaken(seconds+" ms");
			}
			masterDataRepo.saveAll(masterdatas);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<MasterData> getAllTutorials() {
		return masterDataRepo.findAll();
	}

}
