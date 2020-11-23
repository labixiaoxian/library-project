package com.wyu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyu.dto.EchartSeries;
import com.wyu.dto.EchartSeriesData;
import com.wyu.entity.Country;
import com.wyu.entity.Theme;
import com.wyu.entity.Type;
import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import com.wyu.service.BookService;
import com.wyu.service.CountryService;
import com.wyu.service.ThemeService;
import com.wyu.service.TypeService;
import com.wyu.service.user.UserInfoService;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by XiaoXian on 2020/11/21.
 */

@Api(value = "首页统计", tags = { "用于首页统计的相关接口" })
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private ThemeService themeService;

	/**
	 * 统计用户数与图书数
	 * 
	 * @return
	 */
	@ApiOperation(notes = "统计用户数与图书数", value = "统计用户数与图书数")
	@GetMapping(value = "/getuserandbookcount")
	public WriteBack getUserCount() {
		WriteBack writeBack = new WriteBack();
		UserInfo userInfo = new UserInfo();
		User user = new User();
		user.setStatus(1);
		userInfo.setUser(user);

		int userCount = 0;
		int bookCount = 0;
		try {
			// 获取用户数量
			userCount = userInfoService.queryUserInfoCount(userInfo);
			// 获取图书数量
			bookCount = bookService.queryDivPageCount(null, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("userCount", userCount);
		map.put("bookCount", bookCount);
		WriteBackUtil.setSuccess(writeBack);
		writeBack.setData(map);
		return writeBack;
	}

	/**
	 * 统计国家类别
	 * 
	 * @return
	 */
	@ApiOperation(notes = "统计国家类别", value = "统计国家类别")
	@GetMapping(value = "/calcountry")
	public WriteBack calCountry() {
		WriteBack writeBack = new WriteBack();
		Map<String, Object> map = new HashMap<>();
		List<Country> countries = countryService.queryAll();
		LinkedHashSet<Integer> countryIdList = new LinkedHashSet<>();
		LinkedHashSet<String> nameList = new LinkedHashSet<>();
		List<EchartSeriesData> echartSeriesDataList = new ArrayList<>();

		// 遍历国家获取所有国家名称
		for (int i = 0; i < countries.size(); i++) {
			countryIdList.add(countries.get(i).getId());
			nameList.add(countries.get(i).getCountryName());
		}
		List<Country> countryList = new ArrayList<>();
		Iterator<Integer> iterator = countryIdList.iterator();
		while (iterator.hasNext()) {
			Country country = countryService.queryById(iterator.next());
			countryList.add(country);
		}

		// 统计数量
		for (int i = 0; i < countryList.size(); i++) {
			int count = bookService.queryDivPageCount(null, countryList.get(i).getId(), null, null, null);
			EchartSeriesData echartSeriesData = new EchartSeriesData();
			echartSeriesData.setName(countryList.get(i).getCountryName());
			echartSeriesData.setValue(count);
			echartSeriesDataList.add(echartSeriesData);
		}
		map.put("legendData", nameList);
		map.put("echartSeriesDataList", echartSeriesDataList);
		WriteBackUtil.setSuccess(writeBack);
		writeBack.setData(map);
		return writeBack;
	}

	/**
	 * 统计类别
	 * 
	 * @return
	 */
	@ApiOperation(notes = "统计类别", value = "统计类别")
	@GetMapping(value = "/caltype")
	public WriteBack calType() {
		WriteBack writeBack = new WriteBack();
		Map<String, Object> map = new HashMap<>();
		List<Type> types = typeService.queryAll();
		LinkedHashSet<Integer> typeIdList = new LinkedHashSet<>();
		LinkedHashSet<String> nameList = new LinkedHashSet<>();
		List<EchartSeriesData> echartSeriesDataList = new ArrayList<>();

		// 遍历国家获取所有国家名称
		for (int i = 0; i < types.size(); i++) {
			typeIdList.add(types.get(i).getId());
			nameList.add(types.get(i).getTypeName());
		}
		List<Type> typeList = new ArrayList<>();
		Iterator<Integer> iterator = typeIdList.iterator();
		while (iterator.hasNext()) {
			Type type = typeService.queryById(iterator.next());
			typeList.add(type);
		}

		// 统计数量
		for (int i = 0; i < typeList.size(); i++) {
			int count = bookService.queryDivPageCount(null, typeList.get(i).getId(), null, null, null);
			EchartSeriesData echartSeriesData = new EchartSeriesData();
			echartSeriesData.setName(typeList.get(i).getTypeName());
			echartSeriesData.setValue(count);
			echartSeriesDataList.add(echartSeriesData);
		}
		map.put("legendData", nameList);
		map.put("echartSeriesDataList", echartSeriesDataList);
		WriteBackUtil.setSuccess(writeBack);
		writeBack.setData(map);
		return writeBack;
	}

	/**
	 * 统计主题
	 * 
	 * @return
	 */
	@ApiOperation(notes = "统计主题", value = "统计主题")
	@GetMapping(value = "/caltheme")
	public WriteBack calTheme() {
		WriteBack writeBack = new WriteBack();
		Map<String, Object> map = new HashMap<>();
		List<Theme> themes = themeService.queryAll();
		LinkedHashSet<Integer> themeIdList = new LinkedHashSet<>();
		LinkedHashSet<String> nameList = new LinkedHashSet<>();
		List<EchartSeriesData> echartSeriesDataList = new ArrayList<>();

		// 遍历国家获取所有国家名称
		for (int i = 0; i < themes.size(); i++) {
			themeIdList.add(themes.get(i).getId());
			nameList.add(themes.get(i).getThemeName());
		}
		List<Theme> themeList = new ArrayList<>();
		Iterator<Integer> iterator = themeIdList.iterator();
		while (iterator.hasNext()) {
			Theme theme = themeService.queryById(iterator.next());
			themeList.add(theme);
		}

		// 统计数量
		for (int i = 0; i < themeList.size(); i++) {
			int count = bookService.queryDivPageCount(null, themeList.get(i).getId(), null, null, null);
			EchartSeriesData echartSeriesData = new EchartSeriesData();
			echartSeriesData.setName(themeList.get(i).getThemeName());
			echartSeriesData.setValue(count);
			echartSeriesDataList.add(echartSeriesData);
		}
		// map.put("legendData",nameList);
		map.put("echartSeriesDataList", echartSeriesDataList);
		WriteBackUtil.setSuccess(writeBack);
		writeBack.setData(map);
		return writeBack;
	}

	/**
	 * 统计年龄段
	 * 
	 * @return
	 */
	@ApiOperation(notes = "统计年龄段", value = "统计年龄段")
	@GetMapping(value = "/calage")
	public WriteBack calAge() {
		WriteBack writeBack = new WriteBack();
		Map<String, Object> map = new HashMap<>();
		UserInfo userInfo = new UserInfo();
		User user = new User();
		user.setStatus(1);
		userInfo.setUser(user);
		// 查询所有用户
		List<UserInfo> userInfoList = userInfoService.findUserInfoList(userInfo, 0, 999999);
		EchartSeries echartSeries = new EchartSeries();
		echartSeries.setName("该年龄段的人数");
		int count[] = new int[7];
		LinkedList<Integer> counts = new LinkedList<>();
		// 统计每个年龄段的数量
		for (UserInfo item : userInfoList) {
			Integer age = new Integer(item.getAge());
			if (!ObjectUtils.isEmpty(age)) {
				// 0~10岁
				if (age > 0 && age <= 10) {
					count[0] += 1;
				} else if (age > 10 && age <= 20) {// 11~20岁
					count[1] += 1;
				} else if (age > 20 && age <= 30) {// 21~30岁
					count[2] += 1;
				} else if (age > 30 && age <= 40) {// 31~40岁
					count[3] += 1;
				} else if (age > 40 && age <= 50) {// 41~50岁
					count[4] += 1;
				} else if (age > 50 && age <= 60) {// 51~60岁
					count[5] += 1;
				} else if (age > 60) {// 大于60岁
					count[6] += 1;
				}
			}
		}

		for (int i = 0; i < 7; i++) {
			counts.add(new Integer(count[i]));
		}
		echartSeries.setData(counts);
		map.put("series", echartSeries);
		WriteBackUtil.setSuccess(writeBack);
		writeBack.setData(map);
		return writeBack;
	}

}
