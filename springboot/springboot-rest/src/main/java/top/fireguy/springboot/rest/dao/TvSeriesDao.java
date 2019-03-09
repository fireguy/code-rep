package top.fireguy.springboot.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import top.fireguy.springboot.rest.pojo.TvSeries;


public interface TvSeriesDao {
	@Select("select * from tv_series")
	public List<TvSeries> getAll();

}
