package com.ecomindo.onboarding.testinghat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Config {

	@Value("${spring.custom.sftpHost}")
	private String sftpHost;

	@Value("${spring.custom.sftpPort}")
	private Integer sftpPort;

	@Value("${spring.custom.sftpUsername}")
	private String sftpUsername;
	
	@Value("${spring.custom.sftpPassword}")
	private String sftpPassword;

	@Value("${spring.custom.sftpFolder}")
	private String sftpFolder;

	@Value("${spring.custom.sftpFileName}")
	private String sftpFilename;

	@Value("${spring.custom.redis.host}")
	private String redisHost;

	@Value("${spring.custom.redis.port}")
	private Integer redisPort;

	@Value("${spring.custom.redis.password}")
	private String redisPassword;

	@Value("${spring.custom.redis.database}")
	private Integer redisDatabase;

	@Value(value = "${spring.custom.kafka.bootstrapAddress}")
	private String kafkaBootstrapAddress;

	@Value(value = "${spring.custom.kafka.topic}")
	private String kafkaTopic;

	@Value(value = "${spring.custom.kafka.groupId}")
	private String kafkaGroupId;
	
	public String getSftpHost() {
		return sftpHost;
	}

	public String getKafkaGroupId() {
		return kafkaGroupId;
	}

	public void setKafkaGroupId(String kafkaGroupId) {
		this.kafkaGroupId = kafkaGroupId;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public Integer getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(Integer redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}

	public Integer getRedisDatabase() {
		return redisDatabase;
	}

	public void setRedisDatabase(Integer redisDatabase) {
		this.redisDatabase = redisDatabase;
	}

	public String getKafkaBootstrapAddress() {
		return kafkaBootstrapAddress;
	}

	public void setKafkaBootstrapAddress(String kafkaBootstrapAddress) {
		this.kafkaBootstrapAddress = kafkaBootstrapAddress;
	}

	public String getKafkaTopic() {
		return kafkaTopic;
	}

	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}

	public String getSftpFilename() {
		return sftpFilename;
	}

	public void setSftpFilename(String sftpFilename) {
		this.sftpFilename = sftpFilename;
	}

	public void setSftpHost(String sftpHost) {
		this.sftpHost = sftpHost;
	}

	public Integer getSftpPort() {
		return sftpPort;
	}

	public void setSftpPort(Integer sftpPort) {
		this.sftpPort = sftpPort;
	}

	public String getSftpUsername() {
		return sftpUsername;
	}

	public void setSftpUsername(String sftpUsername) {
		this.sftpUsername = sftpUsername;
	}

	public String getSftpPassword() {
		return sftpPassword;
	}

	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}

	public String getSftpFolder() {
		return sftpFolder;
	}

	public void setSftpFolder(String sftpFolder) {
		this.sftpFolder = sftpFolder;
	}
	
}
