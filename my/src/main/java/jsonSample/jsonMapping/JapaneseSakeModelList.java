package jsonSample.jsonMapping;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * jsonデータをこのクラスにマッピングする
 * 日本酒に関する全ての情報
 */
// JapaneseSakeModelクラス内に定義されてないデータがjsonデータにあった場合、無視する
@JsonIgnoreProperties(ignoreUnknown = true)
public class JapaneseSakeModelList {
	// 日本酒 ※根ノード(木構造のルート）。json全体
	@JsonProperty("sake")
	private List<JapaneseSakeModel> japaneseSakeList;

	public List<JapaneseSakeModel> getJapaneseSakeList() {
		return japaneseSakeList;
	}

	/**
	 * 1つのsake（日本酒）をマッピングする
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class JapaneseSakeModel {
		// 銘柄
		@JsonProperty("銘柄")
		private String sakeName;
		// 酒蔵の情報
		@JsonProperty("製造元")
		private SakeBreweryInfo sakeBreweryInfo;
		// 原材料
		@JsonProperty("原材料・精米歩合")
		private List<String> materials;

		public String getSakeName() {
			return sakeName;
		}

		public SakeBreweryInfo getSakeBreweryInfo() {
			return sakeBreweryInfo;
		}

		public List<String> getMaterials() {
			return materials;
		}

		/**
		 * 酒蔵の情報
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		static class SakeBreweryInfo {
			// 蔵元名
			@JsonProperty("蔵名")
			private String breweryName;
			// 県
			@JsonProperty("県名")
			private String prefecture;
			
			public String getBreweryName() {
				return breweryName;
			}

			public String getPrefecture() {
				return prefecture;
			}
		}
	}
}
