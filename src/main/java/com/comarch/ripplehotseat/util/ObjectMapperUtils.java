package com.comarch.ripplehotseat.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {
	
	public static <T1, T2> List<T1> mapAll(List<T2> list, Class<T1> class1) {
		List<T1> conv_list = new ArrayList<T1>();
		Iterator<T2> it = list.iterator();
		while(it.hasNext() == true) {
			conv_list.add(map(it.next(), class1));
		}
		return conv_list;
	}

	public static <T1, T2> T1 map(T2 object, Class<T1> class1) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(object), class1);
		} catch (JsonMappingException e) {
			System.out.println(e);
			return null;
		} catch (JsonProcessingException e) {
			System.out.println(e);
			return null;
		}
	}

}
