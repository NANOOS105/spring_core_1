package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext  ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("모든 빈 출력하기")
	 void findAllBean() {
		String[] beanDefinisionNames = ac.getBeanDefinitionNames();
		for(String beanDefinitionName : beanDefinisionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("Name = " + beanDefinitionName + " object = " + bean);
		}
	}
	
	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	 void findApplicationBean() {
		String[] beanDefinisionNames = ac.getBeanDefinitionNames();
		for(String beanDefinitionName : beanDefinisionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			
			//ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name = " + beanDefinitionName + " object = " + bean);
			}
		}
	}
}
