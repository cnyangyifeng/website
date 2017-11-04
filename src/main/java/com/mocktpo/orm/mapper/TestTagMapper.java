package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.TestTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestTagMapper {

    @Select({
            "SELECT",
            "MT_TAG_ID AS tagId,",
            "MT_TITLE AS title,",
            "MT_STATUS AS status",
            "FROM MT_TEST_TAG",
            "ORDER BY MT_TAG_ID DESC"
    })
    List<TestTag> findAll();

    @Select({
            "SELECT",
            "MT_TAG_ID AS tagId,",
            "MT_TITLE AS title,",
            "MT_STATUS AS status",
            "FROM MT_TEST_TAG",
            "WHERE",
            "MT_TAG_ID = #{tagId}"
    })
    TestTag findByTagId(@Param("tagId") int tagId);

    @Insert({
            "INSERT INTO MT_TEST_TAG (",
            "MT_TAG_ID,",
            "MT_TITLE,",
            "MT_STATUS",
            ") VALUES (",
            "#{tagId},",
            "#{title},",
            "#{status}",
            ")"
    })
    void create(TestTag testTag);

    @Update({
            "UPDATE MT_TEST_TAG",
            "SET",
            "MT_TAG_ID = #{tagId},",
            "MT_TITLE = #{title},",
            "MT_STATUS = #{status}",
            "WHERE",
            "MT_TAG_ID = #{tagId}"

    })
    void update(TestTag testTag);

    @Delete({
            "DELETE FROM MT_TEST_TAG",
            "WHERE",
            "MT_TAG_ID = #{tagId}"
    })
    void deleteByTagId(@Param("tagId") int tagId);
}
