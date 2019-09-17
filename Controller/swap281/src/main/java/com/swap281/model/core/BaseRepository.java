
package com.swap281.model.core;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

}
