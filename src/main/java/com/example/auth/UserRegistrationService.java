package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

	@Autowired
	private UserRepository userRepository;

//	宣言的トランザクション　チェック例外の場合はコミットされる　非チェック例外Runtimeクラス
	@Transactional
	public void registerUser(User user) {
		int result = 0;
		result += userRepository.registerUser(user);
		result += userRepository.registerUserRole(user);

		if (result != 2) {
			throw new RuntimeException();
		}
	}

}
