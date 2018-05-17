一、spring boot starter自定义spring boot starter  具体参见两个项目：1、springboot016 2、starter-redis

	1、新建一个maven项目，并加入spring-boot-start依赖  --> 参见starter-redis
	
	2、新建一个配置类，将配置文件中的配置信息加载到具体的类中 --> 参见starter-redis项目中的RedisProperties.class
		2.1、在此类上用注解@ConfigurationProperties(prefix="redis")将配置文件中的配置信息绑定到RedisProperties对象上
		
	3、新建一个自动配置类 --> 参见starter-redis项目中的RedisAutoConfigeration.class，注意如下三个注解：
		3.1、@ConditionalOnClass(value={Jedis.class}) 当项目类路径中存在Jedis时，才加载这个配置类
		3.2、@EnableConfigurationProperties(value={RedisProperties.class}) 快速注册RedisProperties.class类到spring容器中
		3.3、@ConditionalOnMissingBean当容器中不存在Jedis对象时才进行注册接管，否则用用户自己配置的Jedis
		
	4、将编写的该starter可用的两种注册方式：
		4.1、编写注解@EnableXxx, --> 参见tarter-redis项目中的EnableRedis.class
		4.2、在项目的资源文件位置新建文件夹：META-INF，并在此新建文件夹系新建文件spring.factories，在此配置文件中配置如下内容：
			org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.springboot.starter.reids.RedisAutoConfigeration
		4.3、如果使用的是注解地方式，那么在具体用到starter的项目中，需要开启注解@@EnableXxx --> 参见app.class上面的注解
		4.4、如果使用的时配置文件的方式，那么就不需要做任何配置，只需要在具体用到starter的项目中加入依赖即可使用，则默认开启（开箱即用）
		********4.3、4.4二者选其一***************
			
	4、在具体用到该starter的项目中引入该starter的依赖  --> 参见 springboot016中pom.xml
	
二、spring boot的日志
	1、spring boot的日志默认的是logback，可以参见相关源码：org.springframework.boot.logging.*
	2、如果想用自己的日志记录方案，可以首先在依赖中去除默认的依赖，然后再加入自己的日志方案，在加入自己日志方案的配置文件 可参见pom.xml中的使用方式，
		使用的就是自定义的方案，log4j2的方案，首先去除了logback的日志方案，然后再加入log4j2的依赖，然后再类路劲中加入log4j2.xml配置文件