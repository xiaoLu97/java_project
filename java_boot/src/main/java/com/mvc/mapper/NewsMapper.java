package com.mvc.mapper;

import com.mvc.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NewsMapper {

    public List<News> list();

    @Insert({"insert into news(title,content,createtime,opername) values(#{title},#{content},#{createtime},#{opername})"})
    public void add(News news);

    @Select({"select * from news where id = #{id}"})
    public News getById(Integer id);

    @Update({"update news set title = #{title},content = #{content},opername=#{opername} where id = #{id}"})
    public void update(News news);

    @Delete({"delete from news where id = #{id}"})
    public void delete(Integer id);
}
