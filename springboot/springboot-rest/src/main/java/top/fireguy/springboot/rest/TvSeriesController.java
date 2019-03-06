package top.fireguy.springboot.rest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tvSeries")
public class TvSeriesController {
	private static final Log log = LogFactory.getLog(TvSeriesController.class);
	private static final Logger logger = LoggerFactory.getLogger(TvSeriesController.class);

	//curl -v http://127.0.0.1:8080/tvSeries/
	@GetMapping
	public List<TvSeriesDto> getAll() {
		if (log.isTraceEnabled()) {
			log.trace("getAll() start");
		}
		List<TvSeriesDto> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.OCTOBER, 2, 0, 0);
		if(log.isDebugEnabled()) {
			log.debug("calendar:" + calendar.getTime());
		}
		list.add(new TvSeriesDto(1, "WestWorld", 1, calendar.getTime()));
		calendar.set(2011, Calendar.SEPTEMBER, 22, 0, 0);
		list.add(new TvSeriesDto(2, "Person of Interest", 5, calendar.getTime()));
		if (logger.isTraceEnabled()) {
			logger.trace("list size:" + list.size());
			logger.trace("getAll() ends");
		}
		return list;
	}

	//curl -v http://127.0.0.1:8080/tvSeries/101
	//curl -v http://127.0.0.1:8080/tvSeries/102
	//curl -v http://127.0.0.1:8080/tvSeries/103
	@GetMapping("/{id}")
	public TvSeriesDto getById(@PathVariable int id) {
		if (log.isTraceEnabled()) {
			log.trace("getById():" + id);
		}
		if (id == 101) {
			return creat101();
		} else if (id == 102) {
			return creat102();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	//curl -H "Content-Type:application/json" -X POST --data '{"name":"西部世界","seasonCount":1,"originRelease":"2016-10-02"}' http://127.0.0.1:8080/tvSeries/
	@PostMapping 
	public TvSeriesDto insert(@RequestBody TvSeriesDto tvSeriesDto) {
		if (log.isTraceEnabled()) {
			log.trace("insert()"+tvSeriesDto);
		}
		tvSeriesDto.setId(103);
		log.info(tvSeriesDto.toString());
		return tvSeriesDto;
	}
	
	//curl -H "Content-Type:application/json" -X PUT --data '{"name":"西部世界","seasonCount":1,"originRelease":"2016-10-02"}' http://127.0.0.1:8080/tvSeries/101
	@PutMapping("/{id}") 
	public TvSeriesDto updateById(@PathVariable int id, @RequestBody TvSeriesDto tvSeriesDto) {
		if (log.isTraceEnabled()) {
			log.trace("updateById():"+id);
		}
		if (id == 101) {
			//TODO 更新
			return creat101();
		} else if (id == 102) {
			//TODO 更新
			return creat102();
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	//curl -X DELETE http://127.0.0.1:8080/tvSeries/101?delete_reason=duplicated
	//curl -X DELETE http://127.0.0.1:8080/tvSeries/101
	//curl -X DELETE http://127.0.0.1:8080/tvSeries/102?delete_reason=duplicated
	//curl -X DELETE http://127.0.0.1:8080/tvSeries/103?delete_reason=duplicated
	@DeleteMapping("/{id}") 
	public Map<String,String> deleteById(@PathVariable int id, HttpServletRequest request, @RequestParam(value="delete_reason",required=false)String deleteReason) {
		if (log.isTraceEnabled()) {
			log.trace("deleteById():"+id);
		}
		Map<String,String> result = new HashMap<>();
		if (id == 101) {
			//TODO delete
			result.put("message", "#101 is deleted by "+request.getRemoteAddr()+" and delete_reason is "+deleteReason);
		} else if (id == 102) {
			//RuntimeException不如org.springframework.security.access.AccessDeniedException更合适，暂时用
			result.put("message", "#102 can not be deleted!");
			throw new RuntimeException("#102 can not be deleted!");
		} else {
			throw new ResourceNotFoundException();
		}
		return result;
	}
	
	//文件上传
	//curl -v -F "photo=@poi.jpg" http://127.0.0.1:8080/tvSeries/101/photos
	@PostMapping(value="/{id}/photos",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public void addPhoto(@PathVariable int id, @RequestParam("photo")MultipartFile imgFile) throws Exception {
		if (log.isTraceEnabled()) {
			log.trace("addPhoto():"+id+"\t fileName:"+imgFile.getOriginalFilename());
		}
		FileOutputStream fos = new FileOutputStream("target/"+imgFile.getOriginalFilename());
		IOUtils.copy(imgFile.getInputStream(), fos);
		fos.close();
	}
	
	//文件download
	//curl -v -F "photo=@poi.jpg" http://127.0.0.1:8080/tvSeries/101/icon
	@GetMapping(value="/{id}/icon",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] getIcon(@PathVariable int id) throws Exception {
		if (log.isTraceEnabled()) {
			log.trace("addPhoto():"+id);
		}
		String iconFile = "src/test/resources/poi.jpg";
		InputStream is = new FileInputStream(iconFile);
		return IOUtils.toByteArray(is);
	}

	private TvSeriesDto creat101() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.OCTOBER, 2, 0, 0);
		log.debug("calendar:" + calendar.getTime());
		return new TvSeriesDto(1, "WestWorld", 1, calendar.getTime());
	}

	private TvSeriesDto creat102() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2011, Calendar.SEPTEMBER, 22, 0, 0);
		return new TvSeriesDto(2, "Person of Interest", 5, calendar.getTime());
	}

}
