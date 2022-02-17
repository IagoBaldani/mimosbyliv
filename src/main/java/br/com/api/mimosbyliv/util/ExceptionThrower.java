package br.com.api.mimosbyliv.util;

import java.util.HashMap;

public class ExceptionThrower {
    public static void thrower(HashMap<String, String> verificacao, String key){
        if(verificacao.containsKey(key)){
            throw new NullPointerException(verificacao.get(key));
        }
    }
}
