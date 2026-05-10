package com.mvc.mapper;

import com.mvc.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NewsMapper {
    public int add(News news);

    @Update({"update news set title = #{title},content = #{content},createtime=#{createtime},opername=#{opername} where id=#{id}"})
    public int update(News news);

    @Delete({"delete from news where id = #{id} "})
    public int delete(Integer id);

    @Select({"select * from news where id = #{id}"})
    public News getById(Integer id);

    @Select({"select * from news"})
    public List<News> list();
}
