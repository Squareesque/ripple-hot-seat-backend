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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.OfficeDTO;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

@RestController
@CrossOrigin("https://ripple-hot-seat-backend-app.herokuapp.com")
@RequestMapping("/offices")
public class OfficeRestController {

	@Autowired
	public OfficeService officeService;
	
	@GetMapping(value="")
	public List<OfficeDTO> findAll() {
		return ObjectMapperUtils.mapAll(officeService.findAll(), OfficeDTO.class);
	}
	
	@GetMapping(value = "/byId/{id}")
	public OfficeDTO findById(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(officeService.findById(id), OfficeDTO.class);
	}
	
	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE) 
	public @ResponseBody byte[] getImage(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(officeService.findById(id), OfficeDTO.class).getPicture();
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody OfficeDTO officeDTO) {
		officeDTO.setId(null);
		officeService.save(ObjectMapperUtils.map(officeDTO, Office.class));
		return new ResponseEntity<String>("Office added successfully", HttpStatus.OK);
	}
	
	@PostMapping(value = "/image/{id}")
	public ResponseEntity<String> setImage(@PathVariable("id") String id, @RequestBody byte[] image) {
		Office office = officeService.findById(id);
		office.setPicture(image);
		officeService.save(office);
		return new ResponseEntity<String>("Image set successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody OfficeDTO officeDTO) {
		if(officeService.findById(id) == null) {
			return new ResponseEntity<String>("Office could not be found", HttpStatus.NOT_FOUND);
		}
		officeDTO.setId(id);
		officeService.save(ObjectMapperUtils.map(officeDTO, Office.class));
		return new ResponseEntity<String>("Office updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		if(officeService.findById(id) == null) {
			return new ResponseEntity<String>("Office could not be found", HttpStatus.NOT_FOUND);
		}
		officeService.deleteById(id);
		return new ResponseEntity<String>("Office deleted successfully", HttpStatus.OK);
	}
	
}
