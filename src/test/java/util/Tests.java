/**
 * Quick Test, if all the methods of the group signature scheme work:
 * tested methods: generate, init, join, sign, verify and open
 */
package util;

import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Test;

import demo.DemoManagerKey;
import demo.DemoPublicKey;
import demo.DemoSecretKey;
import demo.DemoSignature;
import keys.ManagerKey;
import keys.SecretKey;
import keys.PublicKey;
import requests.JoinRequest;
import responses.JoinResponse;
import util.Generator;

public class Tests {

	@Test
	public void testSignAndVerify() {

		PublicKey publicKey = new DemoPublicKey();
		ManagerKey managerKey = new DemoManagerKey();

		Generator.generate(publicKey, managerKey);

		SecretKey memberKey = new DemoSecretKey();
		JoinHelper.init(publicKey, memberKey);

		JoinRequest joinRequest = new JoinRequest(memberKey);

		JoinResponse joinResponse = JoinHelper.join(publicKey, managerKey, joinRequest);

		memberKey.maintainResponse(joinResponse);

		byte[] testmessage = new BigInteger("1990").toByteArray();

		DemoSignature signature = new DemoSignature();
		SignHelper.sign(memberKey, publicKey, testmessage, signature);

		assertTrue(VerifyHelper.verify(publicKey, signature, testmessage));
	}

	@Test
	public void testOpen() {
		PublicKey publicKey = new DemoPublicKey();
		ManagerKey managerKey = new DemoManagerKey();

		Generator.generate(publicKey, managerKey);

		// SmartPhone A
		SecretKey memberKeyA = new DemoSecretKey();
		JoinHelper.init(publicKey, memberKeyA);
		// SmartPhone B
		SecretKey memberKeyB = new DemoSecretKey();
		JoinHelper.init(publicKey, memberKeyB);

		JoinRequest joinRequestA = new JoinRequest(memberKeyA);
		JoinRequest joinRequestB = new JoinRequest(memberKeyB);

		JoinResponse joinResponseA = JoinHelper.join(publicKey, managerKey, joinRequestA);
		JoinResponse joinResponseB = JoinHelper.join(publicKey, managerKey, joinRequestB);

		memberKeyA.maintainResponse(joinResponseA);
		memberKeyB.maintainResponse(joinResponseB);

		byte[] testmessage = new BigInteger("1990").toByteArray();

		DemoSignature signatureA = new DemoSignature(); 
		SignHelper.sign(memberKeyA, publicKey, testmessage, signatureA);
		DemoSignature signatureB =  new DemoSignature(); 
		SignHelper.sign(memberKeyB, publicKey, testmessage, signatureB);

		assertTrue(VerifyHelper.verify(publicKey, signatureA, testmessage));
		assertTrue(VerifyHelper.verify(publicKey, signatureB, testmessage));

		BigInteger[] yArr = new BigInteger[] { memberKeyA.getBigY(), memberKeyB.getBigY() };

		assertEquals(1, OpenHelper.open(publicKey, managerKey, testmessage, signatureB, yArr));
		assertEquals(0, OpenHelper.open(publicKey, managerKey, testmessage, signatureA, yArr));

	}

}
