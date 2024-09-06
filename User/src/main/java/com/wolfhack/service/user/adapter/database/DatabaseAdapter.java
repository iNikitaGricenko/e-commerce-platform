package com.wolfhack.service.user.adapter.database;

import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.user.model.domain.DomainModel;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface DatabaseAdapter<T extends DomainModel> {

	Long save(T model);

	Long partialUpdate(Long id, T model);

	Long update(Long id, T model);

	T getById(Long id);

	boolean exists(Long id);

	Collection<T> getById(Collection<Long> ids);

	List<T> getAll();

	DomainPage<T> getPage(Pageable pageable);

	void delete(Long id);

}
