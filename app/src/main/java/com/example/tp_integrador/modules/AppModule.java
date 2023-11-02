package com.example.tp_integrador.modules;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.dao.ongs.OngDao;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.repository.ongs.OngRepository;
import com.example.tp_integrador.data.repository.usuarios.IUsuariosRepository;
import com.example.tp_integrador.data.repository.usuarios.UsuarioRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.repository.voluntarios.VoluntarioRepository;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.dao.usuarios.UsuarioDao;
import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.usecases.ongs.IOngSave;
import com.example.tp_integrador.usecases.ongs.OngSave;
import com.example.tp_integrador.usecases.usuarios.ILoginAllowAccess;
import com.example.tp_integrador.usecases.usuarios.LoginAllowAccess;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.data.dao.voluntarios.VoluntarioDao;
import com.example.tp_integrador.usecases.voluntarios.VoluntarioSave;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarCamposVacios.ValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.example.tp_integrador.utils.validarUsuario.ValidateMail;
import com.example.tp_integrador.utils.validateJpgFiles.IValidateJpegFiles;
import com.example.tp_integrador.utils.validateJpgFiles.ValidateJpegFiles;
import com.example.tp_integrador.utils.validatePdfFiles.IValidatePdfFiles;
import com.example.tp_integrador.utils.validatePdfFiles.ValidatePdfFiles;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class) // Especifica el ámbito en el que se proporcionará la implementación
public class AppModule {

    //Validates or utils
    @Provides
    static IValidateInputs provideValidateInputs() {
        return new ValidateInputs(); // Reemplaza con tu implementación concreta
    }

    @Provides
    static IValidateMail provideValidateMail() {
        return new ValidateMail();
    }

    @Provides
    static IValidatePdfFiles provideValidatePdfFiles() {
        return new ValidatePdfFiles();
    }

    @Provides
    static IValidateJpegFiles provideValidateJpgFiles() {
        return new ValidateJpegFiles();
    }

    //usecases
    @Provides
    @Singleton
    static IVoluntarioSave provideVoluntarioSave(IUsuarioDao usuarioDao, IVoluntarioDao voluntarioDao) {
        return new VoluntarioSave(usuarioDao, voluntarioDao);
    }

    @Provides
    @Singleton
    static IOngSave provideOngSave(IUsuarioDao usuarioDao, IOngDao ongdao) {
        return new OngSave(usuarioDao, ongdao);
    }

    @Provides
    @Singleton
    static ILoginAllowAccess provideLoginAllowAccess(UsuarioDao usuarioDao) {
        return new LoginAllowAccess(usuarioDao);
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

    @Provides
    static IOngRepository provideOngRepository() {
        return new OngRepository();
    }

    //daos
    @Provides
    static IVoluntarioDao provideVoluntarioDao(IVoluntariosRepository voluntariosRepository) {
        return new VoluntarioDao(voluntariosRepository);
    }

    @Provides
    @Singleton
    static IUsuarioDao provideUsuarioDao(IUsuariosRepository usuariosRepository) {
        return new UsuarioDao(usuariosRepository);
    }

    @Provides
    @Singleton
    static IOngDao provideOngDao(IOngRepository ongRepository) {
        return new OngDao(ongRepository);
    }

}