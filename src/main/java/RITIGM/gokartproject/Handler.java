package RITIGM.gokartproject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<String, String>{

    @Override
    public String handleRequest(String input, Context context)
    {
        LambdaLogger logger = context.getLogger();
        logger.log("Input TYPE: " + input.getClass().toString());
        logger.log("Input Value: " + input);
        return new StringBuilder(input).reverse().toString();
    }
}
