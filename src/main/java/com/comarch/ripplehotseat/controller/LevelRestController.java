package com.comarch.ripplehotseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.LevelDTO;
import com.comarch.ripplehotseat.model.Level;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.service.LevelService;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

@CrossOrigin(origins = "${origin}", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping("/levels")
public class LevelRestController {
	
	@Autowired
	public LevelService levelService;
	@Autowired
	public OfficeService officeService;
	
	@GetMapping(value="")
	public List<LevelDTO> findAll(){
		List<LevelDTO> list = ObjectMapperUtils.mapAll(levelService.findAll(), LevelDTO.class);
		for(LevelDTO levelDTO : list) {
			levelDTO.setOfficeName(officeService.findById(levelDTO.getOfficeId()).getName());
		}
		return list;
	}
	
	@GetMapping(value = "/byOfficeId/{officeId}")
	public List<LevelDTO> findManyByOfficeId(@PathVariable("officeId") String officeId){
		List<LevelDTO> list = ObjectMapperUtils.mapAll(levelService.findManyByOfficeId(officeId), LevelDTO.class);
		String officeName = officeService.findById(officeId).getName();
		for(LevelDTO levelDTO : list) {
			levelDTO.setOfficeName(officeName);
		}
		return list;
	}
	
	@GetMapping(value = "/byId/{id}")
	public LevelDTO findById(@PathVariable("id") String id) {
		LevelDTO levelDTO = ObjectMapperUtils.map(levelService.findById(id), LevelDTO.class);
		levelDTO.setOfficeName(officeService.findById(levelDTO.getOfficeId()).getName());
		return levelDTO;
	}

	@GetMapping(value = "/byOfficeName/{officeName}/byNumber/{number}")
	public LevelDTO findByOfficeNameAndNumber(@PathVariable("officeName") String officeName, @PathVariable("number") int number) {
		Office office = officeService.findByName(officeName);
		if(office == null) {
			return ObjectMapperUtils.map(null, LevelDTO.class);
		}
		for(Level level : levelService.findManyByOfficeId(office.getId())) {
			if(level.getNumber() == number) {
				LevelDTO levelDTO = ObjectMapperUtils.map(level, LevelDTO.class);
				levelDTO.setOfficeName(officeName);
				return levelDTO;
			}
		}
		return ObjectMapperUtils.map(null, LevelDTO.class);
	}
	
	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE) 
	public @ResponseBody byte[] getImage(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(levelService.findById(id), LevelDTO.class).getPicture();
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody LevelDTO levelDTO) {
		if(levelDTO.getOfficeId() == null) {
			return new ResponseEntity<String>("'officeId' is required", HttpStatus.FORBIDDEN);
		}
		if(officeService.findById(levelDTO.getOfficeId()) == null) {
			return new ResponseEntity<String>("'officeId' must be of an existing office", HttpStatus.FORBIDDEN);
		}
		for(Level level : levelService.findManyByOfficeId(levelDTO.getOfficeId())) {
			if(level.getNumber() == levelDTO.getNumber()) {
				return new ResponseEntity<String>("'number' must be unique between levels of the same office", HttpStatus.FORBIDDEN);
			}
		}
		levelDTO.setId(null);
		levelService.save(ObjectMapperUtils.map(levelDTO, Level.class));
		return new ResponseEntity<String>("Level added successfully", HttpStatus.OK);
	}
	
	@PostMapping(value = "/image/{id}")
	public ResponseEntity<String> setImage(@PathVariable("id") String id, @RequestBody byte[] image) {
		Level level = levelService.findById(id);
		level.setPicture(image);
		levelService.save(level);
		return new ResponseEntity<String>("Image set successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody LevelDTO levelDTO) {
		if(levelService.findById(id) == null) {
			return new ResponseEntity<String>("Level could not be found", HttpStatus.NOT_FOUND);
		}
		if(levelDTO.getOfficeId() == null) {
			return new ResponseEntity<String>("'officeId' is required", HttpStatus.FORBIDDEN);
		}
		if(officeService.findById(levelDTO.getOfficeId()) == null) {
			return new ResponseEntity<String>("'officeId' must be of an existing office", HttpStatus.FORBIDDEN);
		}
		for(Level level : levelService.findManyByOfficeId(levelDTO.getOfficeId())) {
			if(level.getNumber() == levelDTO.getNumber() && level.getId() != id) {
				return new ResponseEntity<String>("'number' must be unique between levels of the same office", HttpStatus.FORBIDDEN);
			}
		}
		levelDTO.setId(id);
		levelService.save(ObjectMapperUtils.map(levelDTO, Level.class));
		return new ResponseEntity<String>("Level updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		if(levelService.findById(id) == null) {
			return new ResponseEntity<String>("Level could not be found", HttpStatus.NOT_FOUND);
		}
		levelService.deleteById(id);
		return new ResponseEntity<String>("Office deleted successfully", HttpStatus.OK);
	}
	
}
