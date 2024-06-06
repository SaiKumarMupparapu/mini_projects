package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.QuoteDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.ResetDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.CityEntity;
import com.example.demo.entity.CountryEntity;
import com.example.demo.entity.StateEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.CityRepo;
import com.example.demo.repo.CountryRepo;
import com.example.demo.repo.StateRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.utils.EmailUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private EmailUtils email;

	@Override
	public Map<Long, String> getCountries() {
		List<CountryEntity> list = countryRepo.findAll();
		Map<Long, String> countries = new HashMap<>();
		list.forEach(e -> {
			countries.put(e.getCountryId(), e.getCountryName());
		});
		return countries;
	}

	@Override
	public Map<Long, String> getStatesw(Long cId) {

//		List<StateEntity> list = stateRepo.getStates(cId);

//		or below way you can do it

		CountryEntity country = new CountryEntity();
		country.setCountryId(cId);
		StateEntity state = new StateEntity();
		state.setCountry(country);

		Example<StateEntity> of = Example.of(state);

		List<StateEntity> list = stateRepo.findAll(of);

		Map<Long, String> states = new HashMap<>();
		list.forEach(e -> {
			states.put(e.getStateId(), e.getStateName());

		});
		return states;
	}

	@Override
	public Map<Long, String> getCities(Long sId) {
//		List<CityEntity> list = cityRepo.getCities(sId);
//		or 
		StateEntity stateEntity = new StateEntity();
		stateEntity.setStateId(sId);

		CityEntity cityEntity = new CityEntity();
		cityEntity.setState(stateEntity);

		Example<CityEntity> of = Example.of(cityEntity);

		List<CityEntity> list = cityRepo.findAll(of);
		Map<Long, String> cities = new HashMap<>();
		list.forEach(e -> {
			cities.put(e.getCityId(), e.getCityName());
		});
		return cities;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if(userEntity==null) {
			return null;
		}else {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
		}
	}

	@Override
	public Boolean registerUser(RegisterDto rd) {
//		cpying dto data to entity in 2 ways we can do it 
//		1=>by using BeanUtils.copyProperties()
//		2=>by using 3rd party api model mapper

//		BeanUtils.copyProperties(rd, UserEntity.class);
		ModelMapper mapper = new ModelMapper();
		UserEntity entity = mapper.map(rd, UserEntity.class);
		CountryEntity countryEntity = countryRepo.findByCountryId(rd.getCountryId());
		StateEntity stateEntity = stateRepo.findByStateId(rd.getStateId());
		CityEntity cityEntity = cityRepo.findByCityId(rd.getCityId());

		entity.setCountry(countryEntity);
		entity.setState(stateEntity);
		entity.setCity(cityEntity);

		entity.setPswd(generatePassword());
		entity.setUpdatedPswd("NO");

		UserEntity saved = userRepo.save(entity);

		String to = saved.getEmail();
		String Sub = "Successfully Registred";
		String Body = "Your account was created successfully. Pleas login with this password " + saved.getPswd();

		 email.sendMail(to, Sub, Body);

		return saved.getuId() != null;
	}

	private String generatePassword() {
		String alphaNum = "abcdefghijklmnopqrstuvwxyz123456789";
		Random random = new Random();
		StringBuilder pswd = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int randomNumber = random.nextInt(alphaNum.length());
			pswd.append(alphaNum.charAt(randomNumber));
		}
		return pswd.toString();
	}

	@Override
	public UserDto loginValidate(LoginDto ld) {
		UserEntity userEntity = userRepo.findByEmailAndPswd(ld.getEmail(), ld.getPswd());
		if (userEntity == null) {
			return null;
		}
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userEntity, UserDto.class);
		return userDto;
	}

	@Override
	public Boolean resetPaswd(ResetDto rd) {
		UserEntity userEntity = userRepo.findByEmailAndPswd(rd.getEmail(),rd.getOldPswd());
		if(userEntity==null) {
			return false;
		}else {
		userEntity.setPswd(rd.getNewPswd());
		userEntity.setUpdatedPswd("YES");
		userRepo.save(userEntity);		
		return true;
		}
	}

	QuoteDto[] quotes=null;
	@Override
	public String getQuote() {
		// TODO Auto-generated method stub
		if(quotes==null) {
		String url="https://type.fit/api/quotes";
		RestTemplate rt=new RestTemplate();
		ResponseEntity<String> forEntity = rt.getForEntity(url, String.class);
		String responseBody = forEntity.getBody();
		ObjectMapper mapper=new ObjectMapper();		
		try {
			quotes = mapper.readValue(responseBody,QuoteDto[].class );
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		}
		Random random = new Random();
		int randomNum = random.nextInt(quotes.length-1);
		return quotes[randomNum].getText();
	}

}
