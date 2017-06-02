package project.two.crypto.test;

import static org.junit.Assert.*;

import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import project.two.crypto.GroupSign;
import project.two.crypto.GroupSignHelper;
import project.two.crypto.GroupSignJoinRequest;
import project.two.crypto.GroupSignJoinResponse;
import project.two.crypto.GroupSignMemberKey;
import project.two.crypto.GroupSignPublicKey;

public class SerializationTest {
	private GroupSignPublicKey vk;
	private GroupSign authoritySign;
	private SecureRandom rand;
	
	@Before
	public void setup() {
		authoritySign = new GroupSign(true); 
		vk = authoritySign.vk();
		this.rand = new SecureRandom();		
	}
	
	@Test
	public void serializeGroupSignPublicKey() {
		Gson gson = new Gson();
		String res = gson.toJson(vk);
		System.out.println("GroupSignPublicKey");
		System.out.println(res);
		
		GroupSignPublicKey vk2 = gson.fromJson(res, GroupSignPublicKey.class);
		assertEquals(vk.a(), vk2.a());
		assertEquals(vk.bigF(), vk2.bigF());
		assertEquals(vk.bigG(), vk2.bigG());
		
		GroupSignMemberKey sk = GroupSignHelper.joinClientInit(rand, vk);
		// client -> sendet bigY und commitment zum nachweis des berechneten privaten schlüssels
		GroupSignJoinRequest req = new GroupSignJoinRequest(sk.bigY(),sk.commitment());
		GroupSignJoinResponse resp = authoritySign.joinToGroupServer(req);
		sk = GroupSignHelper.joinClientResponse(vk, resp, sk);
		
		res = gson.toJson(sk);
		System.out.println("GroupSignMemberKey");
		System.out.println(res);
		
		res = gson.toJson(authoritySign);
		System.out.println("GroupSign");
		System.out.println(res);
		
		
		assertNotNull(res);
	}
}
