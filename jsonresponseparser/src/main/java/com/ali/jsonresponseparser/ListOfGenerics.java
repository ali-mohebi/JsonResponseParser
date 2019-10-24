package com.ali.jsonresponseparser;

import androidx.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListOfGenerics<X> implements ParameterizedType {

    private Class<?> wrapped;

    public ListOfGenerics(Class<X> wrapped) {
        this.wrapped = wrapped;
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{wrapped};
    }

    @NonNull
    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
