package sample.service.impl;

import com.nexacro.uiadapter17.spring.core.data.DataSetRowTypeAccessor;
import com.nexacro17.xapi.data.DataSet;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sample.service.SampleService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("sampleService")
public class SampleServiceImpl extends EgovAbstractServiceImpl implements SampleService {

	private Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Resource(name = "sampleMapper")
	private SampleMapper sampleMapper;

	@Override
	public List<Map<String, Object>> selectSampleList(Map<String, String> searchInfo) {
		return sampleMapper.selectSampleList(searchInfo);
	}

	@Override
	public void updateSampleList(List<Map<String,Object>> sampleList) {
		int size = sampleList.size();
		for (int i=0; i<size; i++) {
			Map<String,Object> sample = sampleList.get(i);

			int dataRowType = Integer.parseInt(String.valueOf(sample.get(DataSetRowTypeAccessor.NAME)));

			if (dataRowType == DataSet.ROW_TYPE_INSERTED){
				String id = "";

				try {
					id = egovIdGnrService.getNextStringId();

					sample.replace("id", id);

					sampleMapper.insertSampleList(sample);

				} catch (FdlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (dataRowType == DataSet.ROW_TYPE_UPDATED){
				sampleMapper.updateSampleList(sample);
			}
			else if (dataRowType == DataSet.ROW_TYPE_DELETED){
				sampleMapper.deleteSampleList(sample);
			}
		}
	}
}
