package com.wolfhack.service.user.service;

import com.wolfhack.service.user.exception.ForbiddenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PBKDF2Encoder implements PasswordEncoder {

	@Value("${password.encoder.secret}")
	private String secret;

	@Value("${password.encoder.iteration}")
	private Integer iteration;

	@Value("${password.encoder.key.length}")
	private Integer keyLength;

	@Value("${password.encoder.key.factory}")
	private String keyFactory;

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			PBEKeySpec pbeKeySpec = new PBEKeySpec(
				rawPassword.toString().toCharArray(),
				secret.getBytes(),
				iteration,
				keyLength
			);

			byte[] result = SecretKeyFactory
				.getInstance(keyFactory)
				.generateSecret(pbeKeySpec)
				.getEncoded();

			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			throw new ForbiddenException(exception.getMessage());
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}
}
