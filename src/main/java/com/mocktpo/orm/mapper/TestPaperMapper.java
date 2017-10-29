package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.TestPaper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestPaperMapper {

    @Select({
            "SELECT",
            "MT_TID AS tid,",
            "MT_TITLE AS title,",
            "MT_STARS AS stars,",
            "MT_CREATOR AS creator,",
            "MT_CREATED_TIME AS createdTime,",
            "MT_UPDATED_TIME AS updatedTime,",
            "MT_VERSION AS version",
            "FROM MT_TEST_PAPER"
    })
    List<TestPaper> findAll();

    @Select({
            "SELECT",
            "MT_TID AS tid,",
            "MT_TITLE AS title,",
            "MT_STARS AS stars,",
            "MT_CREATOR AS creator,",
            "MT_CREATED_TIME AS createdTime,",
            "MT_UPDATED_TIME AS updatedTime,",
            "MT_VERSION AS version",
            "FROM MT_TEST_PAPER",
            "WHERE",
            "MT_TID = #{tid}"
    })
    TestPaper findByTid(@Param("tid") String tid);

    @Insert({
            "INSERT INTO MT_TEST_PAPER (",
            "MT_TID,",
            "MT_TITLE,",
            "MT_STARS,",
            "MT_CREATOR,",
            "MT_CREATED_TIME,",
            "MT_UPDATED_TIME,",
            "MT_VERSION",
            ") VALUES (",
            "#{tid},",
            "#{title},",
            "#{stars},",
            "#{creator},",
            "#{createdTime},",
            "#{updatedTime},",
            "#{version}",
            ")"
    })
    void create(TestPaper testPaper);

    @Update({
            "UPDATE MT_TEST_PAPER",
            "SET",
            "MT_TID = #{tid},",
            "MT_TITLE = #{title},",
            "MT_STARS = #{stars},",
            "MT_CREATOR = #{creator},",
            "MT_CREATED_TIME = #{createdTime},",
            "MT_UPDATED_TIME = #{updatedTime},",
            "MT_VERSION = #{version}",
            "WHERE",
            "MT_TID = #{tid}"

    })
    void update(TestPaper testPaper);

    @Delete({
            "DELETE FROM MT_TEST_PAPER",
            "WHERE",
            "MT_TID = #{tid}"
    })
    void deleteByTid(@Param("tid") String tid);
}
