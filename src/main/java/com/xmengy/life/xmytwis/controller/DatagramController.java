package com.xmengy.life.xmytwis.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmengy.life.xmytwis.entity.LogFile;
import com.xmengy.life.xmytwis.service.DownLoadDatagramService;

@Controller
@RequestMapping("/datagram")
public class DatagramController extends BaseController {
	
	@Resource
	private DownLoadDatagramService downLoadDatagramService;
	
	@Value("${dataGram.path}")
	private String dataGramPath;
	
	@RequestMapping(value="/datagramList")
	public String datagramList(String path ,String currPath, Model model){
		if(StringUtils.isEmpty(currPath)){
			currPath = dataGramPath;
		}
		if(!StringUtils.isEmpty(path)){
			currPath = currPath + File.separator + path;
		}
		List<LogFile> fileList = downLoadDatagramService.listFiles(currPath);
		model.addAttribute("fileList",fileList);
		model.addAttribute("currPath", currPath);
		return "/datagram/datagram_list.html";
	}
	
	@RequestMapping(value="/datagramView")
	public String datagramView(String currPath,String fileName,Model model){
		currPath = currPath + File.separator + fileName;
		String datagramDetail = downLoadDatagramService.datagramView(currPath);
		model.addAttribute("datagramDetail",datagramDetail);
		model.addAttribute("currPath", currPath);
		return "/datagram/datagram_view.html";
	}
}
