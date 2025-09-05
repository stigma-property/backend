package com.lastline.backend;

import com.lastline.backend.domain.contractRequest.repository.ContractRequestRepository;
import com.lastline.backend.domain.contractRequest.service.ContractService;
import com.lastline.backend.domain.contractRequest.service.ContractServiceImpl;
import com.lastline.backend.domain.contractRequest.service.ContractValidator;
import com.lastline.backend.domain.property.repository.PropertyRepository;
import com.lastline.backend.domain.property.service.PropertyService;
import com.lastline.backend.domain.property.service.PropertyServiceImpl;
import com.lastline.backend.domain.property.service.PropertyValidator;
import com.lastline.backend.domain.user.repository.UserRepository;
import com.lastline.backend.domain.user.service.AuthService;
import com.lastline.backend.domain.user.service.AuthServiceImpl;
import com.lastline.backend.domain.user.service.AuthValidator;
import com.lastline.backend.global.config.DataInitializer;
import com.lastline.backend.view.MainView;

// @SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// SpringApplication.run(BackendApplication.class, args);

		// 윈도우에서 ANSI 색상과 이모지 지원을 위한 설정
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				// 윈도우에서 ANSI 색상 활성화
				System.setProperty("file.encoding", "UTF-8");
				// 파워쉘에서 유니코드 지원 활성화
				System.setProperty("console.encoding", "UTF-8");
			}
		} catch (Exception e) {
			// 설정 실패해도 계속 진행
		}

		// Repository 생성
		UserRepository userRepository = new UserRepository();
		PropertyRepository propertyRepository = new PropertyRepository();
		ContractRequestRepository contractRequestRepository = new ContractRequestRepository();

		// Validator 생성
		AuthValidator authValidator = new AuthValidator();
		PropertyValidator propertyValidator = new PropertyValidator();
		ContractValidator contractValidator = new ContractValidator();

		// Service 생성
		AuthService authService = new AuthServiceImpl(userRepository, authValidator);
		PropertyService propertyService = new PropertyServiceImpl(propertyRepository, userRepository,
			propertyValidator);
		ContractService contractService = new ContractServiceImpl(contractRequestRepository, propertyRepository,
			contractValidator);

		// 데이터 초기화
		DataInitializer dataInitializer = new DataInitializer(userRepository, propertyRepository,
			contractRequestRepository);
		dataInitializer.init();

		// 메인 뷰 시작
		MainView mainView = new MainView(authService, propertyService, contractService, userRepository);
		mainView.start();
	}

}
