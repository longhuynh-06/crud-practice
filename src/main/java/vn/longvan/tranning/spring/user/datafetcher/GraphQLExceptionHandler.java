package vn.longvan.tranning.spring.user.datafetcher;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.dgs.exceptions.DgsBadRequestException;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

import org.springframework.stereotype.Component;
import vn.longvan.tranning.spring.user.exception.UserException;
import vn.longvan.tranning.spring.user.exception.UserNotExistException;

@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {
    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();
    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable ex = handlerParameters.getException();
        TypedGraphQLError error;
        if(ex instanceof UserNotExistException){
            error = TypedGraphQLError.newNotFoundBuilder().message("%s", ex.getMessage()).path(handlerParameters.getPath()).build();

        } else if (ex instanceof DgsBadRequestException) {
            error = TypedGraphQLError.newBadRequestBuilder().message("%s", ex.getMessage()).path(handlerParameters.getPath()).build();
        } else if(ex instanceof UserException) {
            error = TypedGraphQLError.newBadRequestBuilder().message("%s", ex.getMessage()).path(handlerParameters.getPath()).build();
        }else {
            return defaultHandler.onException(handlerParameters);
        }
        return DataFetcherExceptionHandlerResult.newResult().error(error).build();

    }
}
