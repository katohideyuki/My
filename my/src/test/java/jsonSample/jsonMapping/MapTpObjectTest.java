package jsonSample.jsonMapping;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jsonSample.jsonMapping.JapaneseSakeModelList.JapaneseSakeModel;
import jsonSample.jsonMapping.JapaneseSakeModelList.JapaneseSakeModel.SakeBreweryInfo;

@DisplayName("MapToObjectクラスは根ノードが'sake'オブジェクトで構成されたjsonデータをマッピングする")
public class MapTpObjectTest {

	private static final String JSON_DATA = """
			{
			  "sake": [
			    {
			      "銘柄": "残草蓬莱",
			      "製造元": {
			        "蔵名": "大矢考酒造",
			        "県名": "神奈川"
			      },
			      "原材料・精米歩合": [
			        "国産米",
			        "60%"
			      ],
			      "値段": "不明"
			    },
			    {
			      "銘柄": "風の森",
			      "製造元": {
			        "蔵名": "油田酒造",
			        "県名": "奈良"
			      },
			      "原材料・精米歩合": [
			        "国産米",
			        "80%"
			      ],
			      "値段": "2,000円以下"
			    }
			  ]
			}""";

	@Nested
	@TestInstance(Lifecycle.PER_CLASS) /* フィールドを複数のテストメソッドで共有する */
	static class MapToObjectクラスのテスト {

		private MapToObject m;
		private List<JapaneseSakeModel> list;

		/**
		 * 事前準備
		 */
		@BeforeAll
		private void initAll() {
			m = new MapToObject();
			list = m.getJapaneseSakeModelList(JSON_DATA);
		}

		@Test
		void MapToObjectクラスから値を一つ目の値を取得する() {
			assumeTrue(!list.isEmpty(), "日本酒モデルが空のためテスト中断");
			JapaneseSakeModel model = list.get(0);
			SakeBreweryInfo info = model.getSakeBreweryInfo();
			assertEquals(model.getSakeName(), "残草蓬莱");
			assertEquals(info.getBreweryName(), "大矢考酒造");
			assertEquals(info.getPrefecture(), "神奈川");
			assertEquals(model.getMaterials().get(0), "国産米");
			assertEquals(model.getMaterials().get(1), "60%");
		}
		
		@Test
		void MapToObjectクラスから値を二つ目の値を取得する() {
			assumeTrue(!list.isEmpty(), "日本酒モデルが空のためテスト中断");
			JapaneseSakeModel model = list.get(1);
			SakeBreweryInfo info = model.getSakeBreweryInfo();
			assertEquals(model.getSakeName(), "風の森");
			assertEquals(info.getBreweryName(), "油田酒造");
			assertEquals(info.getPrefecture(), "奈良");
			assertEquals(model.getMaterials().get(0), "国産米");
			assertEquals(model.getMaterials().get(1), "80%");
		}
	}
}
