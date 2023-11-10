package com.example.tp_integrador.usecases.ongs;

import java.util.concurrent.ExecutionException;

public interface IOngProjectDelete {

    Boolean deleteProjectOng(Integer id) throws ExecutionException, InterruptedException;
}
