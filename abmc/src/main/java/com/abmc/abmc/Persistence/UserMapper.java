package com.abmc.abmc.Persistence;

import com.abmc.abmc.entities.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * from usuarios")
    List<Users> findAll();

    @Select("SELECT * FROM usuarios WHERE id = #{id}")
    Users findById(int id);

    @Insert("INSERT INTO " +
            " usuarios(name, surname, email ) " +
            " VALUES (#{nombre}, #{apellido},#{email}) ")

    void insert(Users user);

    @Delete("DELETE FROM usuarios WHERE id = #{id}")
    void deleteById(int id);

    @Update("UPDATE usuarios SET name = #{nombre}, " +
            " surname = #{apellido}, " +
            " email = #{email} " +
            " WHERE id =#{id} ")
    void update (Users user);

    @Select("SELECT * FROM usuarios WHERE email = #{email}")
    Users findByEmail(String email);
}
