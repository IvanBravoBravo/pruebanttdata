package com.nttdata.prueba.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class AppUtils {

    @Value("${emailregex}")
    private String emailRegex;

    @Value("${passwordregex}")
    private String passwordRegex;
    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     * @param source lista origen
     * @param targetClass clase de lista destino
     * @return lista mapeada
     * @param <S> clase origen
     * @param <T> clase destino
     */
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public boolean isValidEmail(String correo){
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(correo);
        return matcher.find();
    }

    public boolean isValidPassword(String contraseña){
        Pattern passPattern = Pattern.compile(passwordRegex);
        Matcher matcher = passPattern.matcher(contraseña);
        return matcher.find();
    }
}
