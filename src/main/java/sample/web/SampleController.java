package sample.web;

import com.nexacro.uiadapter17.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter17.spring.core.data.NexacroResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.service.SampleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController {

	private Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Resource(name = "sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value = "/selectSampleList.do")
	public NexacroResult selectSampleList(
			@ParamDataSet(name = "input1", required = false) Map<String, String> ds_search ) {
		
		logger.debug("[ds_search]  " + ds_search);
		
		List<Map<String, Object>> sampleList = null;
		sampleList = sampleService.selectSampleList(ds_search);
		
		NexacroResult result = new NexacroResult();

		List<Map<String, Object>> tmpList = new ArrayList<>();
		Map<String, Object> tmpMap = new HashMap<>();
		tmpMap.put("name", "name1");
		tmpMap.put("id", "id1");
		tmpMap.put("description", "desc1");
		tmpMap.put("useYn", "Y");
		tmpMap.put("regUser", "user1");
		tmpMap.put("test", "user1");
		tmpList.add(tmpMap);
		tmpMap = new HashMap<>();
		tmpMap.put("name", "name2");
		tmpMap.put("id", "id2");
		tmpMap.put("description", "desc2");
		tmpMap.put("useYn", "N");
		tmpMap.put("regUser", "user2");
		tmpMap.put("test", "user2");
		tmpList.add(tmpMap);

		logger.debug("[tmp결과값]" + tmpList);
		logger.debug("[db결과값]" + sampleList);
		result.addDataSet("output1", tmpList);
		
		return result;
	}

	@RequestMapping(value = "/updateSampleList.do")
	public NexacroResult updateSampleList(
			@ParamDataSet(name = "input1") List<Map<String,Object>> updateSampleList
	) {

		sampleService.updateSampleList(updateSampleList);

		NexacroResult result = new NexacroResult();
		return result;
	}
}
