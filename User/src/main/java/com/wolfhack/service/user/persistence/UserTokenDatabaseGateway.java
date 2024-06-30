package com.wolfhack.service.user.persistence;

import com.wolfhack.service.user.adapter.database.UserTokenDatabaseAdapter;
import com.wolfhack.service.user.exception.NotFoundException;
import com.wolfhack.service.user.logging.annotations.DatabaseOperation;
import com.wolfhack.service.user.mapper.UserTokenMapper;
import com.wolfhack.service.user.model.domain.UserToken;
import com.wolfhack.service.user.model.entity.UserTokenEntity;
import com.wolfhack.service.user.repository.UserTokenRepository;
import com.wolfhack.service.user.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTokenDatabaseGateway implements UserTokenDatabaseAdapter {

	private final UserTokenRepository userTokenRepository;

	private final UserTokenMapper userTokenMapper;

	@Override
	@DatabaseOperation
	public Long save(UserToken model) {
		UserTokenEntity entity = userTokenMapper.toEntity(model);

		return userTokenRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public Long partialUpdate(Long id, UserToken model) {
		UserTokenEntity updated = userTokenRepository
			.findById(id)
			.map(userTokenEntity -> userTokenMapper.partialUpdate(model, userTokenEntity))
			.orElseThrow();

		return userTokenRepository.save(updated).getId();
	}

	@Override
	@DatabaseOperation
	public Long update(Long id, UserToken model) {
		if (!exists(id)) {
			throw new NotFoundException("User token does not exist");
		}

		UserTokenEntity entity = userTokenMapper.toEntity(model);
		userTokenMapper.update(model, entity);
		return userTokenRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public UserToken getById(Long id) {
		return userTokenRepository
			.findById(id)
			.map(userTokenMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public boolean exists(Long id) {
		return userTokenRepository.existsById(id);
	}

	@Override
	@DatabaseOperation
	public Collection<UserToken> getById(Collection<Long> ids) {
		return userTokenRepository
			.findAllById(ids)
			.stream()
			.map(userTokenMapper::toModel)
			.toList();
	}

	@Override
	@DatabaseOperation
	public List<UserToken> getAll() {
		return userTokenRepository
			.findAll()
			.stream()
			.map(userTokenMapper::toModel)
			.toList();
	}

	@Override
	@DatabaseOperation
	public DomainPage<UserToken> getPage(Pageable pageable) {
		Page<UserToken> page = userTokenRepository
			.findAll(pageable)
			.map(userTokenMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	@DatabaseOperation
	public void delete(Long id) {
		userTokenRepository.deleteById(id);
	}

	@Override
	@DatabaseOperation
	public UserToken getByToken(String token) {
		return userTokenRepository
			.findByToken(token)
			.map(userTokenMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public UserToken getByUserId(Long id) {
		return userTokenRepository
			.findByUserId(id)
			.map(userTokenMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public boolean existsByUserId(Long userId) {
		return userTokenRepository.existsByUserId(userId);
	}
}
