package com.app.cyb.cybparent.mapper.article;

import com.app.cyb.cybparent.entity.Demo;
import com.app.cyb.cybparent.entity.article.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into articles (title,content,user_id) values (#{title},#{content},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer insertArticle(Article article);

    @Select("select id from articles where title=#{title}")
    @ResultType(List.class)
    List<Article> queryIdByTitle(@Param("title") String title);

    @Select("select id from articles where user_id=#{userId}")
    @ResultType(List.class)
    List<Article> queryIdByUserId(@Param("userId")String userId);

    @Select("select id,title,content,user_id from article where id =#{id}")
    @Results(id = "demoObjMap",value = {
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER,javaType = Integer.class),
            @Result(column = "title",property = "title",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "content",property = "content",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "user_id",property = "userId",jdbcType = JdbcType.VARCHAR,javaType = String.class)
    })
    Article queryArticle(@Param("id") Integer id);

    @Update("update article set content =#{content} where id =#{id}")
    Integer updateContent(@Param("id") Integer id,@Param("content")String content);

    @Delete("delete from article where id =#{id}")
    Integer removeArticle(@Param("id") Integer id);
}