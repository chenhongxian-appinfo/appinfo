package service.data_dictionary;

import java.util.List;

import entity.data_dictionary;

public interface data_dictionary_service {

	public List<data_dictionary> selectCptype();

	public List<data_dictionary> selectApptype();
}
