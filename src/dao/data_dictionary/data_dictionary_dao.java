package dao.data_dictionary;

import java.util.List;

import entity.data_dictionary;

public interface data_dictionary_dao {

	public List<data_dictionary> selectCpType();

	public List<data_dictionary> selectApptype();
}
