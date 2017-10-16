package jp.co.stylez.serverless;

import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, String>, Response> {

	// private static final Logger LOG = Logger.getLogger(Handler.class);
	
	private static final String REQUEST_KEY_GOAL = "goal";
	private static final String REQUEST_KEY_INSTANCE_ID = "instanceId";

	private static final String REQUEST_VALUE_GOAL_START = "start";
	private static final String REQUEST_VALUE_GOAL_STOP = "stop";

	@Override
	public Response handleRequest(Map<String, String> input, Context context) {
		BasicConfigurator.configure();
		
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_1)
                .build();
		
		if (REQUEST_VALUE_GOAL_START.equals(input.get(REQUEST_KEY_GOAL))) {
			return new Response(ec2.startInstances(new StartInstancesRequest(Arrays.asList(input.get(REQUEST_KEY_INSTANCE_ID)))).toString(), input);
		}
		
		if (REQUEST_VALUE_GOAL_STOP.equals(input.get(REQUEST_KEY_GOAL))) {
			return new Response(ec2.stopInstances(new StopInstancesRequest(Arrays.asList(input.get(REQUEST_KEY_INSTANCE_ID)))).toString(), input);
		}

		return new Response("No opelation end..", input);
	}


}
