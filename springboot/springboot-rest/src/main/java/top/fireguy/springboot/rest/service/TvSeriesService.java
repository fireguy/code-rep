package top.fireguy.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.fireguy.springboot.rest.dao.TvSeriesDao;
import top.fireguy.springboot.rest.pojo.TvSeries;

@Service
public class TvSeriesService {
	@Autowired TvSeriesDao tvSeriesDao;
	
	public List<TvSeries> getAllTvSeries(){
		return tvSeriesDao.getAll();
	}

}
