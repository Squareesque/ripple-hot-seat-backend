package com.comarch.ripplehotseat.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Level;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.repository.OfficeRepository;
import com.comarch.ripplehotseat.service.LevelService;
import com.comarch.ripplehotseat.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	public OfficeRepository officeRepository;
	@Autowired
	public LevelService levelService;
	
	@Override
	public List<Office> findAll() {
		return officeRepository.findAll();
	}

	@Override
	public Office findById(String id) {
		try {
			return officeRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public Office findByName(String name) {
		return officeRepository.findByName(name);
	}

	@Override
	public Office save(Office office) {
		return officeRepository.save(office);
	}

	@Override
	public void deleteById(String id) {
		for(Level level : levelService.findManyByOfficeId(id)) {
			levelService.deleteById(level.getId());
		}
		officeRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		officeRepository.deleteAll();
	}

	@Override
	public long count() {
		return officeRepository.count();
	}

}
