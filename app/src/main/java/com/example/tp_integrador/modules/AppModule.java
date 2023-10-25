package com.example.tp_integrador.modules;

import com.example.tp_integrador.data.repository.usuarios.IUsuariosRepository;
import com.example.tp_integrador.data.repository.usuarios.UsuarioRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.repository.voluntarios.VoluntarioRepository;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.dao.usuarios.UsuarioDao;
import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.data.dao.voluntarios.VoluntarioDao;
import com.example.tp_integrador.usecases.voluntarios.VoluntarioSave;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarCamposVacios.ValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.example.tp_integrador.utils.validarUsuario.ValidateMail;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class) // Especifica el ámbito en el que se proporcionará la implementación
public class AppModule {

    @Provides
    static IValidateInputs provideValidateInputs() {
        return new ValidateInputs(); // Reemplaza con tu implementación concreta
    }

    @Provides
    static IValidateMail provideValidateMail() {
        return new ValidateMail();
    }

    //usecases
    @Provides
    static IVoluntarioDao provideVoluntariodao(IVoluntariosRepository voluntariosRepository) {
        return new VoluntarioDao(voluntariosRepository);
    }

    @Provides
    @Singleton
    static IUsuarioDao provideUsuarioSave(IUsuariosRepository usuariosRepository) {
        return new UsuarioDao(usuariosRepository);
    }

    @Provides
    @Singleton
    static IVoluntarioSave provideVoluntarioSave(IUsuarioDao usuarioDao, IVoluntarioDao voluntarioDao) {
        return new VoluntarioSave(usuarioDao, voluntarioDao);
    }

    //repositories
    @Provides
    static IUsuariosRepository provideUsuariosRepository() {
        return new UsuarioRepository();
    }

    @Provides
    static IVoluntariosRepository provideVoluntariosRepository() {
        return new VoluntarioRepository();
    }
}