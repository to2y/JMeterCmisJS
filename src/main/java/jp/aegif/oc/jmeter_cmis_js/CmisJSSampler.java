package jp.aegif.oc.jmeter_cmis_js;

import java.util.Map;

import jp.aegif.oc.cmis_js_console.CmisJSEngine;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.json.simple.parser.JSONParser;

public class CmisJSSampler extends AbstractJavaSamplerClient {

	private static final String REQUEST_DATA_JS_FILE_PATH = "requestDataJsFilePath";
	private static final String REQUEST_DATA_PARAMS_JSON = "requestDataParamsJson";

	@Override
	public Arguments getDefaultParameters() {

		Arguments defaultParameters = new Arguments();
		defaultParameters.addArgument(REQUEST_DATA_JS_FILE_PATH, "/");
		defaultParameters.addArgument(REQUEST_DATA_PARAMS_JSON, "{}");
		return defaultParameters;
	}

	public SampleResult runTest(JavaSamplerContext context) {
		SampleResult result = new SampleResult();

		try {
			String jsFilePath = context.getParameter(REQUEST_DATA_JS_FILE_PATH);
			String paramsJsonStr  = context.getParameter(REQUEST_DATA_PARAMS_JSON);
			
			result.sampleStart();
			
			//TODO call CmisJSEngine
			JSONParser parser=new JSONParser();
			Map paramsJson = (Map)parser.parse(paramsJsonStr);
			
			CmisJSEngine.newInstance().eval(jsFilePath, paramsJson);
			
            result.sampleEnd();
            result.setSuccessful(true);
            result.setResponseCodeOK();
            result.setResponseMessage("Done: " + jsFilePath);
		}
		catch(Exception ex) {
            result.sampleEnd();
            result.setSuccessful(false);
            result.setResponseCode("500");
            result.setResponseMessage("Error!! " + ex.getMessage());
            System.err.println(ex.getMessage());
            ex.printStackTrace();
		}
		return result;
	}

}
