package com.korea.project.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.korea.project.mapper.TimeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class dbmstest {
	
	@Autowired
	public TimeMapper timeMapper;
	
	public void getTime() {
		log.info(timeMapper.getTime());
	}
	
}
