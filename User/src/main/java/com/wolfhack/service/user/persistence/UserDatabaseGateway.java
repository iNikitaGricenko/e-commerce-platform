package com.wolfhack.service.user.persistence;

import com.wolfhack.service.user.adapter.database.UserDatabaseAdapter;
import com.wolfhack.service.user.exception.NotFoundException;
import com.wolfhack.service.user.logging.annotations.DatabaseOperation;
import com.wolfhack.service.user.mapper.UserMapper;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.entity.UserEntity;
import com.wolfhack.service.user.repository.UserRepository;
import com.wolfhack.service.user.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDatabaseGateway implements UserDatabaseAdapter {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	@Override
	@DatabaseOperation
	public Long save(User model) {
		UserEntity entity = userMapper.toEntity(model);
		return userRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public Long partialUpdate(Long id, User model) {
		UserEntity updated = userRepository
			.findById(id)
			.map(userEntity -> userMapper.partialUpdate(model, userEntity))
			.orElseThrow();

		return userRepository.save(updated).getId();
	}

	@Override
	@DatabaseOperation
	public Long update(Long id, User model) {
		if (!exists(id)) {
			throw new NotFoundException("User does not exist");
		}

		UserEntity entity = userMapper.toEntity(model);
		userMapper.update(model, entity);
		return userRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public User getById(Long id) {
		return userRepository
			.findById(id)
			.map(userMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public boolean exists(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	@DatabaseOperation
	public Collection<User> getById(Collection<Long> ids) {
		return userRepository
			.findAllById(ids)
			.stream()
			.map(userMapper::toModel)
			.toList();
	}

	@Override
	@DatabaseOperation
	public List<User> getAll() {
		return userRepository
			.findAll()
			.stream()
			.map(userMapper::toModel)
			.toList();
	}

	@Override
	@DatabaseOperation
	public DomainPage<User> getPage(Pageable pageable) {
		Page<User> page = userRepository
			.findAll(pageable)
			.map(userMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	@DatabaseOperation
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@DatabaseOperation
	public User getByUsername(String username) {
		return userRepository
			.findByUsername(username)
			.map(userMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public User getByEmail(String email) {
		return userRepository
			.findByEmail(email)
			.map(userMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}
}
