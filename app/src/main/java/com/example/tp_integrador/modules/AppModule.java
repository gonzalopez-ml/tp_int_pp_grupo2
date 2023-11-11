package com.example.tp_integrador.modules;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.dao.ongs.OngDao;
import com.example.tp_integrador.data.dao.proyectos.IProyectoDao;
import com.example.tp_integrador.data.dao.proyectos.ProyectoDao;
import com.example.tp_integrador.data.dao.relaciones.IRelationDao;
import com.example.tp_integrador.data.dao.relaciones.impl.RelationDao;
import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.repository.ongs.OngRepository;
import com.example.tp_integrador.data.repository.proyectos.IProyectoRepository;
import com.example.tp_integrador.data.repository.proyectos.ProyectoRepository;
import com.example.tp_integrador.data.repository.relaciones.IRelationRepository;
import com.example.tp_integrador.data.repository.relaciones.impl.RelationRepository;
import com.example.tp_integrador.data.repository.usuarios.IUsuariosRepository;
import com.example.tp_integrador.data.repository.usuarios.UsuarioRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.repository.voluntarios.VoluntarioRepository;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.dao.usuarios.UsuarioDao;
import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.usecases.ongs.IOngGetByUserID;
import com.example.tp_integrador.usecases.ongs.IOngGet;
import com.example.tp_integrador.usecases.ongs.IOngProjectDelete;
import com.example.tp_integrador.usecases.ongs.IOngProjectoUpdate;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;
import com.example.tp_integrador.usecases.ongs.IOngSave;
import com.example.tp_integrador.usecases.ongs.impl.OngGetByUserID;
//import com.example.tp_integrador.usecases.ongs.OngSave;
import com.example.tp_integrador.usecases.ongs.IOngUpdate;
import com.example.tp_integrador.usecases.ongs.impl.OngGet;
import com.example.tp_integrador.usecases.ongs.impl.OngProjectDelete;
import com.example.tp_integrador.usecases.ongs.impl.OngProjectUpdate;
import com.example.tp_integrador.usecases.ongs.impl.OngProyectosGet;
import com.example.tp_integrador.usecases.ongs.impl.OngUpdate;
import com.example.tp_integrador.usecases.proyectos.IProyectoGetLocalidades;
import com.example.tp_integrador.usecases.proyectos.IProyectoSave;
import com.example.tp_integrador.usecases.proyectos.ProyectoSave;
import com.example.tp_integrador.usecases.ongs.impl.OngSave;
import com.example.tp_integrador.usecases.proyectos.impl.ProyectoGetLocalidades;
import com.example.tp_integrador.usecases.relaciones.ISaveRelation;
import com.example.tp_integrador.usecases.relaciones.impl.SaveRelation;
import com.example.tp_integrador.usecases.usuarios.ILoginAllowAccess;
import com.example.tp_integrador.usecases.usuarios.impl.LoginAllowAccess;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGetByUserID;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.data.dao.voluntarios.VoluntarioDao;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioUpdate;
import com.example.tp_integrador.usecases.voluntarios.impl.VoluntarioGetByUserID;
import com.example.tp_integrador.usecases.voluntarios.impl.VoluntarioSave;
import com.example.tp_integrador.usecases.voluntarios.impl.VoluntarioGet;
import com.example.tp_integrador.usecases.voluntarios.impl.VoluntarioUpdate;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarCamposVacios.ValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.example.tp_integrador.utils.validarUsuario.ValidateMail;
import com.example.tp_integrador.utils.validateJpgFiles.IValidateJpegFiles;
import com.example.tp_integrador.utils.validateJpgFiles.ValidateJpegFiles;
import com.example.tp_integrador.utils.validatePdfFiles.IValidatePdfFiles;
import com.example.tp_integrador.utils.validatePdfFiles.ValidatePdfFiles;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
    static IVoluntarioUpdate provideVoluntarioUpdate(IUsuarioDao usuarioDao, IVoluntarioDao voluntarioDao) {
        return new VoluntarioUpdate(usuarioDao, voluntarioDao);
    }


    @Provides
    static IOngUpdate provideOngUpdate(IUsuarioDao usuarioDao, IOngDao ongDao) {
        return new OngUpdate(usuarioDao, ongDao);
    }

    @Provides
    static IVoluntarioGet provideVoluntarioGet(IVoluntarioDao voluntarioDao) {
        return new VoluntarioGet(voluntarioDao);
    }

    @Provides
    static IOngGet provideOngGet(IOngDao ongDao) {
        return new OngGet(ongDao);
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

    @Provides
    @Singleton
    static IVoluntarioGetByUserID provideVoluntarioGetByUserID(IVoluntarioDao voluntarioDao){
        return new VoluntarioGetByUserID(voluntarioDao);
    }

    @Provides
    @Singleton
    static IOngGetByUserID provideOngGetByUserID(IOngDao ongDao){
        return new OngGetByUserID(ongDao);
    }

    @Provides
    @Singleton
    static IProyectoSave provideProyectoSave(IProyectoDao proyectoDao,IOngDao ongDao){
        return new ProyectoSave(proyectoDao,ongDao);
    }

    @Provides
    @Singleton
    static IOngProyectosGet provideOngProyectosGetDao(IOngDao ongDao) {
        return new OngProyectosGet(ongDao);
    }

    @Provides
    @Singleton
    static IOngProjectDelete provideOngProjectDeleteDao(IOngDao ongDao) {
        return new OngProjectDelete(ongDao);
    }

    @Provides
    @Singleton
    static IOngProjectoUpdate provideOngProjectUpdateDao(IProyectoDao projectoUpdate) {
        return new OngProjectUpdate(projectoUpdate);
    }

    @Provides
    @Singleton
    static ISaveRelation provideSaveRelation(IRelationDao relationDao) {
        return new SaveRelation(relationDao);
    }

    @Provides
    @Singleton
    static IProyectoGetLocalidades provideProyectoGetLocalideades(IProyectoDao proyectoDao){
        return new ProyectoGetLocalidades(proyectoDao);
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


    @Provides
    static IProyectoRepository provideProyectoRepository(){return new ProyectoRepository();}

    @Provides
    static IRelationRepository provideRelationRepository() {
        return new RelationRepository();
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

    @Provides
    @Singleton
    static IProyectoDao provideProyectoDao(IProyectoRepository proyectoRepository){
        return new ProyectoDao(proyectoRepository);
    }

    @Provides
    @Singleton
    static IRelationDao provideRelationDao(IRelationRepository repository) {
        return new RelationDao(repository);
    }

}