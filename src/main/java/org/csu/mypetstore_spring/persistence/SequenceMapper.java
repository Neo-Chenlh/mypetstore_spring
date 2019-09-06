package org.csu.mypetstore_spring.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore_spring.domain.Sequence;

@Mapper
public interface SequenceMapper {

    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
