package top.fireguy.springboot.rest.pojo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 基本的POJO,使用Bean Validation注解进行校验
 * DTO:Data Transfer Object
 * @author slfu
 *
 */
public class TvSeriesDto {
	// Bean Validation 规范JSR303/Hibernate Validator
	// @Null @NotNull @Min @Max @Size @Past @Future @AssertTrue @AssertFalse @Valid
	@Null private int id;
	@NotNull private String name;
	@DecimalMin("1") private int seasonCount;
	//@Valid表示级联校验； @Size(min=2) 表示列表至少有2项内容
	@Valid @NotNull @Size(min=2) private List<TvCharacterDto> tvCharacters;
	//如果要用timestamp,用@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	@JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
	//@Past只接受过去的时间
	@Past private Date originRelease;

	public TvSeriesDto() {

	}

	public TvSeriesDto(int id, String name, int seasonCount, Date originRelease) {
		this.id = id;
		this.name = name;
		this.seasonCount = seasonCount;
		this.originRelease = originRelease;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeasonCount() {
		return seasonCount;
	}

	public void setSeasonCount(int seasonCount) {
		this.seasonCount = seasonCount;
	}

	public Date getOriginRelease() {
		return originRelease;
	}

	public void setOriginRelease(Date originRelease) {
		this.originRelease = originRelease;
	}

	public String toString() {
		return this.getClass().getName() + "\t{id:" + id + ",name:" + name + ",seasonCount:" + seasonCount
				+ ",originRelease:" + originRelease;
	}

}
