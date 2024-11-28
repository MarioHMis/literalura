package com.literalura.Literalura.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> clazz);
}
