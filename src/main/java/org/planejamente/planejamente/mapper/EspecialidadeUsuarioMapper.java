//package org.planejamente.planejamente.mapper;
//
//import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeUsuarioDto;
//import org.planejamente.planejamente.entity.EspecialidadeUsuario;
//
//import java.util.Objects;
//
//public class EspecialidadeUsuarioMapper {
//    public static EspecialidadeUsuario toEntity(EspecialidadeUsuarioDto dto) {
//        if(Objects.isNull(dto)) return null;
//
//        EspecialidadeUsuario especialidadeUsuario = new EspecialidadeUsuario();
//
//        especialidadeUsuario.setEspecialidade(dto.getEspecialidade());
//        especialidadeUsuario.setPsicologo(dto.getPsicologo());
//
//        return especialidadeUsuario;
//    }
//}
