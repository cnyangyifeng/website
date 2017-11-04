package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.Test;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestMapper {

    @Select({
            "SELECT",
            "MT_TID AS tid,",
            "MT_TAG_ID AS tagId,",
            "MT_TITLE AS title,",
            "MT_STARS AS stars,",
            "MT_CREATOR AS creator,",
            "MT_CREATED_TIME AS createdTime,",
            "MT_UPDATED_TIME AS updatedTime,",
            "MT_VERSION AS version,",
            "MT_STATUS AS status",
            "FROM MT_TEST",
            "ORDER BY MT_TITLE DESC"
    })
    List<Test> findAll();

    @Select({
            "SELECT",
            "MT_TID AS tid,",
            "MT_TAG_ID AS tagId,",
            "MT_TITLE AS title,",
            "MT_STARS AS stars,",
            "MT_CREATOR AS creator,",
            "MT_CREATED_TIME AS createdTime,",
            "MT_UPDATED_TIME AS updatedTime,",
            "MT_VERSION AS version,",
            "MT_STATUS AS status",
            "FROM MT_TEST",
            "WHERE",
            "MT_TID = #{tid}"
    })
    Test findByTid(@Param("tid") String tid);

    @Insert({
            "INSERT INTO MT_TEST (",
            "MT_TID,",
            "MT_TAG_ID,",
            "MT_TITLE,",
            "MT_STARS,",
            "MT_CREATOR,",
            "MT_CREATED_TIME,",
            "MT_UPDATED_TIME,",
            "MT_VERSION,",
            "MT_STATUS",
            ") VALUES (",
            "#{tid},",
            "#{tagId},",
            "#{title},",
            "#{stars},",
            "#{creator},",
            "#{createdTime},",
            "#{updatedTime},",
            "#{version},",
            "#{status}",
            ")"
    })
    void create(Test test);

    @Update({
            "UPDATE MT_TEST",
            "SET",
            "MT_TID = #{tid},",
            "MT_TAG_ID = #{tagId},",
            "MT_TITLE = #{title},",
            "MT_STARS = #{stars},",
            "MT_CREATOR = #{creator},",
            "MT_CREATED_TIME = #{createdTime},",
            "MT_UPDATED_TIME = #{updatedTime},",
            "MT_VERSION = #{version},",
            "MT_STATUS = #{status}",
            "WHERE",
            "MT_TID = #{tid}"

    })
    void update(Test test);

    @Delete({
            "DELETE FROM MT_TEST",
            "WHERE",
            "MT_TID = #{tid}"
    })
    void deleteByTid(@Param("tid") String tid);
}
