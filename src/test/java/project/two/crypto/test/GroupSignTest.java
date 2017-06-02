package project.two.crypto.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import project.two.crypto.GroupSign;
import project.two.crypto.GroupSignHelper;
import project.two.crypto.GroupSignJoinRequest;
import project.two.crypto.GroupSignJoinResponse;
import project.two.crypto.GroupSignMemberKey;
import project.two.crypto.GroupSignPublicKey;
import project.two.crypto.GroupSignSignature;

public class GroupSignTest {
	
	private GroupSignPublicKey vk;
	private GroupSign authoritySign;
	private SecureRandom rand;
	
	private long start;
	private long stop;
	
	@Before
	public void setup() {
		startM();
		authoritySign = new GroupSign(true); 
		vk = authoritySign.vk();
		this.rand = new SecureRandom();
		stopM(1, "Authority", "KeyGen");
		
	}
	
	private void startM() {
		start = System.currentTimeMillis();		
	}
	/*private void stopM(int nr, String sType, String operation) {
		stop = System.currentTimeMillis();
		float elapsedTime = (stop - start);
		System.out.println("-------------------------------------------------------------------");
		System.out.print("System: " + sType + ", ");
	    System.out.print("Operation: " + operation+ ", ");
	    System.out.print("Repeat: " + nr + ", ");
	    System.out.print("Zeitmessung: ");
	    System.out.print(elapsedTime / 1000);
	    System.out.println(" seconds");
	    System.out.println("-------------------------------------------------------------------");
	}*/
	
	private void stopM(int nr, String sType, String operation) {
	stop = System.currentTimeMillis();
	float elapsedTime = (stop - start);
	
	System.out.print("System:" + sType + ":");
    System.out.print("Operation:" + operation+ ":");
    System.out.print("Repeat:" + nr + ":");
    System.out.print("Zeitmessung:");
    System.out.print(elapsedTime / 1000);
    System.out.println("");
    
}
	
	private void execOperations(int mCount) {
			
		startM();
		GroupSignMemberKey[] sks = new GroupSignMemberKey[mCount];
		for(int i = 0; i < mCount; i++) {
			sks[i] = GroupSignHelper.joinClientInit(rand, vk);
			// client -> sendet bigY und commitment zum nachweis des berechneten privaten schlüssels
			GroupSignJoinRequest req = new GroupSignJoinRequest(sks[i].bigY(),sks[i].commitment());
			GroupSignJoinResponse resp = authoritySign.joinToGroupServer(req);
			sks[i] = GroupSignHelper.joinClientResponse(vk, resp, sks[i]);
		}
		stopM(mCount, "Client", "Join");
		byte[] testmessage = new BigInteger("1990").toByteArray();
		
		startM();
		GroupSignSignature[] gss = new GroupSignSignature[mCount];
		for(int i = 0; i < mCount; i++) {			
			gss[i] = GroupSignHelper.sign(rand, testmessage, sks[i], vk);
		}
		stopM(mCount, "Client", "Sign");
		
		startM();	
		for(int i = 0; i < mCount; i++) {
			assertTrue(GroupSignHelper.verify(vk, testmessage, gss[i]));
		}
		stopM(mCount, "Server", "verify");
		
		startM();
		for(int i = 0; i < mCount; i++) {
			int pId = authoritySign.open(vk, authoritySign.gsmk(), testmessage, gss[i]);
			//System.out.println("=>"+i+":" + pId);
			//assertEquals(pId, i);
		}
		stopM(mCount, "Authority", "Open");
		
	}
	
	@Test
	public void SignTest() {
		execOperations(1);
		execOperations(2);
		//execOperations(10);	
		//execOperations(15);	
		//execOperations(20);	
		//execOperations(30);		
		//execOperations(100);
	}
	
}
