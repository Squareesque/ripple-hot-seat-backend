package com.comarch.ripplehotseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.OfficeDTO;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

//@CrossOrigin(origins = "${origin}", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
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
	
	@GetMapping(value = "/byName/{name}")
	public OfficeDTO findByName(@PathVariable("name") String name) {
		return ObjectMapperUtils.map(officeService.findByName(name), OfficeDTO.class);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody OfficeDTO officeDTO) {
		if(officeDTO.getName() == null || officeDTO.getName().isBlank()) {
			return new ResponseEntity<String>("'name' is required", HttpStatus.FORBIDDEN);
		}
		if(officeService.findByName(officeDTO.getName()) != null) {
			return new ResponseEntity<String>("'name' must be unique", HttpStatus.FORBIDDEN);
		}
		officeDTO.setId(null);
		officeService.save(ObjectMapperUtils.map(officeDTO, Office.class));
		return new ResponseEntity<String>("Office added successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody OfficeDTO officeDTO) {
		if(officeService.findById(id) == null) {
			return new ResponseEntity<String>("Office could not be found", HttpStatus.NOT_FOUND);
		}
		if(officeDTO.getName() == null || officeDTO.getName().isBlank()) {
			return new ResponseEntity<String>("'name' is required", HttpStatus.FORBIDDEN);
		}
		if(officeService.findByName(officeDTO.getName()) != null) {
			return new ResponseEntity<String>("'name' must be unique", HttpStatus.FORBIDDEN);
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
