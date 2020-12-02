package com.gruzini.gramophone.exceptions

import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation

class UnauthenticatedAccessException extends RuntimeException implements GraphQLError {

    UnauthenticatedAccessException(final String message) {
        super(message)
    }

    @Override
    List<SourceLocation> getLocations() {
        return null
    }

    @Override
    ErrorType getErrorType() {
        return null
    }
}
