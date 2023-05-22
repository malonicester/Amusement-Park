package com.adventurelandVillage.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public interface SecurityConstant {
	public static final String JWT_HEADER = "Authorization";
	public static final String JWT_ISSUER = "AdventureLandVillage";
	public static final String JWT_KEY = "25432A462D4A614E645266556A586E3272357538782F413F4428472B4B6250655368566B5970337336763979244226452948404D635166546A576E5A71347437";
	
	static KeyPair keyPairGenerator() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}
	
}
