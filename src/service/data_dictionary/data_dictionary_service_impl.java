package service.data_dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.data_dictionary.data_dictionary_dao;
import entity.data_dictionary;

@Service
public class data_dictionary_service_impl implements data_dictionary_service {

	@Resource
	data_dictionary_dao datadao;

	@Override
	public List<data_dictionary> selectCptype() {
		List<data_dictionary> list = datadao.selectCpType();
		return list;
	}

	@Override
	public List<data_dictionary> selectApptype() {
		List<data_dictionary> list = datadao.selectApptype();
		return list;
	}

}
