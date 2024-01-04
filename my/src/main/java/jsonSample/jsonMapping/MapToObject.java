package jsonSample.jsonMapping;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jsonSample.jsonMapping.JapaneseSakeModelList.JapaneseSakeModel;

/**
 * 
 */
public class MapToObject {
	
	/**
	 * @param jsonData
	 * @return Optionalに包まれたマッピング済みのjsonオブジェクト
	 */
	public List<JapaneseSakeModel> getJapaneseSakeModelList(String jsonData) {
		JapaneseSakeModelList japaneseSakeModelList;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// jsonデータをオブジェクトにマッピング
			japaneseSakeModelList = objectMapper.readValue(jsonData,
					JapaneseSakeModelList.class);
			return japaneseSakeModelList.getJapaneseSakeList();
		} catch (JsonProcessingException e) {
			// JSONの形式が正しくない場合やマッピングできないフィールドが存在する場合など
			System.out.println("処理失敗");
			e.printStackTrace();
		}
		return new JapaneseSakeModelList().getJapaneseSakeList();
	}
}
